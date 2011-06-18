/**
 * 
 */
package com.sgg.dbaccess;

import java.util.List;

import com.sgg.hibernate.LinkInfo;

/**
 * @author scottgordon
 *
 */
public interface LinkInfoAccess {
	
	LinkInfo createLinkInfo(LinkInfo linkInfo);
	
	List<LinkInfo> retrieveLinkInfo(LinkInfoSearchCriteria linkSearch);
	
	List<LinkInfo> findAllLinkInfo();
	
	LinkInfo updateLinkInfo(LinkInfo linkInfo);
	
	boolean deleteLinkInfo(LinkInfo linkinfo,boolean permanent);
	
}
