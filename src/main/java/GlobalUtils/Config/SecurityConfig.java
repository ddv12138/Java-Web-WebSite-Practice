package GlobalUtils.Config;

import GlobalUtils.PasswdEncoder;
import WebComponent.Service.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;
	@Autowired
	PasswdEncoder passwdEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user/**", "/", "/spittr", "/spittr/{id}").permitAll()
				.regexMatchers(".*(css|js|ico|png)\\??[^/\\\\]*").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				//指定登录页的路径
				.loginPage("/user/login")
				//指定自定义form表单请求的路径
				.loginProcessingUrl("/user/login")
//				.failureUrl("/spittr/message?message=")
//				.defaultSuccessUrl("/")
				//必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
				//这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
				.and().logout().logoutUrl("/user/logout")
				.logoutSuccessUrl("/")
				.permitAll();
		//默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
		http.csrf().disable();
//		http.authorizeRequests().antMatchers ("/user/**","/","/spittr").permitAll().anyRequest().authenticated()
//		.and().formLogin().loginPage("/user/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwdEncoder);
	}
}
