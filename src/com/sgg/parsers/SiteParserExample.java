/**
 * 
 */
package com.sgg.parsers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgg.dbaccess.LinkInfoAccess;
import com.sgg.dbaccess.LinkInfoAccessImpl;
import com.sgg.dbaccess.LinkInfoSearchCriteria;
import com.sgg.hibernate.LinkInfo;

/**
 * @author scottgordon
 * 
 */
public class SiteParserExample {

	/**
	 * This will take urls from a database and then search them for links Then
	 * it will add any new found links to the database. If you run it over and
	 * over the data will get exponentially bigger
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(SiteParserExample.class);
		
		SiteParser sp = new SiteParser("");
		List<LinkInfo> links = new ArrayList();
		List<LinkInfo> linksToParse = new ArrayList();
		LinkInfo linkToAdd = null;
		LinkInfo link;
		LinkInfoAccess la = new LinkInfoAccessImpl();
		LinkInfoSearchCriteria linkSearch = new LinkInfoSearchCriteria();
		int linkCount = 0;

		linksToParse = la.findAllLinkInfo();

		Iterator<LinkInfo> j = linksToParse.iterator();

		while (j.hasNext()) {
			link = (LinkInfo) j.next();
			if (link!=null && (link.getCrawl() != null && link.getCrawl())) {
				sp = new SiteParser(link.getLongUrl());

				sp.build();

				links = sp.getLinks();

				Iterator<LinkInfo> i = links.iterator();

				while (i.hasNext()) {
					
					linkToAdd = i.next();
					linkToAdd.setParentId(link.getId());
					
					//Change this to true if added links should be crawled the next cycle
					linkToAdd.setCrawl(true);
					
					linkSearch.setLongURL(linkToAdd.getLongUrl());
					if (la.retrieveLinkInfo(linkSearch) != null
							&& la.retrieveLinkInfo(linkSearch).size() == 0) {
						log.info("Adding Link: "
								+ linkToAdd.getLongUrl() + " Description: "
								+ linkToAdd.getDescription());
						la.createLinkInfo(linkToAdd);
						linkCount++;
					}
				}
			}
		}
		log.info("DONE! Added: " + linkCount + " Links!");

	}

}
