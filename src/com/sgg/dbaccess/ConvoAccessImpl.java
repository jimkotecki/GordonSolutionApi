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

import com.sgg.hibernate.Convo;
import com.sgg.hibernate.LinkInfo;
import com.sgg.util.SessionFactoryUtil;

/**
 * @author scottgordon
 *
 */
public class ConvoAccessImpl implements ConvoAccess {
	
	final static Logger logger = LoggerFactory.getLogger(ConvoAccessImpl.class);

	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.ConvoAccess#createConvo(com.sgg.hibernate.Convo)
	 */
	@Override
	public Convo createConvo(Convo convo) {
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		convo.setCreateDate(new Date());
		convo.setActive(true);
		convo.setDeleted(false);
		
	    try {
	      tx = session.beginTransaction();
	      session.save(convo);
	      tx.commit();
	     
	      return convo;
	      
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
	 * @see com.sgg.dbaccess.ConvoAccess#retrieveLinkInfo(com.sgg.dbaccess.ConvoSearchCriteria)
	 */
	@Override
	public List<Convo> retrieveConvo(ConvoSearchCriteria convoSearch) {
		List<Convo> returnConvo = new ArrayList<Convo>();
		
		if(convoSearch.getId() > 0) {
			returnConvo.add(findConvoById(convoSearch.getId()));
			return returnConvo;
		}
		
		if(convoSearch.getOriginalLinkId() > 0)
		{
			
			returnConvo.add(findConvoByOriginalLinkId(convoSearch.getOriginalLinkId()));
			return returnConvo;
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.ConvoAccess#findAllConvo()
	 */
	@Override
	public List<Convo> findAllConvo() {
		Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    Query	hqry	=	null;
	    String		hql		=		"from LinkInfo";
	    Convo 	returnConvo	=	null;
	    List convos = new ArrayList<Convo>();
	    	
	    try {
	      
	      tx = session.beginTransaction();	      
	      hqry = session.createQuery(hql);
	      convos = hqry.list();
	      tx.commit();
	      return convos;
	    
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
	 * @see com.sgg.dbaccess.ConvoAccess#updateConvo(com.sgg.hibernate.Convo)
	 */
	@Override
	public Convo updateConvo(Convo convo) {
		
		convo.setUpdateDate(new Date());
		
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    try {
	      tx = session.beginTransaction();
	      session.update(convo);
	      tx.commit();
	      return convo;
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
	 * @see com.sgg.dbaccess.ConvoAccess#deleteConvo(com.sgg.hibernate.Convo, boolean)
	 */
	@Override
	public boolean deleteConvo(Convo convo, boolean permanent) {
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		
		if(permanent)
		{
			session.delete(convo);
		}
		else
		{
			convo.setDeleted(true);
			convo.setDeleteDate(new Date());
			convo.setActive(false);
			session.save(convo);
		}
		
        tx.commit();
        
		return true;
	}
	
	private Convo findConvoById(int id)
	{
	Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    Query	hqry	=	null;
    String		hql		=		"from Convo where id = :id";
    Convo 	returnConvo	=	null;
    Iterator 	i	=	null;
    	
    try {
      
      tx = session.beginTransaction();	      
      hqry = session.createQuery(hql);
      hqry.setInteger("id", id);
      i = hqry.list().iterator();
      if(i.hasNext())
      {
    	  returnConvo = (Convo) i.next();
      } else
      {
    	  returnConvo = null;
      }
    	  
      tx.commit();
      
      return returnConvo;
    
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
	
	private Convo findConvoByOriginalLinkId(int originalLinkid)
	{
	Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    Query	hqry	=	null;
    String		hql		=		"from Convo where originalLinkId = :originalLinkId";
    Convo 	returnConvo	=	null;
    Iterator 	i	=	null;
    	
    try {
      
      tx = session.beginTransaction();	      
      hqry = session.createQuery(hql);
      hqry.setInteger("originalLinkId", originalLinkid);
      i = hqry.list().iterator();
      if(i.hasNext())
      {
    	  returnConvo = (Convo) i.next();
      } else
      {
    	  returnConvo = null;
      }
    	  
      tx.commit();
      
      return returnConvo;
    
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

}
