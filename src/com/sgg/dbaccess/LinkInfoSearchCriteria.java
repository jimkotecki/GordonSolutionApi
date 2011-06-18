/**
 * 
 */
package com.sgg.dbaccess;

/**
 * @author scottgordon
 *
 */
public class LinkInfoSearchCriteria {

	private int id;
	private String keyword;
	private int userId;
	private String longURL;
	/**
	 * @return the linkInfoId
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param linkInfoId the linkInfoId to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the longURL
	 */
	public String getLongURL() {
		return longURL;
	}
	/**
	 * @param longURL the longURL to set
	 */
	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}
	
	
}
