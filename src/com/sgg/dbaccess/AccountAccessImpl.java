
/**
 * 
 */
package com.sgg.dbaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgg.hibernate.Account;
import com.sgg.util.SessionFactoryUtil;

/**
 * @author scottgordon
 *
 */
public class AccountAccessImpl implements AccountAccess {
	
	final static Logger logger = LoggerFactory.getLogger(AccountAccessImpl.class);

	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.AccountAccess#getAllAccounts()
	 */

	private List getAllAccounts() {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    String		hql		=		"from Account where id >= 0";
	    
	    try {
	    
	      logger.debug("Getting All Accounts...");	      
	      tx = session.beginTransaction();
	      List accounts = session.createQuery(hql).list();
	      tx.commit();
	      logger.debug("Accounts Retrieved...");
	      
	      return accounts;
	    
	    } catch (RuntimeException e) {
	    
	    	if (tx != null && tx.isActive()) {
	        
	    		try {
	    			// Second try catch as the rollback could fail as well
	    			tx.rollback();
	    		} catch (HibernateException e1) {
	    			logger.debug("Error rolling back transaction");
	    		}
	    		// throw again the first exception
	    		throw e;
	    	}
	    }
	    logger.debug("Failed to find any accounts!");
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.AccountAccess#findAccountById(int)
	 */
	private Account findAccountById(int id)
	{
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    Query	hqry	=	null;
	    String		hql		=		"from Account where id = :id";
	    Account 	returnAccount	=	null;
	    Iterator 	i	=	null;
	    	
	    try {
	      logger.debug("Finding Account by ID: " + id);
	      tx = session.beginTransaction();	      
	      hqry = session.createQuery(hql);
	      hqry.setInteger("id", id);
	      i = hqry.list().iterator();
	      if(i.hasNext())
	      {
	    	  returnAccount = (Account) i.next();
	    	  logger.debug("Account Found! ID: " + returnAccount.getId());
	      } else
	      {
	    	  returnAccount = null;
	    	  logger.debug("Account NOT Found! ID: " + id);
	      }
	    	  
	      tx.commit();
	      
	    
	      return returnAccount;
	    
	    } catch (RuntimeException e) {
	    
	    	if (tx != null && tx.isActive()) {
	        
	    		try {
	    			// Second try catch as the rollback could fail as well
	    			tx.rollback();
	    		} catch (HibernateException e1) {
	    			logger.debug("Error rolling back transaction");
	    		}
	    		// throw again the first exception
	    		throw e;
	    	}
	    }
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.AccountAccess#findAccountsByName(java.lang.String)
	 */
	private List<Account> findAccountsByName(String name) {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    String		hql		=		"from Account where id >= 0 and ((upper(accountFirstName) LIKE '%" + name.toUpperCase() + "%') " +
	    							"or (upper(accountLastName) LIKE '%" + name.toUpperCase() + "%') " +
	    							"or (upper(accountName) LIKE '%" + name.toUpperCase() + "%')) ";
	    try {
	    	
	    	
	      logger.debug("Finding accounts by name: " + name);
	      tx = session.beginTransaction();	      
	      List accounts = session.createQuery(hql).list();
	      tx.commit();
	      logger.debug("Accounts retrieved!  Count: " + accounts.size());
	      
	      return accounts;
	    
	    } catch (RuntimeException e) {
	    
	    	if (tx != null && tx.isActive()) {
	        
	    		try {
	    			// Second try catch as the rollback could fail as well
	    			tx.rollback();
	    		} catch (HibernateException e1) {
	    			logger.debug("Error rolling back transaction");
	    		}
	    		// throw again the first exception
	    		throw e;
	    	}
	    }
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.AccountAccess#createAccount(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Account createAccount(Account account) {
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;

		
	    if (account.getAccountName() == null || account.getAccountName().length() < 3) {
	    	account.setAccountName(account.getAccountLastName() + "_" + account.getAccountFirstName());
	    }
	    
	    account.setCreateDate(new Date());
	    account.setUpdateDate(new Date());
	    account.setDeleted(false);
	    account.setActive(true);
	    
	    try {
	      tx = session.beginTransaction();
	      
	      session.save(account);
	      tx.commit();
	     
	      return account;
	      
	    } catch (RuntimeException e) {
	      if (tx != null && tx.isActive()) {
	        try {
	// Second try catch as the rollback could fail as well
	          tx.rollback();
	          return null;
	        } catch (HibernateException e1) {
	          logger.debug("Error rolling back transaction");
	        }
	// throw again the first exception
	        throw e;
	      }
	    }
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.AccountAccess#updateAccount(com.sgg.hibernate.Account)
	 */
	@Override
	public Account updateAccount(Account account) {

		Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    try {
	      tx = session.beginTransaction();
	      session.update(account);
	      tx.commit();
	      return account;
	    } catch (RuntimeException e) {
	      if (tx != null && tx.isActive()) {
	        try {
	// Second try catch as the rollback could fail as well
	          tx.rollback();
	        } catch (HibernateException e1) {
	          logger.debug("Error rolling back transaction");
	        }
	// throw again the first exception
	        throw e;
	      }
	    }
		return null;
	  }

	@Override
	public List<Account> retrieveAccounts(AccountSearchCriteria accountSearch) {
		List<Account> returnAccounts = new ArrayList();
		
		if(accountSearch.getId() > 0) {
			if(returnAccounts.add(findAccountById(accountSearch.getId()))) {;
				return returnAccounts;
			} else
			{
				return null;
			}
		} else if (accountSearch.getName() != null) {
			return findAccountsByName(accountSearch.getName());
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteAccount(Account account, boolean permanent) {
		
		account = findAccountById(account.getId());
		if(account == null)
		{
			logger.debug("Account: " + account.getId() + " not found for deletion");
			return false;
		}		
		
		//Session must be retrieved after any other function calls that use a hibernate session object
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;

	try {
		
		
		tx = session.beginTransaction();
				
		if(permanent)
		{
			session.delete(account);
		}
		else
		{
			account.setActive(false);
			account.setDeleted(true);
			account.setDeleteDate(new Date());
			session.update(account);
		}
		
		tx.commit();
		
    } catch (RuntimeException e) {
	      if (tx != null && tx.isActive()) {
	        try {
	// Second try catch as the rollback could fail as well
	          tx.rollback();
	          return false;
	        } catch (HibernateException e1) {
	          logger.debug("Error rolling back transaction");
	        }
	// throw again the first exception
	        throw e;
	      }
	    }
		
		// TODO Return a record deletion confirmation number
		return true;
	}

}


