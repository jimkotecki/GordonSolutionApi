/**
 * 
 */
package com.sgg.dbaccess;

import java.util.List;

import com.sgg.hibernate.LinkInfo;
import com.sgg.hibernate.Ranking;

/**
 * @author scottgordon
 *
 */
public interface RankingAccess {
	
	Ranking createRanking(Ranking ranking);
	
	List<Ranking> retrieveRanking(RankingSearchCriteria rankingSearch);
	
	Ranking updateRanking(Ranking ranking);
	
	Ranking deleteRanking(Ranking ranking);

}
