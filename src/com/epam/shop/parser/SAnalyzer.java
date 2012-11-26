package com.epam.shop.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.stepanov.sergey.dateconverter.DateConverter;

import com.epam.shop.model.Category;
import com.epam.shop.model.Product;
import com.epam.shop.model.Subcategory;

/**
 * This is common class for SAX and STAX analyzers.
 * 
 * @author Sergey
 * 
 */
public class SAnalyzer {
    private Product product;
    private Subcategory subcategory;
    private Category category;
    private String data;
    private String element;
    private List<Category> categoryList = new ArrayList<Category>();

    /**
     * Start element for SAX and STAX parser
     * 
     * @param tagName
     *            name of tag
     * @param attribute
     *            attribute name
     */
    public void startElement(String tagName, String attribute) {
	element = tagName;
	if (Constants.CATEGORY_TAG.equals(element)) {
	    category = new Category();
	    category.setName(attribute);
	}
	if (Constants.SUBCATEGORY_TAG.equals(element)) {
	    subcategory = new Subcategory();
	    subcategory.setName(attribute);
	}
	if (Constants.PRODUCT_TAG.equals(element)) {
	    product = new Product();
	}
    }

    /**
     * Read data in tag and set data to entity
     * 
     * @param dataInTag
     *            data inside tag
     */
    public void characters(String dataInTag) {
	data = dataInTag;
	if (Constants.PRODUCER_TAG.equals(element)) {
	    product.setProducer(data);
	}
	if (Constants.MODEL_TAG.equals(element)) {
	    product.setModel(data);
	}
	if (Constants.DATE_OF_ISSUE_TAG.equals(element)) {
	    Date dateOfIssue = DateConverter.convertToDateUtil(data,
		    Constants.DATE_PATTERN);
	    product.setDateOfIssue(dateOfIssue);
	}
	if (Constants.COLOR_TAG.equals(element)) {
	    product.setColor(data);
	}
	if (Constants.PRICE_TAG.equals(element)) {
	    product.setPrice(Float.parseFloat(data));
	}
    }

    /**
     * End element method
     * 
     * @param tagName
     *            name of tag
     */
    public void endElement(String tagName) {
	element = tagName;
	if (Constants.CATEGORY_TAG.equals(element)) {
	    categoryList.add(category);
	}
	if (Constants.SUBCATEGORY_TAG.equals(element)) {
	    category.getSubcategoryList().add(subcategory);
	}
	if (Constants.PRODUCT_TAG.equals(element)) {
	    subcategory.getProductList().add(product);
	}
	if (Constants.NOT_IN_STOCK_TAG.equals(element)) {
	    product.setNotInStock(true);
	}
	element = "";
    }

    /**
     * @return the categoryList
     */
    public List<Category> getCategoryList() {
	return categoryList;
    }
}
