/**
 * 
 */
package com.sgg.dbaccess.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.sgg.dbaccess.AccountAccess;
import com.sgg.dbaccess.AccountAccessImpl;
import com.sgg.dbaccess.ContactInfoAccess;
import com.sgg.dbaccess.ContactInfoAccessImpl;
import com.sgg.dbaccess.ContactInfoSearchCriteria;
import com.sgg.dbaccess.UserAccess;
import com.sgg.dbaccess.UserAccessImpl;
import com.sgg.hibernate.Account;
import com.sgg.hibernate.ContactInfo;

/**
 * @author scottgordon
 *
 */
public class ContactInfoAccessTest extends TestCase {
	private List<Integer> contactIds		=		new ArrayList<Integer>();
	private ContactInfoAccess cia  			= 		new ContactInfoAccessImpl();
	private AccountAccess aa				=		new AccountAccessImpl();



	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#addContactInfo(com.sgg.hibernate.ContactInfo, com.sgg.hibernate.Account)}.
	 */
	@Test
	public final void testCreateContactInfo() {
		/*
		 * Setup
		 */
		ContactInfo contactinfo = new ContactInfo();
	
		contactinfo.setContactInfoCity("Crystal Lake");
		contactinfo.setContactInfoState("IL");
		contactinfo.setContactInfoZip("60014");
		contactinfo.setContactInfoEmail("Young@Scott.org");
		contactinfo.setContactInfoLine1("4716 Daniel Dr");
		contactinfo.setContactInfoPhone("(815) 455 - 5256");
	
		contactinfo = cia.createContactInfo(contactinfo);
		
		/*
		 * Test
		 */
		//Search by Id
		ContactInfoSearchCriteria contactSearch = new ContactInfoSearchCriteria();
		contactSearch.setId(contactinfo.getId());
		
		assertTrue(cia.retrieveContactInfo(contactSearch).size() > 0);
		
		/*
		 * Cleanup
		 */
		
		cia.deleteContactInfo(contactinfo, true);
		
	}

	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#updateContactInfo(com.sgg.hibernate.ContactInfo)}.
	 */
	@Test
	public final void testUpdateContactInfo() {
		/*
		 * Setup
		 */
		ContactInfo contactinfo = new ContactInfo();
	
		contactinfo.setContactInfoCity("Crystal Lake");
		contactinfo.setContactInfoState("IL");
		contactinfo.setContactInfoZip("60014");
		contactinfo.setContactInfoEmail("Young@Scott.org");
		contactinfo.setContactInfoLine1("4716 Daniel Dr");
		contactinfo.setContactInfoPhone("(815) 455 - 5256");
	
		contactinfo = cia.createContactInfo(contactinfo);
		
		/*
		 * Test
		 */
		//Find the contact info
		ContactInfoSearchCriteria contactSearch = new ContactInfoSearchCriteria();
		contactSearch.setId(contactinfo.getId());
		contactinfo = cia.retrieveContactInfo(contactSearch).get(0);
		
		//Update the Contact info
		contactinfo.setContactInfoCity("FAKE");
		contactinfo = cia.updateContactInfo(contactinfo);
		
		assertTrue("FAKE".equalsIgnoreCase(contactinfo.getContactInfoCity()));
		
		/*
		 * Cleanup
		 */
		
		cia.deleteContactInfo(contactinfo, true);		
	
	}
	
	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#updateContactInfo(com.sgg.hibernate.ContactInfo)}.
	 */
	@Test
	public final void testRetrieveContactInfo() {
		/*
		 * Setup
		 */
		ContactInfo contactinfo = new ContactInfo();
	
		contactinfo.setContactInfoCity("Crystal Lake");
		contactinfo.setContactInfoState("IL");
		contactinfo.setContactInfoZip("60014");
		contactinfo.setContactInfoEmail("Young@Scott.org");
		contactinfo.setContactInfoLine1("4716 Daniel Dr");
		contactinfo.setContactInfoPhone("(815) 455 - 5256");
	
		contactinfo = cia.createContactInfo(contactinfo);
		
		/*
		 * Test
		 */
		//Search By Id
		ContactInfoSearchCriteria contactSearch = new ContactInfoSearchCriteria();
		contactSearch.setId(contactinfo.getId());
		assertTrue(cia.retrieveContactInfo(contactSearch).size() > 0);
		
		
		
		//Update the Contact info
		contactinfo.setContactInfoCity("FAKE");
		contactinfo = cia.updateContactInfo(contactinfo);
		
		assertTrue("FAKE".equalsIgnoreCase(contactinfo.getContactInfoCity()));
		
		/*
		 * Cleanup
		 */
		
		cia.deleteContactInfo(contactinfo, true);		
	
	}
	

}
