package eu.beersafe.mooc.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
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
public class Notes extends AbstractController {

	@RequestMapping(value = "/Notes")
	public ModelAndView listAllNotes(HttpServletRequest request) {
		try {
			Logger.info("GET /Notes");
			
			Session session = ((Session)request.getAttribute("session"));
			if(session.isAuthenticated()) {
				List<Note> notes = (new NoteDAO()).findAllByUserId((int)session.getAuthenticatedUser().getId());
		        return new ModelAndView("notes", "notes", notes);
			}
			else {
				Logger.warn("User is not authenticated. Redirecting to the login page.");
				return new ModelAndView("redirect:Login");
			}
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
	
	@RequestMapping(value = "/EditNote", params = "id", method = RequestMethod.GET)
	public ModelAndView editNote(@RequestParam("id") int id, HttpServletRequest request) {
		try {
			Logger.info("GET /EditNote (id = " + id + ")");
			
			Session session = ((Session)request.getAttribute("session"));
			if(session.isAuthenticated()) {
				Note note = (new NoteDAO()).findOneById(id);
		        return new ModelAndView("editnote", "note", note);
			}
			else {
				Logger.warn("User is not authenticated. Redirecting to the login page.");
				return new ModelAndView("redirect:Login");
			}
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
	
	@RequestMapping(value = "/UpdateNote", method = RequestMethod.POST)
	public ModelAndView updateNote(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("publicNote") Optional<String> publicNote, HttpServletRequest request) {
		try {
			Logger.info("POST /UpdateNote (id = " + id + ")");
			
			Session session = ((Session)request.getAttribute("session"));
			if(session.isAuthenticated()) {
				Note note = (new NoteDAO()).findOneById(id);
				note.setTitle(title);
				note.setContent(content);
				note.setPublicNote(publicNote.isPresent() ? true : false);
				
				if(note.isValid()) {
					if((new NoteDAO()).updateNote(note)) {
						Logger.info("Tasting note updated successfully! Redirecting to the beer page.");
						return new ModelAndView("redirect:/Beers?id=" + note.getBeerid());
					}
					else {					
						Logger.error("Update of the note failed in the database. Operation returned false");
						ModelAndView model = new ModelAndView("editnote", "note", note);
						model.addObject("error", "Update of the note failed in the database for unknown reasons.");
						return model;
					}
				}
				else {				
					Logger.info("Provided data is not considered valid.");
					ModelAndView model = new ModelAndView("editnote", "note", note);
					model.addObject("error", "Invalid input. Title and content are mandatory fields.");
					return model;
				}
			}
			else {
				Logger.warn("User is not authenticated. Redirecting to the login page.");
				return new ModelAndView("redirect:Login");
			}
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
	
	@RequestMapping(value = "/NewNote", params = "id", method = RequestMethod.GET)
	public ModelAndView newNote(@RequestParam("id") int beerid, HttpServletRequest request) {
		try {
			Logger.info("GET /EditNote (id = " + beerid + ")");
			
			Session session = ((Session)request.getAttribute("session"));
			if(session.isAuthenticated()) {
				Beer beer = (new BeerDAO()).findOneById(beerid);
		        return new ModelAndView("createnote", "beer", beer);
			}
			else {
				Logger.warn("User is not authenticated. Redirecting to the login page.");
				return new ModelAndView("redirect:Login");
			}
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
	
	@RequestMapping(value = "/CreateNote", method = RequestMethod.POST)
	public ModelAndView createNote(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("publicNote") Optional<String> publicNote, @RequestParam("beerid") int beerid, HttpServletRequest request) {
		try {
			Logger.info("POST /CreateNote (...)");
			
			Session session = ((Session)request.getAttribute("session"));
			if(session.isAuthenticated()) {
				//Retrieve the beer to rebuild the page in case of an error
				Beer beer = (new BeerDAO()).findOneById(beerid);
				
				boolean publicNoteBool = (publicNote.isPresent() ? true : false);
				Note note = new Note(-1, Timestamp.from(Instant.now()), title, content, publicNoteBool, session.getAuthenticatedUser().getId(), beerid);				
				if(note.isValid()) {
					if((new NoteDAO()).createNote(note)) {
						Logger.info("New tasting note created successfully! Redirecting to the beer page.");
						return new ModelAndView("redirect:/Beers?id=" + note.getBeerid());
					}
					else {
						Logger.error("Creation of the note failed in the database. Operation returned false");
						ModelAndView model = new ModelAndView("createnote", "beer", beer);
						model.addObject("note", note);
						model.addObject("error", "Creation of the note failed in the database for unknown reasons.");
						return model;
					}
				}
				else {
					Logger.info("Provided data is not considered valid.");
					ModelAndView model = new ModelAndView("createnote", "beer", beer);
					model.addObject("note", note);
					model.addObject("error", "Invalid input. Title and content are mandatory fields.");
					return model;
				}
			}
			else {
				Logger.warn("User is not authenticated. Redirecting to the login page.");
				return new ModelAndView("redirect:Login");
			}
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