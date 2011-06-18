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
import com.sgg.hibernate.ContactInfo;
import com.sgg.util.SessionFactoryUtil;

/**
 * @author scottgordon
 *
 */
public class ContactInfoAccessImpl implements ContactInfoAccess {
	final static Logger logger = LoggerFactory.getLogger(ContactInfoAccessImpl.class);
	
	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.ContactInfoAccess#addContactInfo(com.sgg.hibernate.ContactInfo, com.sgg.hibernate.Account)
	 */

	private ContactInfo addContactInfo(ContactInfo contactinfo, Account account) {
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		account.setAccountContactInfoId(contactinfo.getId());
		account.setUpdateDate(new Date());
		contactinfo.setCreateDate(new Date());
		contactinfo.setActive(true);
		contactinfo.setDeleted(false);
		
	    try {
	      tx = session.beginTransaction();
	      session.save(contactinfo);
	      session.save(account);
	      tx.commit();
	     
	      return contactinfo;
	      
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
	 * @see com.sgg.dbaccess.ContactInfoAccess#deleteContactInfo(com.sgg.hibernate.ContactInfo)
	 */
	@Override
	public boolean deleteContactInfo(ContactInfo contactinfo, boolean permanent) {
	
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
 
		if(permanent)
		{
			session.delete(contactinfo);
		}
		else
		{
			contactinfo.setActive(false);
			contactinfo.setDeleted(true);
			contactinfo.setDeleteDate(new Date());
			session.save(contactinfo);
		}
		
        tx.commit();
		
		// TODO Return a record deletion confirmation number
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.ContactInfoAccess#findContactInfoById(int)
	 */
	private ContactInfo findContactInfoById(int id) {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    Query	hqry	=	null;
	    String		hql		=		"from ContactInfo where id = :id";
	    ContactInfo returnContactInfo	=	null;
	    Iterator 	i	=	null;
	    	
	    try {
	      logger.debug("Finding Contact Info by ID: " + id);
	      tx = session.beginTransaction();	      
	      hqry = session.createQuery(hql);
	      hqry.setInteger("id", id);
	      i = hqry.list().iterator();
	      if(i.hasNext())
	      {
	    	  returnContactInfo = (ContactInfo) i.next();
	    	  logger.debug("Contact Info! ID: " + returnContactInfo.getId());
	      } else
	      {
	    	  returnContactInfo = null;
	    	  logger.debug("Contact Info NOT Found! Account ID: " + id);
	      }
	    	  
	      tx.commit();
	      
	    
	      return returnContactInfo;
	    
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
	 * @see com.sgg.dbaccess.ContactInfoAccess#updateContactInfo(com.sgg.hibernate.ContactInfo)
	 */
	@Override
	public ContactInfo updateContactInfo(ContactInfo contactinfo) {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    
	    contactinfo.setUpdateDate(new Date());
	    
	    try {
	      tx = session.beginTransaction();
	      session.update(contactinfo);
	      tx.commit();
	      return contactinfo;
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
	public ContactInfo createContactInfo(ContactInfo contactInfo) {
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		contactInfo.setCreateDate(new Date());
		contactInfo.setActive(true);
		contactInfo.setDeleted(false);
		
	    try {
	      tx = session.beginTransaction();
	      session.save(contactInfo);
	      tx.commit();
	     
	      return contactInfo;
	      
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

	@Override
	public List<ContactInfo> retrieveContactInfo(
			ContactInfoSearchCriteria contactInfoSearch) {
		List<ContactInfo> returnContact = new ArrayList<ContactInfo>();
		
		if(contactInfoSearch.getId() > 0) {
			returnContact.add(findContactInfoById(contactInfoSearch.getId()));
			return returnContact;
		}
		
		
		
		return null;
	}

}
