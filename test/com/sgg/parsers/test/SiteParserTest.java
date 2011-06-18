/**
 * 
 */
package com.sgg.parsers.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sgg.parsers.SiteParser;

/**
 * @author scottgordon
 *
 */
public class SiteParserTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.sgg.parsers.SiteParser#SiteParser(java.lang.String)}.
	 */
	@Test
	public final void testSiteParser() {
		SiteParser parser = new SiteParser("http://www.google.com");
		
		parser.build();
		
		assertTrue(parser.getLinks().size() > 0);
		
	}

	/**
	 * Test method for {@link com.sgg.parsers.SiteParser#getUrl()}.
	 */
	@Test
	public final void testGetUrl() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.sgg.parsers.SiteParser#setUrl(java.lang.String)}.
	 */
	@Test
	public final void testSetUrl() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.sgg.parsers.SiteParser#getLinks()}.
	 */
	@Test
	public final void testGetLinks() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.sgg.parsers.SiteParser#buildLinks()}.
	 */
	@Test
	public final void testBuildLinks() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.sgg.parsers.SiteParser#printNodes(org.w3c.dom.Node)}.
	 */
	@Test
	public final void testPrintNodes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
