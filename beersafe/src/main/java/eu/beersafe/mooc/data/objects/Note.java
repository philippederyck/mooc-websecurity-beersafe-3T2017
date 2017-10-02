package eu.beersafe.mooc.data.objects;


import java.sql.SQLException;
import java.sql.Timestamp;

import eu.beersafe.mooc.data.access.BeerDAO;
import eu.beersafe.mooc.data.access.UserDAO;
import eu.beersafe.mooc.logger.Logger;

public class Note {
	
	private long id;
	private Timestamp created;
	private String title;
	private String content;
	private boolean publicNote;
	private long userid;
	private long beerid;
	
	public Note(long id, Timestamp created, String title, String content, boolean publicNote, long userid, long beerid) {
		this.setId(id);
		this.setCreated(created);
		this.setTitle(title);
		this.setContent(content);
		this.setPublicNote(publicNote);
		this.setUserid(userid);
		this.setBeerid(beerid);
	}
	
	public boolean isValid() {
		//Rudimentary validity check
		return (this.getTitle().length() > 0 && this.getContent().length() > 0 && this.userid >= 0 && this.beerid >= 0);
	}
	
	public Beer getBeer() {
		try {
			return (new BeerDAO()).findOneById((int)this.getBeerid());
		}
		catch(SQLException e) {
			Logger.error("Failed to retrieve Beer belonging to Note (beerid=" + this.getBeerid() + ")", e);
			return null;
		}
	}
	
	public User getUser() {
		try {
			return (new UserDAO()).findOneById((int)this.getUserid());
		}
		catch(SQLException e) {
			Logger.error("Failed to retrieve User belonging to Note (beerid=" + this.getUserid() + ")", e);
			return null;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isPublicNote() {
		return publicNote;
	}

	public void setPublicNote(boolean publicNote) {
		this.publicNote = publicNote;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getBeerid() {
		return beerid;
	}

	public void setBeerid(long beerid) {
		this.beerid = beerid;
	}
	
	
}
