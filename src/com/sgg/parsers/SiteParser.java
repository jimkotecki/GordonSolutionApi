/**
 * 
 */
package com.sgg.parsers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;

import org.ccil.cowan.tagsoup.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.sgg.dbaccess.LinkInfoAccess;
import com.sgg.dbaccess.LinkInfoAccessImpl;
import com.sgg.hibernate.LinkInfo;

/**
 * @author scottgordon
 * 
 */
public class SiteParser {
	final static Logger log = LoggerFactory.getLogger(SiteParser.class);
	
	private String url;
	ArrayList<LinkInfo> links;
	
	public SiteParser(String urlToParse) {
		url = urlToParse;
		links = new ArrayList<LinkInfo>();
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the links
	 */
	public ArrayList<LinkInfo> getLinks() {
		return links;
	}

	public void build()
	{
		try {
			URL urlToParse = new URL(this.url);
			XMLReader reader = new Parser();
			reader.setFeature(Parser.namespacesFeature, false);
			reader.setFeature(Parser.namespacePrefixesFeature, false);

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();

			DOMResult result = new DOMResult();

			log.debug("Transforming URL : " + url);
			
			transformer.transform(
					new SAXSource(reader, new InputSource(urlToParse.openStream())),
					result);

			// here we go - an DOM built from abitrary HTML
			log.debug("Parsing Links from transformed URL");
			buildLinks(result.getNode(), urlToParse.toString());

		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		} catch (Exception e) {
			System.out.println("GeneralException: " + e);
		}
	}

	public void buildLinks(Node rootNode, String url) {
		Node attribute;
		String attributeName;
		String attributeValue;
		LinkInfo link = new LinkInfo();
		LinkInfoAccess la = new LinkInfoAccessImpl();
		
		if (rootNode != null) {
			// Print the Node - We want the content only if this is the last
			// child node
			// if(rootNode.getChildNodes().getLength() < 1) {

			if (rootNode.getNodeName().trim().equalsIgnoreCase("a")) {
				NamedNodeMap attributeMap = null;

				attributeMap = rootNode.getAttributes();
				log.debug("******************************************************************************************");
				log.debug("Node Name: " + rootNode.getNodeName()
						+ " Node Value: " + rootNode.getNodeValue() + " Text Context: "+ rootNode.getTextContent());
				log.debug("******************************************************************************************");
				log.debug("Attributes: ");

				
				if (attributeMap != null) {
					for (int j = 0; j < attributeMap.getLength(); j++) {
						attribute = attributeMap.item(j);
						attributeName = attribute.toString().split("=")[0];
						attributeValue = attribute.toString().split("=")[1];
						if(attributeName.equalsIgnoreCase("href")) {
							link.setLinkType("AUTO");
							link.setDescription(rootNode.getTextContent());
							if(!attributeValue.contains("http")) {
								attributeValue = url + attributeValue;
							}
							link.setLongUrl(attributeValue.replace("\"", ""));
							links.add(link);
						}
					log.debug(attribute.toString() + " : " + attributeName + " : " + attributeValue);
					}
				} else {
					log.debug("No Atrributes Found");
				}
			}

			// Print all children
			for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
				buildLinks(rootNode.getChildNodes().item(i), url);
			}
			// Print the next Sibling
		//	printNodes(rootNode.getNextSibling());
		}
	}


}
