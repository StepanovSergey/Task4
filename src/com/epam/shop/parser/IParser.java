package com.epam.shop.parser;

import java.util.List;

import com.epam.shop.model.Category;

/**
 * This interface provides parser methods
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public interface IParser {
    /**
     * Parse XML file
     * 
     * @param XMLName
     *            name of XML file to parse
     * @return list of categories in XML file
     */
    List<Category> parse(String XMLName);
}
