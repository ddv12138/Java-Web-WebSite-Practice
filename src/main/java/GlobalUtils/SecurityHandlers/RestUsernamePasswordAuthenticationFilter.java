package GlobalUtils.SecurityHandlers;

import GlobalUtils.Global;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
//		if (requiresAuthentication(request, response)) {
//			chain.doFilter(request, res);
//			return;
//		}
		Authentication authResult;

		try {
			authResult = attemptAuthentication(request, response);
			if (authResult == null) {
				// return immediately as subclass has indicated that it hasn't completed
				// authentication
				return;
			}
			sessionStrategy.onAuthentication(authResult, request, response);
		} catch (InternalAuthenticationServiceException failed) {
			logger.error(
					"An internal error occurred while trying to authenticate the user.",
					failed);
			unsuccessfulAuthentication(request, response, failed);

			return;
		} catch (AuthenticationException failed) {
			// Authentication failed
			unsuccessfulAuthentication(request, response, failed);

			return;
		}


		successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}
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
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			setDetails(request, authenticationToken);
			return this.getAuthenticationManager().authenticate(authenticationToken);
		} else {
			return super.attemptAuthentication(request, response);
		}
	}

	@Nullable
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String ss = super.getPasswordParameter();
		return request.getParameter(super.getPasswordParameter());
	}

	@Nullable
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(super.getUsernameParameter());
	}
}
