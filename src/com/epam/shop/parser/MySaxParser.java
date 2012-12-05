package com.epam.shop.parser;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
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
public final class MySaxParser implements IParser {
    private static final Logger logger = Logger.getLogger(MySaxParser.class);
    private static final MySaxParser instance = new MySaxParser();

    private MySaxParser() {
    }

    @Override
    public List<Category> parse(String XMLName) {
	List<Category> categoryList = null;
	try {
	    XMLReader reader = XMLReaderFactory.createXMLReader();
	    SaxAnalyzer analyzer = new SaxAnalyzer();
	    reader.setContentHandler(analyzer);
	    reader.parse(XMLName);
	    categoryList = analyzer.getCategoryList();
	} catch (SAXException | IOException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
	return categoryList;
    }

    /**
     * @return the saxParser
     */
    public static MySaxParser getInstance() {
        return instance;
    }
}
