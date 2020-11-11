/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author veliz
 */

// REMOVE: THIS IS LIKE PRODUCT CATEGORY
public class ProductLine {
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private String image;

    public ProductLine() {
    }

    public ProductLine(String productLine) {
        this.productLine = productLine;
    }

    public ProductLine(String productLine, String textDescription, String htmlDescription, String image) {
        this.productLine = productLine;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
        this.image = image;
    }

    public String getProductLine() {
        return productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public String getImage() {
        return image;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public void setImage(String image) {
        this.image = image;
    }    
}
