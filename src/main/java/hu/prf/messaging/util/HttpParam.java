package hu.prf.messaging.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Handles the HTTP GET and POST parameters.
 */
@Named
public class HttpParam implements Serializable {

	private static final long serialVersionUID = 7983554186431906866L;

	@Inject
	private HttpServletRequest request;

	public String getParam(String key) {
		return request.getParameter(key);
	}

}
