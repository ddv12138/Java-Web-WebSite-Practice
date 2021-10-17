package ddvudo.web.GlobalUtils.SecurityHandlers;

import ddvudo.web.ORM.POJO.User;
import ddvudo.web.Service.Services.UserService;
import ddvudo.root.GlobalUtils.SystemCode;
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
