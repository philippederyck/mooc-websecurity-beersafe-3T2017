package eu.beersafe.mooc.filters.session;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Dictionary;
import java.util.Hashtable;

import eu.beersafe.mooc.data.objects.User;


public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	private static SecureRandom random = new SecureRandom();

	private String id;
	private boolean newlyCreated;
	private boolean modified;
	private Dictionary<String, Object> attributes;

	
	public Session() {
		this.setId(nextSessionId());
		this.setNewlyCreated(true);
		this.setModified(false);
		this.setAttributes(new Hashtable<String, Object>());
	}
	
	public void markSaved() {
		this.setNewlyCreated(false);
		this.setModified(false);
	}
	
	private void markModified() {
		this.setModified(true);
	}
	
	public void setAttribute(String key, Object value) {
		this.getAttributes().put(key, value);
		this.markModified();
	}
	
	public void removeAttribute(String key) {
		this.getAttributes().remove(key);
		this.markModified();
	}
	
	public Object getAttribute(String key) {
		return this.getAttributes().get(key);
	}
	
	public void setAuthenticatedUser(User user) {
		if(user != null) {
			this.setAttribute("authenticatedUser", user);
		}
		else {
			this.removeAttribute("authenticatedUser");
		}
	}
	
	public User getAuthenticatedUser() {
		if(isAuthenticated()) {
			return (User)this.getAttribute("authenticatedUser");
		}
		else {
			return null;
		}
	}
	
	public boolean isAuthenticated() {
		return this.getAttribute("authenticatedUser") != null;
	}
	
	
	private String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
	
	public boolean isNewlyCreated() {
		return newlyCreated;
	}

	private void setNewlyCreated(boolean newlyCreated) {
		this.newlyCreated = newlyCreated;
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public boolean isModified() {
		return modified;
	}

	private void setModified(boolean modified) {
		this.modified = modified;
	}

	private Dictionary<String, Object> getAttributes() {
		return attributes;
	}

	private void setAttributes(Dictionary<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
	
	
	
}
