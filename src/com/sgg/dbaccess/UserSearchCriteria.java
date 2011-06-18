/**
 * 
 */
package com.sgg.dbaccess;

/**
 * @author scottgordon
 *
 */
public class UserSearchCriteria {
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param email
	 */
	public UserSearchCriteria() {
		super();
		this.id = 0;
		this.username = "";
		this.password = "";
		this.email = "";
	}
	int id;
	String username;
	String password;
	String email;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
