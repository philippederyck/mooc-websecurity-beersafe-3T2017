package eu.beersafe.mooc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import eu.beersafe.mooc.filters.security.CSP;
import eu.beersafe.mooc.filters.security.HTTPS;
import eu.beersafe.mooc.filters.session.CookieSessionManager;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
    CookieSessionManager getSessionManager() {
         return new CookieSessionManager();
    }
	
	@Bean
    CSP getCSP() {
         return new CSP();
    }
	
	@Bean
    HTTPS getHTTPS() {
         return new HTTPS();
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("redirect:/Home");
	}
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	    	registry.addInterceptor(getSessionManager()).addPathPatterns("/**");
     	registry.addInterceptor(getCSP()).addPathPatterns("/**");
     	registry.addInterceptor(getHTTPS()).addPathPatterns("/**");
    }
    
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/font/**");
		registry.addMapping("/fonts/**");
	}
}
