/**
 * 
 */
package com.sgg.dbaccess;

/**
 * @author scottgordon
 *
 */
public class AccountSearchCriteria {
	private int id;
	private String name;
	private int minId;
	private int maxId;
	
	/**
	 * @return the minId
	 */
	public int getMinId() {
		return minId;
	}

	/**
	 * @param minId the minId to set
	 */
	public void setMinId(int minId) {
		this.minId = minId;
	}

	/**
	 * @return the maxId
	 */
	public int getMaxId() {
		return maxId;
	}

	/**
	 * @param maxId the maxId to set
	 */
	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
