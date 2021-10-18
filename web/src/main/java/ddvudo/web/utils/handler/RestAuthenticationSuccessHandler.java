package ddvudo.web.utils.handler;

import ddvudo.web.bean.User;
import ddvudo.web.service.UserService;
import ddvudo.web.utils.SystemCode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final UserService userService;

	public RestAuthenticationSuccessHandler(ApplicationEventPublisher eventPublisher, UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		User springUser = (User) authentication.getPrincipal();
		User user = (User) userService.loadUserByUsername(springUser.getUsername());
		User newUser = new User();
		newUser.setName(user.getName());
		RestUtil.response(response, SystemCode.OK, SystemCode.OK.getMessage(), newUser);
	}
}
