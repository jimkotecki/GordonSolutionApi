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
import com.sgg.hibernate.User;
import com.sgg.util.SessionFactoryUtil;

/**
 * @author scottgordon
 *
 */
public class UserAccessImpl implements UserAccess {
	
	final static Logger logger = LoggerFactory.getLogger(UserAccessImpl.class);

	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.UserAccess#getAllUsers()
	 */
	private List getAllUsers() {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    String		hql		=		"from User where id >= 0";
	    
	    try {
	      
	      tx = session.beginTransaction();
	      List users = session.createQuery(hql).list();
	      tx.commit();
	      
	      return users;
	    
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
	 * @see com.sgg.dbaccess.UserAccess#findUsersByEmail(java.lang.String)
	 */
	private User findUserByEmail(String email) {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    Query	hqry	=	null;
	    String		hql		=		"from User where userEmail = :email";
	    User 	returnUser	=	null;
	    Iterator 	i	=	null;
	    	
	    try {
	      
	      tx = session.beginTransaction();	      
	      hqry = session.createQuery(hql);
	      hqry.setString("email", email);
	      i = hqry.list().iterator();
	      if(i.hasNext())
	      {
	    	  returnUser = (User) i.next();
	      } else
	      {
	    	  returnUser = null;
	      }
	    	  
	      tx.commit();
	      
	      return returnUser;
	    
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
	 * @see com.sgg.dbaccess.UserAccess#findUsersByEmail(java.lang.String)
	 */
	private User findUserByUsername(String username) {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    Query	hqry	=	null;
	    String		hql		=		"from User where userName = :username";
	    User 	returnUser	=	null;
	    Iterator 	i	=	null;
	    	
	    try {
	      
	      tx = session.beginTransaction();	      
	      hqry = session.createQuery(hql);
	      hqry.setString("username", username);
	      i = hqry.list().iterator();
	      if(i.hasNext())
	      {
	    	  returnUser = (User) i.next();
	      } else
	      {
	    	  returnUser = null;
	      }
	    	  
	      tx.commit();
	      
	      return returnUser;
	    
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
	 * @see com.sgg.dbaccess.UserAccess#findUsersByEmail(java.lang.String)
	 */
private User findUserById(int userId) {
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    Query	hqry	=	null;
	    String		hql		=		"from User where id = :id";
	    User 	returnUser	=	null;
	    Iterator 	i	=	null;
	    	
	    try {
	      
	      tx = session.beginTransaction();	      
	      hqry = session.createQuery(hql);
	      hqry.setInteger("id", userId);
	      i = hqry.list().iterator();
	      if(i.hasNext())
	      {
	    	  returnUser = (User) i.next();
	      } else
	      {
	    	  returnUser = null;
	      }
	    	  
	      tx.commit();
	      
	      return returnUser;
	    
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
	
	private User findUserByUsernamePassword(String username, String password)
	{
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    Query	hqry	=	null;
	    String		hql		=		"from User where userName = :username and userPassword = :password";
	    User 	returnUser	=	null;
	    Iterator 	i	=	null;
	    	
	    try {
	      
	      tx = session.beginTransaction();	      
	      hqry = session.createQuery(hql);
	      hqry.setString("username", username);
	      hqry.setString("password", password);
	      i = hqry.list().iterator();
	      if(i.hasNext())
	      {
	    	  returnUser = (User) i.next();
	      } else
	      {
	    	  returnUser = null;
	      }
	    	  
	      tx.commit();
	      
	      return returnUser;
	    
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
	 * @see com.sgg.dbaccess.UserAccess#createUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	private int createUser(String username, String password, String userEmail) {
	    User user = new User();

		user.setUserName(username);
		user.setUserPassword(password);
		user.setUserEmail(userEmail);
		
		user = createUser(user);
	
		if(user == null)
		{
			return -1;			
		}
		else
		{
			return user.getId();
		}
	}
	
	@Override
	public User createUser(User user) {
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setActive(true);
		user.setDeleted(false);

		try {
	      tx = session.beginTransaction();
	      session.save(user);
	      tx.commit();
	     
	      return user;
	      
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
	 * @see com.sgg.dbaccess.UserAccess#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(User user, boolean permanent) {
		
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		
		if(permanent)
		{
			session.delete(user);
		}
		else
		{
			user.setDeleted(true);
			user.setDeleteDate(new Date());
			user.setActive(false);
			session.save(user);
		}
		
        tx.commit();
        
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sgg.dbaccess.UserAccess#updateUser(com.sgg.hibernate.User)
	 */
	@Override
	public User updateUser(User user) {

		user.setUpdateDate(new Date());
		
		
	    Transaction tx = null;
	    Session session = SessionFactoryUtil.getInstance().getCurrentSession();
	    try {
	      tx = session.beginTransaction();
	      session.update(user);
	      tx.commit();
	      return user;
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
	public List<User> retrieveUsers(UserSearchCriteria userSearch) {
		List<User> returnUsers = new ArrayList<User>();
		
		if(userSearch.getId() > 0) {
			returnUsers.add(findUserById(userSearch.getId()));
			return returnUsers;
		} else if (userSearch.getUsername().length() > 0 && userSearch.getPassword().length() > 0) {
			returnUsers.add(findUserByUsernamePassword(userSearch.getUsername(), userSearch.getPassword()));
			return returnUsers;
		} else if (userSearch.getEmail().length() > 0) {
			returnUsers.add(findUserByEmail(userSearch.getEmail()));
			return returnUsers;
		}
		
		return null;
	}



}