package eu.beersafe.mooc.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.beersafe.mooc.data.access.BeerDAO;
import eu.beersafe.mooc.data.access.UserDAO;
import eu.beersafe.mooc.data.objects.Beer;
import eu.beersafe.mooc.data.objects.User;
import eu.beersafe.mooc.filters.session.Session;
import eu.beersafe.mooc.logger.Logger;
import eu.beersafe.mooc.utils.AuthenticationUtils;

@Controller
public class Authentication extends AbstractController {

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView doGetLogin() {
		try {
			Logger.info("GET /Login");
	        return new ModelAndView("login");
		}
		catch(Exception e) {
			Logger.error("Unexpected error occurred. Aborting the operation", e);
			return handleError("Unexpected error occurred.", e);
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView doPostLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
		try {
			Logger.info("POST /Login (email = " + email + ", password = " + password + ")");
			
			Session session = ((Session)request.getAttribute("session"));
			if(!session.isAuthenticated()) {
				List<User> users = new UserDAO().findAllByEmail(email);
				if(users.size() == 1) {
					User user = users.get(0);
					if(AuthenticationUtils.verifyAndClearPassword(user, password)) {
						Logger.info("Authentication successful. Storing authenticated user in the session (" + user.getName() + ")");
						session.setAuthenticatedUser(user);
						return new ModelAndView("redirect:Home");
					}
					else {
						Logger.warn("Invalid password. Authentication failed");
						
						ModelAndView model = new ModelAndView("login");
						model.addObject("email", email);
						model.addObject("password", password);
						model.addObject("error", "Invalid username / password combination");
						return model;
					}
				}
				else {
					if(users.size() == 0) {
						Logger.warn("No matching user account found. Authentication failed");
					}
					else {
						Logger.warn("Multiple records returned by the database. Considering this a failed authentication.");
					}
					
					ModelAndView model = new ModelAndView("login");
					model.addObject("email", email);
					model.addObject("password", password);
					model.addObject("error", "Unknown username / password combination");
					return model;
				}
			}
			else {
				Logger.warn("User is already authenticated. Aborting login attempt and redirecting to Home.");
				return new ModelAndView("redirect:Home");
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
	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public ModelAndView doGetLogout(HttpServletRequest request) {       
		try {
			Logger.info("GET /Logout");
			
			Session session = ((Session)request.getAttribute("session"));
			if(session.isAuthenticated()) {
				Logger.info("Destroying authenticated user (" + session.getAuthenticatedUser().getName() + ")");
				session.setAuthenticatedUser(null);
			}
			else {
				Logger.warn("Logout attempted, but the session is not authenticated. Redirecting to home anyway.");
			}
			
	        return new ModelAndView("redirect:Home");
		}
		catch(Exception e) {
			Logger.error("Unexpected error occurred. Aborting the operation", e);
			return handleError("Unexpected error occurred.", e);
		}
	}
	
	
}
