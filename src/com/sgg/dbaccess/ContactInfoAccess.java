/**
 * 
 */
package com.sgg.dbaccess;

import java.util.List;

import com.sgg.hibernate.Account;
import com.sgg.hibernate.ContactInfo;
import com.sgg.hibernate.ContactInfo;

/**
 * @author scottgordon
 *
 */
public interface ContactInfoAccess {
	
	ContactInfo createContactInfo(ContactInfo contactInfo);
	
	List<ContactInfo> retrieveContactInfo(ContactInfoSearchCriteria contactSearch);
	
	ContactInfo updateContactInfo(ContactInfo contactInfo);
	
	boolean deleteContactInfo(ContactInfo contactInfo, boolean permanent);

}
