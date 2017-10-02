package eu.beersafe.mooc.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractController {

	public ModelAndView handleError(String message, Exception e) {
		ModelAndView model = handleError(message);
		model.addObject("exception", e);
		return model;
	}
	
	public ModelAndView handleError(String message) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("message", message);
		return model;
	}
}
