package com.ysy.ysy_im.entities;

/**
 * @author Stay
 * @version create time：Mar 15, 2015 9:15:16 PM
 */
public class Profile {
	private String _id;
	private String userId;
	private String name;
	private String description;
	private User user;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "name:" + name + ",user:" + user.toString();
	}

}
