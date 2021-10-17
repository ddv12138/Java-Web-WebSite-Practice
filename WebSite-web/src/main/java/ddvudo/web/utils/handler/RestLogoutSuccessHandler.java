package ddvudo.web.utils.handler;

import ddvudo.web.bean.User;
import ddvudo.web.service.UserService;
import ddvudo.web.utils.SystemCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	private final UserService userService;

	public RestLogoutSuccessHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		User springUser = (User) authentication.getPrincipal();
		if (null != springUser) {
			userService.loadUserByUsername(springUser.getUsername());
		}
		RestUtil.response(response, SystemCode.OK);
	}
}

