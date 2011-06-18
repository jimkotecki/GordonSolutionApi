/**
 * 
 */
package com.sgg.dbaccess;

import java.util.List;
import com.sgg.hibernate.Account;


/**
 * @author Scott Gordon
 *
 */
public interface AccountAccess {
	
	Account createAccount(Account account);
	
	List<Account> retrieveAccounts(AccountSearchCriteria accountSearch);
	
	Account updateAccount(Account account);
	
	boolean deleteAccount(Account account, boolean permanent);
	

}
