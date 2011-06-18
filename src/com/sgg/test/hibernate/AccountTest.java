/**
 * 
 */
package com.sgg.test.hibernate;

/**
 * @author scottgordon
 *
 */
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgg.util.SessionFactoryUtil;
import com.sgg.hibernate.Account;

public class AccountTest {

  final static Logger logger = LoggerFactory.getLogger(AccountTest.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    Account ScottGordon = new Account();
    ScottGordon.setAccountFirstName("Scott");
    ScottGordon.setAccountLastName("Gordon");
    ScottGordon.setAccountName("Gordon_Scott");

	Account TestUser = new Account();
	TestUser.setAccountFirstName("Test");
	TestUser.setAccountLastName("User");
	TestUser.setAccountName("User_Test");
	
	createAccount(ScottGordon);
	createAccount(TestUser);
	  
    // our instances have a primary key now:
    logger.debug("{}", ScottGordon);
    logger.debug("{}", TestUser);
    listAccount();
    deleteAccount(TestUser);
    listAccount();
    ScottGordon.setAccountName("Gordon_Scott_ChangedAccountName");
    updateAccount(ScottGordon);

  }

  private static void listAccount() {
    Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      List accounts = session.createQuery("select a from Account as a")
              .list();
      for (Iterator iter = accounts.iterator(); iter.hasNext();) {
        Account element = (Account) iter.next();
        logger.debug("{}", element);
      }
      tx.commit();
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
  }

  private static void deleteAccount(Account account) {
    Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      session.delete(account);
      tx.commit();
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
  }

  private static void createAccount(Account account) {
    Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      session.save(account);
      tx.commit();
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
  }

   private static void updateAccount(Account account) {
    Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      session.update(account);
      tx.commit();
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
  }
}
