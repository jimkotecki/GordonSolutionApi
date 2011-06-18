/**
 * 
 */
package com.sgg.dbaccess;

import java.util.List;

import com.sgg.hibernate.Convo;

/**
 * @author scottgordon
 *
 */
public interface ConvoAccess {

	Convo createConvo(Convo convo);
	
	List<Convo> retrieveConvo(ConvoSearchCriteria convoSearch);
	
	List<Convo> findAllConvo();
	
	Convo updateConvo(Convo convo);
	
	boolean deleteConvo(Convo convo,boolean permanent);
	
}
