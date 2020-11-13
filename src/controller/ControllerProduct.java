/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import model.*;
import view.*;

/**
 *
 * @author veliz
 */
public class ControllerProduct {
    public static ViewProduct view = new ViewProduct();  
    
    public static void show() {
        
        view.setVisible(true);
        getProducts();
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    
    // TODO: Fix instance issues when select a product from the list
    public static Product buildProductInstance() {
        Product product = new Product();
        
        product.setProductCode(view.getProductCode().getText());
        product.setProductName(view.getProductName().getText());
        product.setProductLine(view.getProductLine().getText());
        product.setProductScale(view.getProductScale().getText());
        product.setProductVendor(view.getProductVendor().getText());
        product.setProductDescription(view.getProductDescription().getText());
        product.setQuantityInStock(Integer.parseInt(view.getQuantityInStock().getText()));
        product.setBuyPrice(Double.parseDouble(view.getBuyPrice().getText()));
        product.setMSRP(Double.parseDouble(view.getMSRP().getText()));
        
        return product;
    }
    
    public static void addProduct() {
        Product product = buildProductInstance();
        DB.addProduct(product);
    }
    
    public static void modifyProduct(String selectedProductCode) {
        Product product = buildProductInstance();
        DB.modifyProduct(product, selectedProductCode);
    }
    
    public static void deleteProduct(String selectedProductCode) {
        DB.deleteProduct(selectedProductCode);
    }
    
    public static void getProducts() {
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();      
        ArrayList<Product> products = DB.getProducts();
        
        // Clear table
        data.setNumRows(0);

        for (int i = 0; i < products.size(); i++) {
            Object[] row = new Object[9];

            row[0] = products.get(i).getProductCode();
            row[1] = products.get(i).getProductName();
            row[2] = products.get(i).getProductLine();
            row[3] = products.get(i).getProductScale();
            row[4] = products.get(i).getProductVendor();
            row[5] = products.get(i).getProductDescription();
            row[6] = products.get(i).getQuantityInStock();
            row[7] = products.get(i).getBuyPrice();
            row[8] = products.get(i).getMSRP();

            data.addRow(row);
        }
    }
}
