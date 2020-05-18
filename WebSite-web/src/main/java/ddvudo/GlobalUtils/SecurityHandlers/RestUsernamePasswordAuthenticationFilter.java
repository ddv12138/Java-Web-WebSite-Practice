package ddvudo.GlobalUtils.SecurityHandlers;

import com.alibaba.fastjson.JSON;
import ddvudo.root.GlobalUtils.Global;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RestUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	public RestUsernamePasswordAuthenticationFilter(RestAuthenticationSuccessHandler restAuthenticationSuccessHandler,
													RestAuthenticationFailureHandler restAuthenticationFailureHandler) {
		super(new AntPathRequestMatcher("/user/login", "POST"));
		this.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
		this.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", "");
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			String username = null;
			String password = null;
			try {
				Map<String, String> map = JSON.parseObject(request.getInputStream(), Map.class);
				username = map.get("username");
				password = map.get("password");
			} catch (IOException e) {
				Global.Logger(this).error(e);
			}
			if (username == null) {
				username = "";
			}
			if (password == null) {
				password = "";
			}
			authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			setDetails(request, authenticationToken);
		}
		return this.getAuthenticationManager().authenticate(authenticationToken);
	}

	private void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
}
