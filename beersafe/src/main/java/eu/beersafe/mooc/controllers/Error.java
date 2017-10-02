package eu.beersafe.mooc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import eu.beersafe.mooc.logger.Logger;

@Controller
public class Error implements ErrorController {

	private static final String PATH = "/error";
	
	@RequestMapping(PATH)
	public ModelAndView handleError(HttpServletRequest request) {
        return new ModelAndView("error", "message", "Internal Server Error");
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}