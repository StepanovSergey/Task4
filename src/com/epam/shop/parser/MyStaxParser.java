package com.epam.shop.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.epam.shop.model.Category;

/**
 * @author Sergey
 * 
 */
public class MyStaxParser implements IParser {
    private static Logger logger = Logger.getLogger(MyStaxParser.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.epam.shop.parser.IParser#parse(java.lang.String)
     */
    @Override
    public List<Category> parse(String XMLName) {
	List<Category> categoryList = new ArrayList<Category>();
	InputStream input;
	try {
	    input = new FileInputStream(XMLName);
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	    XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
	    StaxAnalyzer staxAnalyzer = new StaxAnalyzer();
	    categoryList = staxAnalyzer.process(reader);
	} catch (FileNotFoundException | XMLStreamException e) {
	    //if (logger.getLevel().equals(Level.ERROR))
		logger.error(e.getMessage(), e);
	}
	return categoryList;
    }
}
