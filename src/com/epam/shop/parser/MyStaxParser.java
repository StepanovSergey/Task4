package com.epam.shop.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.shop.model.Category;

/**
 * This class provides STAX parser
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class MyStaxParser implements IParser {
    private static final Logger logger = Logger.getLogger(MyStaxParser.class);
    private static final MyStaxParser instance = new MyStaxParser();

    private MyStaxParser() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.epam.shop.parser.IParser#parse(java.lang.String)
     */
    @Override
    public List<Category> parse(String XMLName) {
	List<Category> categoryList = null;
	InputStream input;
	try {
	    input = new FileInputStream(XMLName);
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	    XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
	    StaxAnalyzer staxAnalyzer = new StaxAnalyzer();
	    categoryList = staxAnalyzer.process(reader);
	} catch (FileNotFoundException | XMLStreamException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
	return categoryList;
    }

    /**
     * @return the instance
     */
    public static MyStaxParser getInstance() {
	return instance;
    }
}
