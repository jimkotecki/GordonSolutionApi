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

import com.sgg.dbaccess.AccountAccess;
import com.sgg.dbaccess.AccountAccessImpl;
import com.sgg.dbaccess.AccountSearchCriteria;
import com.sgg.hibernate.Account;

/**
 * @author scottgordon
 *
 */
public class AccountAccessTest extends TestCase {
	/**
	 * Test method for {@link com.sgg.dbaccess.AccountAccessImpl#getAllAccounts()}.
	 */
	@Test
	public final void testRetrieveAccounts() {		
		/*
		 * Setup
		 */
		
		AccountAccess account  = new AccountAccessImpl();
		
		Account account1 = new Account();
		Account account2 = new Account();
		
		account1.setAccountFirstName("Scott");
		account1.setAccountLastName("Gordon");
		account1.setAccountName("Gordon_Scott");
		account1 = account.createAccount(account1);
		
		account2.setAccountFirstName("Simone");
		account2.setAccountLastName("Calcagno");
		account2.setAccountName("Gordon_Simone");
		account2 = account.createAccount(account2);
		
		/*
		 * Test
		 */
		//Search by ID
		AccountSearchCriteria accountSearch = new AccountSearchCriteria();
		accountSearch.setId(account1.getId());
		assertTrue(account.retrieveAccounts(accountSearch).size() > 0);	
		
		//Search by name
		accountSearch = new AccountSearchCriteria();
		accountSearch.setName(account2.getAccountLastName());
		assertTrue(account.retrieveAccounts(accountSearch).size() > 0);
		
		/*
		 * Cleanup
		 */
		account.deleteAccount(account1, true);
		account.deleteAccount(account2, true);
		
	}

	/**
	 * Test method for {@link com.sgg.dbaccess.AccountAccessImpl#createAccount(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testCreateAccount() {
		/*
		 * Setup
		 */
		AccountAccess account  = new AccountAccessImpl();
		
		Account account1 = new Account();
		
		account1.setAccountFirstName("Scott");
		account1.setAccountLastName("Gordon");
		account1.setAccountName("Gordon_Scott");
		account1 = account.createAccount(account1);
		
		/*
		 * Test
		 */
		
		assertNotNull(account1);	
		
		/*
		 * Cleanup
		 */
		account.deleteAccount(account1, true);

	}
	
	/**
	 * Test method for {@link com.sgg.dbaccess.AccountAccessImpl#updateAccount(com.sgg.hibernate.Account)}.
	 */
	@Test
	public final void testUpdateAccountAccount() {
		AccountAccess account  = new AccountAccessImpl();
		
		Account account1 = new Account();
		Account account2= new Account();
		
		String accountNameBefore;
		
		account1.setAccountFirstName("Scott");
		account1.setAccountLastName("Gordon");
		account1.setAccountName("Gordon_Scott");
		account1 = account.createAccount(account1);
		accountNameBefore = account1.getAccountName();
	
		account1.setAccountName("Updated_TEST");
		
		account1 = account.updateAccount(account1);
		
		assertFalse(account1.getAccountName().equals(accountNameBefore));
		
		
		account.deleteAccount(account1, true);
		
	}

	/**
	 * Test method for {@link com.sgg.dbaccess.AccountAccessImpl#deleteAccount(int)}.
	 */
	@Test
	public final void testDeleteAccount() {
		AccountAccess account  = new AccountAccessImpl();
		
		Account account1 = new Account();
		int id;

		
		account1.setAccountFirstName("Scott");
		account1.setAccountLastName("Gordon");
		account1.setAccountName("Gordon_Scott");
		account1 = account.createAccount(account1);

		id = account1.getId();
		
		assertTrue(account.deleteAccount(account1, true));	
		
	}
	
}
