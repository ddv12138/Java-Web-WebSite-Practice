package GlobalUtils.Config;

import GlobalUtils.PasswdEncoder;
import WebComponent.Service.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

	public SecurityConfig(UserService userService, PasswdEncoder passwdEncoder,
						  RedisIndexedSessionRepository redisIndexedSessionRepository) {
		this.userService = userService;
		this.passwdEncoder = passwdEncoder;
		this.redisIndexedSessionRepository = redisIndexedSessionRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//				.antMatchers("/user/**", "/", "/spittr", "/spittr/{id}").permitAll()
				.antMatchers("user/login", "/sa-html/cfg/500.html").permitAll()
				.regexMatchers(".*(css|js|ico|png|jpg)\\??[^/\\\\]*").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				//指定登录页的路径
				.loginPage("/login.html")
				.usernameParameter("username")
				.passwordParameter("password")
				//指定自定义form表单请求的路径
				.loginProcessingUrl("/user/login")
				.failureUrl("/sa-html/cfg/500.html")
				.defaultSuccessUrl("/")
				//必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
				//这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
				.and().logout().logoutUrl("/user/logout")
				.logoutSuccessUrl("/login.html")
				.permitAll();
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
}
