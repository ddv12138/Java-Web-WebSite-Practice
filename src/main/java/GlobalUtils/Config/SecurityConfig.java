package GlobalUtils.Config;

import GlobalUtils.PasswdEncoder;
import GlobalUtils.SecurityHandlers.*;
import WebComponent.Service.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;


@Configuration
@EnableWebSecurity
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class SecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {
	RedisIndexedSessionRepository redisIndexedSessionRepository;
	UserService userService;
	PasswdEncoder passwdEncoder;
	RestAccessDeniedHandler restAccessDeniedHandler;
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	RestLoginAuthenticationEntryPoint restAuthenticationEntryPoint;
	RestAuthenticationProvider restAuthenticationProvider;
	RestLogoutSuccessHandler restLogoutSuccessHandler;

	public SecurityConfig(UserService userService, PasswdEncoder passwdEncoder,
						  RedisIndexedSessionRepository redisIndexedSessionRepository, RestAccessDeniedHandler restAccessDeniedHandler,
						  RestAuthenticationSuccessHandler restAuthenticationSuccessHandler,
						  RestAuthenticationFailureHandler restAuthenticationFailureHandler,
						  RestLoginAuthenticationEntryPoint restAuthenticationEntryPoint,
						  RestAuthenticationProvider restAuthenticationProvider,
						  RestLogoutSuccessHandler restLogoutSuccessHandler) {
		this.userService = userService;
		this.passwdEncoder = passwdEncoder;
		this.redisIndexedSessionRepository = redisIndexedSessionRepository;
		this.restAccessDeniedHandler = restAccessDeniedHandler;
		this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
		this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.restAuthenticationProvider = restAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user/login", "/").permitAll()
				.regexMatchers(".*(css|js|ico|png|jpg|html)\\??[^/\\\\]*").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterAt(restUsernamePasswordAuthenticationFilter(restAuthenticationSuccessHandler, restAuthenticationFailureHandler), UsernamePasswordAuthenticationFilter.class)
				.formLogin()
//				.loginPage("/login.html")
				.usernameParameter("username")
				.passwordParameter("password")
//				.loginProcessingUrl("/user/login")
				.failureHandler(restAuthenticationFailureHandler).permitAll()
//				.defaultSuccessUrl("/")
				.and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/login.html").clearAuthentication(true).permitAll()
				.and().exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint)
				.and().authenticationProvider(restAuthenticationProvider);
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
	RestUsernamePasswordAuthenticationFilter restUsernamePasswordAuthenticationFilter
			(RestAuthenticationSuccessHandler restAuthenticationSuccessHandler,
			 RestAuthenticationFailureHandler restAuthenticationFailureHandler) throws Exception {

		RestUsernamePasswordAuthenticationFilter tokenProcessingFilter
				= new RestUsernamePasswordAuthenticationFilter(restAuthenticationSuccessHandler, restAuthenticationFailureHandler);
		tokenProcessingFilter.setAuthenticationManager(super.authenticationManager());
		return tokenProcessingFilter;
	}
}
