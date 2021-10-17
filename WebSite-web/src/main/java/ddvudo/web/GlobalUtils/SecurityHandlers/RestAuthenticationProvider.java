package ddvudo.web.GlobalUtils.SecurityHandlers;

import ddvudo.web.ORM.POJO.User;
import ddvudo.web.Service.Services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationProvider implements AuthenticationProvider {
	private final UserService userService;

	public RestAuthenticationProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();

		User user = (User) userService.loadUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名或密码错误");
		}

		boolean result = userService.authUser(user);
		if (!result) {
			throw new BadCredentialsException("用户名或密码错误");
		}

		if (user.getBaned()) {
			throw new LockedException("用户被禁用");
		}

		User authUser = new User();
		authUser.setName(user.getName());
		authUser.setPassword(user.getPassword());
		return new UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return false;
	}
}
