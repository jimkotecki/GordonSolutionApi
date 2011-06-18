package com.sgg.dbaccess.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		Class[] testClasses = { AccountAccessTest.class, UserAccessTest.class, ContactInfoAccessTest.class, LinkInfoAccessTest.class, ConvoAccessTest.class};
		TestSuite suite = new TestSuite(testClasses);		
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}

}
