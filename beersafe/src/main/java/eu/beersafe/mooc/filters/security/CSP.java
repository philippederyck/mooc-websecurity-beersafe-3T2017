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

public class CSP extends ConfigurableManager implements HandlerInterceptor {

	private static final String FILENAME = "csp.txt";
	
	private boolean enabled = false;
	private boolean reportOnly = false;
	private List<String> directives = new ArrayList<String>();
	
	public CSP() {
		super(FILENAME);
	}
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//Reset the values
		this.setDirectives(new ArrayList<String>());
		
		if(readPolicyFile(request)) {
			if(this.isEnabled()) {
				String header = (this.isReportOnly() ? "Content-Security-Policy-Report-Only" : "Content-Security-Policy");
				String policy = String.join(" ", this.getDirectives());
				Logger.info("Setting the following CSP header: " + header + ": " + policy);
				response.addHeader(header, policy);
			}
		}
		else {
			Logger.error("CSP - Failed to read configuration file: " + FILENAME);
		}		
	}
	
	@Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
	
	@Override
	protected void processLine(String line) {
		if(line.startsWith("enabled: ")) {
			this.setEnabled(Boolean.parseBoolean(line.replace("enabled: ", "").trim()));
		}
		else if(line.startsWith("report-only: ")) {
			this.setReportOnly(Boolean.parseBoolean(line.replace("report-only: ", "").trim()));
		}
		else if(line.startsWith("directive: ")) {
			this.getDirectives().add(line.replace("directive: ", "").trim());
		}
	}

	private boolean isEnabled() {
		return enabled;
	}

	private void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private boolean isReportOnly() {
		return reportOnly;
	}

	private void setReportOnly(boolean reportOnly) {
		this.reportOnly = reportOnly;
	}

	private List<String> getDirectives() {
		return directives;
	}

	private void setDirectives(List<String> directives) {
		this.directives = directives;
	}
	
	
}
