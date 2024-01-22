package save_eat.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/AuthenticationException.html
 */
public class SecurityExceptionEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
		throws IOException {

		if (isUnauthorized(exception)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
		}

	}

	public boolean isUnauthorized(AuthenticationException exception) {

		if (exception instanceof AuthenticationCredentialsNotFoundException) {
			return true;
		}

		return false;

	}

}
