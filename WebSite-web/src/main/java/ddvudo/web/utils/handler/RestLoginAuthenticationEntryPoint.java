package ddvudo.web.utils.handler;

import ddvudo.web.utils.SystemCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestLoginAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	public RestLoginAuthenticationEntryPoint() {
		super("/user/login");
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) {
		RestUtil.response(response, SystemCode.UNAUTHORIZED);
	}
}
