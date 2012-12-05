package com.epam.shop.parser;

import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.epam.shop.model.Category;

/**
 * This class provides analyzer for STAX parser
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class StaxAnalyzer extends AAnalyzer {
    /**
     * STAX Parser XML analyzer
     * 
     * @param reader
     *            reader of XML to parse
     * @return list of categories
     * @throws XMLStreamException
     *             if something wrong
     */
    public List<Category> process(XMLStreamReader reader)
	    throws XMLStreamException {
	while (reader.hasNext()) {
	    int type = reader.next();
	    switch (type) {
	    case XMLStreamConstants.START_ELEMENT:
		startElement(reader.getLocalName(), reader.getAttributeValue(0));
		break;

	    case XMLStreamConstants.CHARACTERS:
		characters(reader.getText());
		break;

	    case XMLStreamConstants.END_ELEMENT:
		endElement(reader.getLocalName());
		break;
	    }
	}
	return getCategoryList();
    }
}
