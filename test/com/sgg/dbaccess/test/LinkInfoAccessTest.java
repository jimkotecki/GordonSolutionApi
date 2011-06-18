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
import com.sgg.dbaccess.LinkInfoAccess;
import com.sgg.dbaccess.LinkInfoAccessImpl;
import com.sgg.dbaccess.LinkInfoSearchCriteria;
import com.sgg.hibernate.ContactInfo;
import com.sgg.hibernate.LinkInfo;


/**
 * @author scottgordon
 *
 */
public class LinkInfoAccessTest extends TestCase {
	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#addContactInfo(com.sgg.hibernate.ContactInfo, com.sgg.hibernate.Account)}.
	 */
	
	
	
	@Test
	public final void testCreateLinkInfo() {
		/*
		 * Setup
		 */
		LinkInfoAccess la = new LinkInfoAccessImpl();
		
		
		LinkInfo linkinfo = new LinkInfo();
	
		linkinfo.setLongUrl("http://www.google.com");
		linkinfo.setDescription("Google Main Web Site");
	
		linkinfo = la.createLinkInfo(linkinfo);
		
		/*
		 * Test
		 */
		//Search by Id
		LinkInfoSearchCriteria linkSearch = new LinkInfoSearchCriteria();
		linkSearch.setId(linkinfo.getId());
		
		assertTrue(la.retrieveLinkInfo(linkSearch).size() > 0);
		
		/*
		 * Cleanup
		 */
		
		la.deleteLinkInfo(linkinfo, true);
		
	}

	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#updateContactInfo(com.sgg.hibernate.ContactInfo)}.
	 */
	@Test
	public final void testUpdateLinkInfo() {
		/*
		 * Setup
		 */
		LinkInfoAccess la = new LinkInfoAccessImpl();
		
		
		LinkInfo linkinfo = new LinkInfo();
	
		linkinfo.setLongUrl("http://www.google.com");
		linkinfo.setDescription("Google Main Web Site");
	
		linkinfo = la.createLinkInfo(linkinfo);
		
		/*
		 * Test
		 */
		linkinfo.setInternalUrl("UpdatedURL");
		linkinfo = la.updateLinkInfo(linkinfo);
		
		assertTrue(linkinfo.getInternalUrl().equals("UpdatedURL"));
		
		/*
		 * Cleanup
		 */
		
		la.deleteLinkInfo(linkinfo, true);	
	
	}
	
	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#updateContactInfo(com.sgg.hibernate.ContactInfo)}.
	 */
	@Test
	public final void testRetrieveLinkInfo() {
		/*
		 * Setup
		 */
		LinkInfoAccess la = new LinkInfoAccessImpl();
		
		
		LinkInfo linkinfo = new LinkInfo();
	
		linkinfo.setLongUrl("http://www.google.com");
		linkinfo.setDescription("Google Main Web Site");
	
		linkinfo = la.createLinkInfo(linkinfo);
		
		/*
		 * Test
		 */
		//Search by Id
		LinkInfoSearchCriteria linkSearch = new LinkInfoSearchCriteria();
		linkSearch.setId(linkinfo.getId());
		
		assertTrue(la.retrieveLinkInfo(linkSearch).size() > 0);
		
		/*
		 * Cleanup
		 */
		
		la.deleteLinkInfo(linkinfo, true);	
	
	}
	
	/**
	 * Test method for {@link com.sgg.dbaccess.ContactInfoAccessImpl#updateContactInfo(com.sgg.hibernate.ContactInfo)}.
	 */
	@Test
	public final void testFindAllLinkInfo() {
		/*
		 * Setup
		 */
		LinkInfoAccess la = new LinkInfoAccessImpl();
		
		
		LinkInfo linkinfo = new LinkInfo();
	
		linkinfo.setLongUrl("http://www.google.com");
		linkinfo.setDescription("Google Main Web Site");
	
		linkinfo = la.createLinkInfo(linkinfo);
		
		/*
		 * Test
		 */

		
		assertTrue(la.findAllLinkInfo().size() > 0);
		
		/*
		 * Cleanup
		 */
		
		la.deleteLinkInfo(linkinfo, true);	
	
	}
	


}
