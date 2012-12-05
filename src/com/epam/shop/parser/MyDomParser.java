package com.epam.shop.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.epam.shop.model.Category;

/**
 * This class provides dom parser
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class MyDomParser implements IParser {
    private static final Logger logger = Logger.getLogger(MyDomParser.class);

    @Override
    public List<Category> parse(String XMLName) {
	List<Category> categoryList = new ArrayList<Category>();
	try {
	    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		    .newInstance();
	    DocumentBuilder documentBuilder;
	    documentBuilder = documentBuilderFactory.newDocumentBuilder();
	    Document document = documentBuilder.parse(XMLName);
	    Element root = document.getDocumentElement();
	    DomAnalyzer domAnalyzer = new DomAnalyzer();
	    categoryList = domAnalyzer.listBuilder(root);
	} catch (ParserConfigurationException | SAXException | IOException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
	return categoryList;
    }

}
