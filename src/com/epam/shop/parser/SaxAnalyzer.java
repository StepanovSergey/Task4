package com.epam.shop.parser;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * This class provides SAX handler for products of shop
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class SaxAnalyzer extends SAnalyzer implements ContentHandler {

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
	String data = new String(ch, start, length);
	characters(data);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
	endElement(qName);
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
