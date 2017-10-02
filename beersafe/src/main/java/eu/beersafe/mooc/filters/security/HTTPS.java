package eu.beersafe.mooc.filters.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import eu.beersafe.mooc.filters.base.ConfigurableManager;
import eu.beersafe.mooc.filters.session.CookieSessionService;
import eu.beersafe.mooc.filters.session.Session;
import eu.beersafe.mooc.logger.Logger;

public class HTTPS extends ConfigurableManager implements HandlerInterceptor {

	private static final String FILENAME = "https.txt";
	
	private boolean redirectHttp = false;
	private boolean hstsEnabled = false;
	private String hstsPolicy = "";
	
	public HTTPS() {
		super(FILENAME);
	}
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getScheme().toLowerCase().equals("http")) {
			if(readPolicyFile(request)) {
				if(this.isRedirectHttp()) {
					String url = getFullURL(request);
					
					Logger.info("HTTPS - Redirecting HTTP request to HTTPS: " + url);
					response.setStatus(301);
					response.sendRedirect(url.replace("http", "https"));
					return false;
				}
			}
			else {
				Logger.error("HTTPS - Failed to read configuration file: " + FILENAME);
			}		
		}

		return true;
	}
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(readPolicyFile(request)) {
			if(this.isHstsEnabled()) {
				Logger.info("Setting the following HSTS header: " + this.getHstsPolicy());
				response.addHeader("Strict-Transport-Security", this.getHstsPolicy());
			}
		}
		else {
			Logger.error("HTTPS - Failed to read configuration file: " + FILENAME);
		}		
	}
	
	@Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
	
	private static String getFullURL(HttpServletRequest request) {
	    StringBuffer requestURL = request.getRequestURL();
	    String queryString = request.getQueryString();

	    if (queryString == null) {
	        return requestURL.toString();
	    } else {
	        return requestURL.append('?').append(queryString).toString();
	    }
	}
	
	@Override
	protected void processLine(String line) {
		if(line.startsWith("hsts-enabled: ")) {
			this.setHstsEnabled(Boolean.parseBoolean(line.replace("hsts-enabled: ", "").trim()));
		}
		else if(line.startsWith("redirect-enabled: ")) {
			this.setRedirectHttp(Boolean.parseBoolean(line.replace("redirect-enabled: ", "").trim()));
		}
		else if(line.startsWith("hsts-policy: ")) {
			this.setHstsPolicy(line.replace("hsts-policy: ", "").trim());
		}
	}

	private boolean isRedirectHttp() {
		return redirectHttp;
	}

	private void setRedirectHttp(boolean redirectHttp) {
		this.redirectHttp = redirectHttp;
	}

	private boolean isHstsEnabled() {
		return hstsEnabled;
	}

	private void setHstsEnabled(boolean hstsEnabled) {
		this.hstsEnabled = hstsEnabled;
	}

	private String getHstsPolicy() {
		return hstsPolicy;
	}

	private void setHstsPolicy(String hstsPolicy) {
		this.hstsPolicy = hstsPolicy;
	}

	
	
	
}
