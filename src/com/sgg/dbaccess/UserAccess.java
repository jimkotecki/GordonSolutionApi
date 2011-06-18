/**
 * 
 */
package com.sgg.dbaccess;

import java.util.List;

import com.sgg.hibernate.User;

/**
 * @author scottgordon
 *
 */
public interface UserAccess {
	User createUser(User user);
	
	List<User> retrieveUsers(UserSearchCriteria userSearch);
	
	User updateUser(User user);
	
	boolean deleteUser(User user, boolean permanent);
}
