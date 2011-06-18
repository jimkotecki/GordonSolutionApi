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

import com.sgg.hibernate.LinkInfo;
import com.sgg.hibernate.User;
import com.sgg.util.SessionFactoryUtil;

/**
 * @author scottgordon
 *
 */
public class LinkInfoAccessImpl implements LinkInfoAccess {
	
	final static Logger logger = LoggerFactory.getLogger(LinkInfoAccessImpl.class);
	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.LinkInfoAccess#createLinkInfo(com.sgg.hibernate.LinkInfo)
	 */
	@Override
	public LinkInfo createLinkInfo(LinkInfo linkInfo) {
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		linkInfo.setCreateDate(new Date());
		linkInfo.setActive(true);
		linkInfo.setDeleted(false);
		
	    try {
	      tx = session.beginTransaction();
	      session.save(linkInfo);
	      tx.commit();
	     
	      return linkInfo;
	      
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
	 * @see com.sgg.dbaccess.LinkInfoAccess#retrieveLinkInfo(com.sgg.dbaccess.LinkInfoSearchCriteria)
	 */
	@Override
	public List<LinkInfo> retrieveLinkInfo(LinkInfoSearchCriteria linkSearch) {
		List<LinkInfo> returnLinkInfo = new ArrayList<LinkInfo>();
		if(linkSearch == null)
		{
			returnLinkInfo = findAllLinkInfo();
			return returnLinkInfo;
		}
		
		if(linkSearch.getId() > 0) {
			returnLinkInfo.add(findLinkInfoById(linkSearch.getId()));
		}
		
		if(linkSearch.getLongURL().length() > 0)
		{
			returnLinkInfo.addAll(findLinkInfoByURL(linkSearch.getLongURL()));
		}
			
		return returnLinkInfo;

	}
	
	
	
	public List<LinkInfo> findAllLinkInfo()
	{
	Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    Query	hqry	=	null;
    String		hql		=		"from LinkInfo";
    LinkInfo 	returnLinkInfo	=	null;
    List links = new ArrayList<LinkInfo>();
    	
    try {
      
      tx = session.beginTransaction();	      
      hqry = session.createQuery(hql);
      links = hqry.list();
      tx.commit();
      return links;
    
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
	
	private LinkInfo findLinkInfoById(int id)
	{
	Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    Query	hqry	=	null;
    String		hql		=		"from LinkInfo where id = :id";
    LinkInfo 	returnLinkInfo	=	null;
    Iterator 	i	=	null;
    	
    try {
      
      tx = session.beginTransaction();	      
      hqry = session.createQuery(hql);
      hqry.setInteger("id", id);
      i = hqry.list().iterator();
      if(i.hasNext())
      {
    	  returnLinkInfo = (LinkInfo) i.next();
      } else
      {
    	  returnLinkInfo = null;
      }
    	  
      tx.commit();
      
      return returnLinkInfo;
    
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
	
	private List <LinkInfo> findLinkInfoByURL(String longUrl)
	{
	Transaction tx = null;
    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
    Query	hqry	=	null;
    String		hql		=		"from LinkInfo where longUrl = :longUrl";
    List<LinkInfo> links = new ArrayList<LinkInfo>();
    	
    try {
      
        tx = session.beginTransaction();	      
        hqry = session.createQuery(hql);
        hqry.setString("longUrl", longUrl);
        links = hqry.list();
        tx.commit();
        return links;
    
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
	 * @see com.sgg.dbaccess.LinkInfoAccess#updateLinkInfo(com.sgg.hibernate.LinkInfo)
	 */
	@Override
	public LinkInfo updateLinkInfo(LinkInfo linkInfo) {
		
		linkInfo.setUpdateDate(new Date());
		
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    try {
	      tx = session.beginTransaction();
	      session.update(linkInfo);
	      tx.commit();
	      return linkInfo;
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
	 * @see com.sgg.dbaccess.LinkInfoAccess#deleteLinkInfo(com.sgg.hibernate.LinkInfo)
	 */
	@Override
	public boolean deleteLinkInfo(LinkInfo linkinfo, boolean permanent) {
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		
		if(permanent)
		{
			session.delete(linkinfo);
		}
		else
		{
			linkinfo.setDeleted(true);
			linkinfo.setDeleteDate(new Date());
			linkinfo.setActive(false);
			session.save(linkinfo);
		}
		
        tx.commit();
        
		return true;
	}



}
