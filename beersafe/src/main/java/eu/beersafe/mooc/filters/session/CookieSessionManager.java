package eu.beersafe.mooc.filters.session;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import eu.beersafe.mooc.filters.base.ConfigurableManager;
import eu.beersafe.mooc.logger.Logger;

public class CookieSessionManager extends ConfigurableManager implements HandlerInterceptor {

	private static final String FILENAME = "sessions.txt";
	private String COOKIE_NAME = "BEERID";
	
	private Dictionary<String, Boolean> featureStates = new Hashtable<String, Boolean>();
	private Dictionary<String, String> featureSettings = new Hashtable<String, String>();
	
	public CookieSessionManager() {
		super(FILENAME);
	}
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(readPolicyFile(request)) {
			// Create an empty session by default
			Session session = new Session();
			
			// Check if we can find an existing session
			Enumeration<String> headers = request.getHeaders("Cookie");
			while(headers.hasMoreElements()) {
				String h = headers.nextElement();
				String[] cookies = h.split(";");
				for(String c : cookies) {
					c = c.trim();
					if(c.startsWith(COOKIE_NAME + "=")) {
						session = CookieSessionService.getSession(c.replaceFirst(COOKIE_NAME + "=", ""));
						
						if(session.isNewlyCreated())  {
							Logger.debug("SESSIONS - Session cookie found, but no matching session. Starting a new one");
						}
						else {
							Logger.debug("SESSIONS - Resuming server-side session");
						}
					}
				}
			}
			
			// Attach the session to the request
			request.setAttribute("session", session); 
		}
		else {
			Logger.error("SESSIONS - Failed to read configuration file: " + FILENAME);
		}
		
		return true;
	}
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		if(readPolicyFile(request)) {
			Object o = request.getAttribute("session");
			if(o != null) {
				Session session = (Session) o;
				if(session.isNewlyCreated() && session.isModified()) {
					CookieSessionService.saveSession(session);
					
					// Generate the set-cookie header with all flags
					String header = COOKIE_NAME + "=" + session.getId();
					
					if(this.getFeatureStates().get("secure")) {
						header += "; Secure";
					}
					if(this.getFeatureStates().get("httponly")) {
						header += "; HttpOnly";
					}
					if(this.getFeatureStates().get("domain")) {
						header += "; Domain=" + this.getFeatureSettings().get("domain");
					}
					if(this.getFeatureStates().get("samesite")) {
						header += "; SameSite=" + this.getFeatureSettings().get("samesite");
					}
					
					Logger.debug("SESSIONS - Found new session, sending a Set-Cookie header");
					response.addHeader("Set-Cookie", header);
				}
			}
		}
		else {
			Logger.error("SESSIONS - Failed to read configuration file: " + FILENAME);
		}		
	}

	

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

	
	@Override
	protected void processLine(String line) {
		if(line.startsWith("mode: ")) {
			this.getFeatureSettings().put("mode", line.replace("mode: ", ""));
		}
		else if(line.startsWith("secure: ")) {
			this.getFeatureStates().put("secure", Boolean.parseBoolean(line.replace("secure: ", "")));
		}
		else if(line.startsWith("httponly: ")) {
			this.getFeatureStates().put("httponly", Boolean.parseBoolean(line.replace("httponly: ", "")));
		}
		else if(line.startsWith("domain: ")) {
			String value = line.replace("domain: ", "");
			if(value.equals("none")) {
				this.getFeatureStates().put("domain", false);
			}
			else {
				this.getFeatureStates().put("domain", true);
				this.getFeatureSettings().put("domain", value);
			}
		}
		else if(line.startsWith("samesite: ")) {
			String value = line.replace("samesite: ", "");
			if(value.equals("none")) {
				this.getFeatureStates().put("samesite", false);
			}
			else {
				this.getFeatureStates().put("samesite", true);
				this.getFeatureSettings().put("samesite", value);
			}
		}
		else if(line.startsWith("prefix_secure: ")) {
			this.getFeatureStates().put("prefix_secure", Boolean.parseBoolean(line.replace("prefix_secure: ", "")));
		}
		else if(line.startsWith("prefix_host: ")) {
			this.getFeatureStates().put("prefix_host", Boolean.parseBoolean(line.replace("prefix_host: ", "")));
		}
	}
	

	protected Dictionary<String, Boolean> getFeatureStates() {
		return featureStates;
	}
	protected Dictionary<String, String> getFeatureSettings() {
		return featureSettings;
	}
	
}
