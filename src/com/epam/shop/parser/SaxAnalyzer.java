package com.epam.shop.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import by.stepanov.sergey.dateconverter.DateConverter;

import com.epam.shop.model.Category;
import com.epam.shop.model.Product;
import com.epam.shop.model.Subcategory;

/**
 * This class provides SAX handler for products of shop
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class SaxAnalyzer extends SAnalyzer implements ContentHandler {
    private Product product;
    private Subcategory subcategory;
    private Category category;
    private String data;
    private String element;
    private List<Category> categoryList = new ArrayList<Category>();

    @Override
    public void startDocument() {
	// At start of document nothing to do
    }

    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes attributes) {
	startElement(qName, attributes.getValue(0));
    }

    @Override
    public void characters(char[] ch, int start, int length) {
	data = new String(ch, start, length);
	if (Constants.producerTag.equals(element)) {
	    product.setProducer(data);
	}
	if (Constants.modelTag.equals(element)) {
	    product.setModel(data);
	}
	if (Constants.dateOfIssueTag.equals(element)) {
	    Date dateOfIssue = DateConverter.convertToDateUtil(data,
		    Constants.datePattern);
	    product.setDateOfIssue(dateOfIssue);
	}
	if (Constants.colorTag.equals(element)) {
	    product.setColor(data);
	}
	if (Constants.priceTag.equals(element)) {
	    product.setPrice(Float.parseFloat(data));
	}
	if (Constants.notInStockTag.equals(element)) {
	    product.setNotInStock(true);
	}
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
	element = qName;
	if (Constants.categoryTag.equals(element)) {
	    categoryList.add(category);
	}
	if (Constants.subcategoryTag.equals(element)) {
	    category.getSubcategoryList().add(subcategory);
	}
	if (Constants.productTag.equals(element)) {
	    subcategory.getProductList().add(product);
	}
	element = "";
    }

    @Override
    public void endDocument() {
	// Nothing to do at the end of the document
    }

    @Override
    public void endPrefixMapping(String arg0) throws SAXException {
	// Nothing to do
    }

    @Override
    public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
	    throws SAXException {
	// Nothing to do
    }

    @Override
    public void processingInstruction(String arg0, String arg1)
	    throws SAXException {
	// Nothing to do
    }

    @Override
    public void setDocumentLocator(Locator arg0) {
	// Nothing to do
    }

    @Override
    public void skippedEntity(String arg0) throws SAXException {
	// Nothing to do
    }

    @Override
    public void startPrefixMapping(String arg0, String arg1)
	    throws SAXException {
	// Nothing to do
    }
}
