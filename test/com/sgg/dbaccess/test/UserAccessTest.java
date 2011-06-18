/**
 * 
 */
package com.sgg.dbaccess.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sgg.dbaccess.UserAccess;
import com.sgg.dbaccess.UserAccessImpl;
import com.sgg.dbaccess.UserSearchCriteria;
import com.sgg.hibernate.User;

/**
 * @author scottgordon
 *
 */
public class UserAccessTest extends TestCase {
	private UserAccess ua  			= 		new UserAccessImpl();


	/**
	 * Test method for {@link com.sgg.dbaccess.UserAccessImpl#getAllUsers()}.
	 */
	@Test
	public final void testRetrieveUsers() {
		/* 
		 * Setup
		 */
		
		User user1 = new User();
		
		user1.setUserName("Scottyg66");
		user1.setUserEmail("scottyg66@hotmail.com");
		user1.setUserPassword("password");
		
		ua.createUser(user1);
		
		User user2 = new User();
		
		user2.setUserName("simonecal12");
		user2.setUserEmail("simonecal12@aol.com");
		user2.setUserPassword("abc");
		
		ua.createUser(user2);
		
		/* 
		 * Test
		 */	

		//Search By ID
		UserSearchCriteria userSearch = new UserSearchCriteria();
		userSearch.setId(user1.getId());
		
		assertTrue(ua.retrieveUsers(userSearch).size() > 0);
		
		//Search By User Name and password
		userSearch = new UserSearchCriteria();
		userSearch.setUsername(user1.getUserName());
		userSearch.setPassword(user1.getUserPassword());		
		
		assertTrue(ua.retrieveUsers(userSearch).size() > 0);
		
		//Search By E-mail
		userSearch = new UserSearchCriteria();
		userSearch.setEmail(user1.getUserEmail());
		
		assertTrue(ua.retrieveUsers(userSearch).size() > 0);
		
		/* 
		 * Cleanup
		 */
		
		ua.deleteUser(user1, true);
		ua.deleteUser(user2, true);
		
	}

	/**
	 * Test method for {@link com.sgg.dbaccess.UserAccessImpl#createUser(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testCreateUser() {
		/* 
		 * Setup
		 */
		
		User user1 = new User();
		
		user1.setUserName("Scottyg66");
		user1.setUserEmail("scottyg66@hotmail.com");
		user1.setUserPassword("password");
		
		/*
		 * Test
		 */
		
		assertNotNull(ua.createUser(user1));
	
		/*
		 * Cleanup
		 */
		ua.deleteUser(user1, true);
		
	}

	/**
	 * Test method for {@link com.sgg.dbaccess.UserAccessImpl#deleteUser(int)}.
	 */
	@Test
	public final void testDeleteUser() {
		/* 
		 * Setup
		 */
		
		User user1 = new User();
		
		user1.setUserName("Scottyg66");
		user1.setUserEmail("scottyg66@hotmail.com");
		user1.setUserPassword("password");
		
		user1 = ua.createUser(user1);
		
		/*
		 * Test
		 */
		
		int id = user1.getId();
		
		assertTrue(ua.deleteUser(user1, true));
				
	
		/*
		 * Cleanup
		 */

}

	/**
	 * Test method for {@link com.sgg.dbaccess.UserAccessImpl#updateUser(com.sgg.hibernate.User)}.
	 */
	@Test
	public final void testUpdateUser() {
		/* 
		 * Setup
		 */
		
		User user1 = new User();
		
		user1.setUserName("Scottyg66");
		user1.setUserEmail("scottyg66@hotmail.com");
		user1.setUserPassword("password");
		
		ua.createUser(user1);
		
		
		/* 
		 * Test
		 */	
		
		user1.setUserPassword("newpass");

		user1 = ua.updateUser(user1);
		
		assertTrue(user1.getUserPassword().equals("newpass"));
		
		/* 
		 * Cleanup
		 */
		
		ua.deleteUser(user1, true);		
		
	}	

}
