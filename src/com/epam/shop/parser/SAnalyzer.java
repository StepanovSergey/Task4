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
	if (Constants.categoryTag.equals(element)) {
	    category = new Category();
	    category.setName(attribute);
	}
	if (Constants.subcategoryTag.equals(element)) {
	    subcategory = new Subcategory();
	    subcategory.setName(attribute);
	}
	if (Constants.productTag.equals(element)) {
	    product = new Product();
	}
    }

    /**
     * Read data in tag and set data to entity
     * 
     * @param dataInTag
     *            data inside tag
     */
    public void data(String dataInTag) {
	data = dataInTag;
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

    /**
     * End element method
     * 
     * @param tagName
     *            name of tag
     */
    public void endElement(String tagName) {
	element = tagName;
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

    /**
     * @return the categoryList
     */
    public List<Category> getCategoryList() {
	return categoryList;
    }
}
