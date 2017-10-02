package eu.beersafe.mooc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import eu.beersafe.mooc.logger.Logger;

@Controller
public class Taste extends AbstractController {

	@RequestMapping("/Taste")
	public ModelAndView doGet() {
		try {
			Logger.info("GET /Taste");
	        return new ModelAndView("taste");
		}
		catch(Exception e) {
			Logger.error("Unexpected error occurred. Aborting the operation", e);
			return handleError("Unexpected error occurred.", e);
		}
	}
	
}