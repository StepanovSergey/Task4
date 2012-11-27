package com.epam.shop.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import by.stepanov.sergey.dateconverter.DateConverter;

import com.epam.shop.model.Category;
import com.epam.shop.model.Product;
import com.epam.shop.model.Subcategory;

/**
 * This class provides DOM parser analyzer
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class DomAnalyzer {
    private Product product;
    private Subcategory subcategory;
    private Category category;
    private List<Category> categoryList = new ArrayList<Category>();

    public List<Category> listBuilder(Element root) {
	NodeList categoryNodes = root
		.getElementsByTagName(Constants.CATEGORY_TAG);
	for (int i = 0; i < categoryNodes.getLength(); i++) {
	    category = new Category();
	    Element categoryElement = (Element) categoryNodes.item(i);
	    category.setName(categoryElement
		    .getAttribute(Constants.NAME_ATTRIBUTE));

	    NodeList subcategoryNodes = categoryElement
		    .getElementsByTagName(Constants.SUBCATEGORY_TAG);
	    for (int j = 0; j < subcategoryNodes.getLength(); j++) {
		subcategory = new Subcategory();
		Element subcategoryElement = (Element) subcategoryNodes.item(j);
		subcategory.setName(subcategoryElement
			.getAttribute(Constants.NAME_ATTRIBUTE));

		NodeList productNodes = subcategoryElement
			.getElementsByTagName(Constants.PRODUCT_TAG);
		for (int k = 0; k < productNodes.getLength(); k++) {
		    product = new Product();
		    Element productElement = (Element) productNodes.item(k);
		    product.setProducer(getChildElementValue(productElement,
			    Constants.PRODUCER_TAG));
		    product.setModel(getChildElementValue(productElement,
			    Constants.MODEL_TAG));
		    String dateOfIssueString = getChildElementValue(
			    productElement, Constants.DATE_OF_ISSUE_TAG);
		    Date dateOfIssue = DateConverter.convertToDateUtil(
			    dateOfIssueString, Constants.DATE_PATTERN);
		    product.setDateOfIssue(dateOfIssue);
		    product.setColor(getChildElementValue(productElement,
			    Constants.COLOR_TAG));
		    Element notInStock = getChildElement(productElement,
			    Constants.NOT_IN_STOCK_TAG);
		    if (notInStock != null) {
			product.setNotInStock(true);
		    } else {
			String priceString = getChildElementValue(
				productElement, Constants.PRICE_TAG);
			product.setPrice(Float.parseFloat(priceString));
		    }
		    subcategory.getProductList().add(product);
		}
		category.getSubcategoryList().add(subcategory);
	    }
	    categoryList.add(category);
	}
	return categoryList;
    }

    private static String getChildElementValue(Element parent, String childName) {
	Element child = getChildElement(parent, childName);
	if (child != null) {
	    Node node = child.getFirstChild();
	    String value = node.getNodeValue();
	    return value;
	} else {
	    return null;
	}
    }

    private static Element getChildElement(Element parent, String childName) {
	NodeList nlist = parent.getElementsByTagName(childName);
	if (nlist.getLength() != 0) {
	    Element child = (Element) nlist.item(0);
	    return child;
	} else {
	    return null;
	}
    }
}
