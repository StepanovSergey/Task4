package com.epam.shop.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.shop.model.Category;

/**
 * This class provides SAX parser initialisation and parsing XML file
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class MySaxParser implements IParser {
    private static Logger logger = Logger.getLogger(MySaxParser.class);
    private static final String SAX_PARSER_CLASS = "com.sun.org.apache.xerces.internal.parsers.SAXParser";

    @Override
    public List<Category> parse(String XMLName) {
	List<Category> categoryList = new ArrayList<Category>();
	try {
	    XMLReader reader = XMLReaderFactory
		    .createXMLReader(SAX_PARSER_CLASS);
	    SaxAnalyzer analyzer = new SaxAnalyzer();
	    reader.setContentHandler(analyzer);
	    reader.parse(XMLName);
	    categoryList = analyzer.getCategoryList();
	} catch (SAXException | IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return categoryList;
    }
}
