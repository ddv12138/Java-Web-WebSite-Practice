package GlobalUtils.Config;

import GlobalUtils.PasswdEncoder;
import WebComponent.Service.Services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	UserService userService;
	PasswdEncoder passwdEncoder;

	public SecurityConfig(UserService userService, PasswdEncoder passwdEncoder) {
		this.userService = userService;
		this.passwdEncoder = passwdEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//				.antMatchers("/user/**", "/", "/spittr", "/spittr/{id}").permitAll()
				.antMatchers("user/login").permitAll()
				.regexMatchers(".*(css|js|ico|png|jpg)\\??[^/\\\\]*").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				//指定登录页的路径
				.loginPage("/user/login")
				.usernameParameter("username")
				.passwordParameter("password")
				//指定自定义form表单请求的路径
				.loginProcessingUrl("/user/login")
//				.failureUrl("/spittr/message?message=")
				.defaultSuccessUrl("/")
				//必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
				//这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
				.and().logout().logoutUrl("/user/logout")
				.logoutSuccessUrl("/user/login")
				.permitAll();
		//默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
		http.csrf().disable();
//		http.authorizeRequests().anyRequest().permitAll();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwdEncoder);
	}
}
