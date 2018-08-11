package com.concretepage.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class AppAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	@Override
	/*Used header based authentication. 
	When we are not using login page based authentication, then for any request 
	to application, Spring needs to send an error with a proper status code. 
	Spring provides BasicAuthenticationEntryPoint that needs be implemented to achieve it.
	It has a method commence() that we will override and return a status code (401) unauthorized with 
    header containing authentication type required for authentication.  */
	public void commence(HttpServletRequest request, HttpServletResponse response,
		     AuthenticationException authException) throws IOException, ServletException {
		response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("MY APP REALM");
	}
}
