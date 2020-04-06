package GlobalUtils.Config;

import GlobalUtils.PasswdEncoder;
import GlobalUtils.SecurityHandlers.RestAuthenticationHandler;
import GlobalUtils.SecurityHandlers.RestUsernamePasswordAuthenticationFilter;
import GlobalUtils.SecurityHandlers.RestUtil;
import GlobalUtils.SystemCode;
import WebComponent.Service.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Configuration
@EnableWebSecurity
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class SecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {
	RedisIndexedSessionRepository redisIndexedSessionRepository;
	UserService userService;
	PasswdEncoder passwdEncoder;
	RestAuthenticationHandler restAuthenticationHandler;

	public SecurityConfig(UserService userService, PasswdEncoder passwdEncoder,
						  RedisIndexedSessionRepository redisIndexedSessionRepository, RestAuthenticationHandler restAuthenticationHandler) {
		this.userService = userService;
		this.passwdEncoder = passwdEncoder;
		this.redisIndexedSessionRepository = redisIndexedSessionRepository;
		this.restAuthenticationHandler = restAuthenticationHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user/login", "/sa-html/cfg/*").permitAll()
				.regexMatchers(".*(css|js|ico|png|jpg)\\??[^/\\\\]*").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterAt(restUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.formLogin()
				.loginPage("/login.html")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/user/login")
				.failureHandler(restAuthenticationHandler).permitAll()
				.defaultSuccessUrl("/")
				.and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/login.html").permitAll()
				.and().exceptionHandling().accessDeniedHandler(restAuthenticationHandler);
		//默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
		http.csrf().disable();
//		http.authorizeRequests().anyRequest().permitAll();
		http.headers().frameOptions().disable();
		//spring session配置
		http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
	}

	@Bean
	public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<S>(
				(FindByIndexNameSessionRepository<S>) this.redisIndexedSessionRepository);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwdEncoder);
	}

	@Bean
	RestUsernamePasswordAuthenticationFilter restUsernamePasswordAuthenticationFilter() throws Exception {
		RestUsernamePasswordAuthenticationFilter tokenProcessingFilter = new RestUsernamePasswordAuthenticationFilter();
		tokenProcessingFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
				RestUtil.response(resp, SystemCode.OK, "sucess", authentication);
			}
		});
		tokenProcessingFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
				resp.setContentType("application/json;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.write(new ObjectMapper().writeValueAsString("登录失败!"));
				out.flush();
				out.close();
			}
		});
		tokenProcessingFilter.setAuthenticationManager(super.authenticationManager());
		return tokenProcessingFilter;
	}
}
