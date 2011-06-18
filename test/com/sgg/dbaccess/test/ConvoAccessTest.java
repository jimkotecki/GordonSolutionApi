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

import com.sgg.dbaccess.ContactInfoSearchCriteria;
import com.sgg.dbaccess.ConvoAccess;
import com.sgg.dbaccess.ConvoAccessImpl;
import com.sgg.dbaccess.ConvoSearchCriteria;
import com.sgg.dbaccess.LinkInfoAccess;
import com.sgg.dbaccess.LinkInfoAccessImpl;
import com.sgg.dbaccess.LinkInfoSearchCriteria;
import com.sgg.hibernate.ContactInfo;
import com.sgg.hibernate.Convo;
import com.sgg.hibernate.LinkInfo;


/**
 * @author scottgordon
 *
 */
public class ConvoAccessTest extends TestCase {
	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#addContactInfo(com.sgg.hibernate.ContactInfo, com.sgg.hibernate.Account)}.
	 */
	
	
	
	@Test
	public final void testCreateConvo() {
		/*
		 * Setup
		 */
		ConvoAccess ca = new ConvoAccessImpl();
		
		
		Convo convo = new Convo();

		convo.setConvoType("TEST");
		convo.setCreatorUserId(0);
		convo.setShortDescription("Short Description of Conversation");
		convo.setLongDescription("Really Long Description of the conversation.  You can also even use HTML if you wanted here <p>Like This </p>");
		convo = ca.createConvo(convo);
		
		/*
		 * Test
		 */
		//Search by Id
		ConvoSearchCriteria convoSearch = new ConvoSearchCriteria();
		convoSearch.setId(convo.getId());
		
		assertTrue(ca.retrieveConvo(convoSearch).size() > 0);
		
		/*
		 * Cleanup
		 */
		
		ca.deleteConvo(convo, true);
		
	}

	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#updateContactInfo(com.sgg.hibernate.ContactInfo)}.
	 */
	@Test
	public final void testUpdateConvo() {
		/*
		 * Setup
		 */
		ConvoAccess ca = new ConvoAccessImpl();
		
		
		Convo convo = new Convo();

		convo.setConvoType("TEST");
		convo.setCreatorUserId(0);
		convo.setShortDescription("Short Description of Conversation");
		convo.setLongDescription("Really Long Description of the conversation.  You can also even use HTML if you wanted here <p>Like This </p>");
		convo = ca.createConvo(convo);
		
		/*
		 * Test
		 */
		//Search by Id
		ConvoSearchCriteria convoSearch = new ConvoSearchCriteria();
		convoSearch.setId(convo.getId());
		
		convo = ca.retrieveConvo(convoSearch).get(0);
		
		convo.setShortDescription("I changed the short description!");
		
		convo = ca.updateConvo(convo);
		
		convo = ca.retrieveConvo(convoSearch).get(0);
		
		assertTrue(convo.getShortDescription().equals("I changed the short description!"));
		
		
		/*
		 * Cleanup
		 */
		
		ca.deleteConvo(convo, true);
	
	}
	
	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#updateContactInfo(com.sgg.hibernate.ContactInfo)}.
	 */
	@Test
	public final void testRetrieveConvo() {
		/*
		 * Setup
		 */
		ConvoAccess ca = new ConvoAccessImpl();
		
		
		Convo convo = new Convo();

		convo.setConvoType("TEST");
		convo.setCreatorUserId(0);
		convo.setShortDescription("Short Description of Conversation");
		convo.setLongDescription("Really Long Description of the conversation.  You can also even use HTML if you wanted here <p>Like This </p>");
		convo = ca.createConvo(convo);
		
		/*
		 * Test
		 */
		//Search by Id
		ConvoSearchCriteria convoSearch = new ConvoSearchCriteria();
		convoSearch.setId(convo.getId());
		
		convo = ca.retrieveConvo(convoSearch).get(0);
		
		convo.setShortDescription("I changed the short description!");
		
		convo = ca.updateConvo(convo);
		
		convo = ca.retrieveConvo(convoSearch).get(0);
		
		assertTrue(convo.getShortDescription().equals("I changed the short description!"));
		
		
		/*
		 * Cleanup
		 */
		
		ca.deleteConvo(convo, true);	
	
	}

}
