package eu.beersafe.mooc.filters.session;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import eu.beersafe.mooc.logger.Logger;

public class CookieSessionService {

	private static Dictionary<String, Session> sessions = new Hashtable<String, Session>();
	
	public static Session getSession(String id) {
		Session session = sessions.get(id);
		if(session == null) session = new Session();
		return session;
	}
	
	public static void saveSession(Session session) {
		Logger.debug("SESSIONS - Saving session object in the CookieService");
		sessions.put(session.getId(), session);
		session.markSaved();
	}
}
