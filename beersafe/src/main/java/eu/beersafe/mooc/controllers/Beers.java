package eu.beersafe.mooc.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.beersafe.mooc.data.access.BeerDAO;
import eu.beersafe.mooc.data.access.NoteDAO;
import eu.beersafe.mooc.data.objects.Beer;
import eu.beersafe.mooc.data.objects.Note;
import eu.beersafe.mooc.filters.session.Session;
import eu.beersafe.mooc.logger.Logger;

@Controller
public class Beers extends AbstractController {

	@RequestMapping(value = "/Beers")
	public ModelAndView listAllBeers() {
		try {
			Logger.info("GET /Beers");
			
			List<Beer> beers =  (new BeerDAO()).findAll();			
	        return new ModelAndView("beers", "beers", beers);
		}
		catch(SQLException e) {
			Logger.error("Unexpected database error. Aborting the operation", e);
			return handleError("Unexpected database error.", e);
		}
		catch(Exception e) {
			Logger.error("Unexpected error occurred. Aborting the operation", e);
			return handleError("Unexpected error occurred.", e);
		}
	}
	
	@RequestMapping(value = "/Beers", params = "search")
	public ModelAndView searchBeers(@RequestParam("search") String search) {
		try {
			Logger.info("GET /Beers (search = '" + search + "')");
			
			List<Beer> beers =  (new BeerDAO()).findAllByName(search);			
			ModelAndView model = new ModelAndView("beers", "beers", beers);
			model.addObject("query", search);	
	        return model;
		}
		catch(SQLException e) {
			Logger.error("Unexpected database error. Aborting the operation", e);
			return handleError("Unexpected database error.", e);
		}
		catch(Exception e) {
			Logger.error("Unexpected error occurred. Aborting the operation", e);
			return handleError("Unexpected error occurred.", e);
		}
	}
	
	@RequestMapping(value = "/Beers", params = "id")
	public ModelAndView showBeer(@RequestParam("id") int id, HttpServletRequest request) {
		try {
			Logger.info("GET /Beers (id = '" + id + "')");
			
			// Determine if the user is logged in
			long currentUserId = -1;
			Session session = ((Session)request.getAttribute("session"));
			if(session.isAuthenticated()) {
				Logger.debug("User is authenticated, also showing private notes");
				currentUserId = session.getAuthenticatedUser().getId();
			}
			else {
				Logger.debug("User is not authenticated, only showing public notes");
			}
			
			// Get the beer
			Beer beer =  (new BeerDAO()).findOneById(id);
			
			//Get the notes for the beer and filter
			List<Note> allNotes = (new NoteDAO()).findAllByBeerId(id);
			List<Note> visibleNotes = new ArrayList<Note>();
			for(Note n : allNotes) {
				if(n.isPublicNote() || n.getUserid() == currentUserId) {
					visibleNotes.add(n);
				}
			}
			
			ModelAndView model = new ModelAndView("beer", "beer", beer);
			model.addObject("notes", visibleNotes);
	        return model;
		}
		catch(SQLException e) {
			Logger.error("Unexpected database error. Aborting the operation", e);
			return handleError("Unexpected database error.", e);
		}
		catch(Exception e) {
			Logger.error("Unexpected error occurred. Aborting the operation", e);
			return handleError("Unexpected error occurred.", e);
		}
	}
	
}
