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
        
        // First load
        getProductsAndInsertInTable();
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    
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
        
        clearFields();
        getProductsAndInsertInTable();
    }
    
    public static void modifyProduct() {
        Product product = buildProductInstance();
        DB.modifyProduct(product);
        
        clearFields();
        getProductsAndInsertInTable();
    }
    
    public static void deleteProduct() {
        String productCode = view.getProductCode().getText();
        DB.deleteProduct(productCode);
        
        clearFields();
        getProductsAndInsertInTable();
    }
    
    public static void getProductsAndInsertInTable() {
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
    
    public static void loadFieldsOnSelectTableRow() {
        int selectedRow = view.getTable().getSelectedRow();
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();

        if (selectedRow >= 0) {
            String productCodeValue = data.getValueAt(selectedRow, 0).toString();
            String productNameValue = data.getValueAt(selectedRow, 1).toString();
            String productLineValue = data.getValueAt(selectedRow, 2).toString();
            String productScaleValue = data.getValueAt(selectedRow, 3).toString();
            String productVendorValue = data.getValueAt(selectedRow, 4).toString();
            String productDescriptionValue = data.getValueAt(selectedRow, 5).toString();
            String quantityInStockValue = data.getValueAt(selectedRow, 6).toString();
            String buyPriceValue = data.getValueAt(selectedRow, 7).toString();
            String MSRPValue = data.getValueAt(selectedRow, 8).toString();

            view.getProductCode().setText(productCodeValue);
            view.getProductName().setText(productNameValue);
            view.getProductLine().setText(productLineValue);
            view.getProductScale().setText(productScaleValue);
            view.getProductVendor().setText(productVendorValue);
            view.getProductDescription().setText(productDescriptionValue);
            view.getQuantityInStock().setText(quantityInStockValue);
            view.getBuyPrice().setText(buyPriceValue);
            view.getMSRP().setText(MSRPValue);
        }
    }
    
    public static void clearFields() {
        view.getProductCode().setText("");
        view.getProductName().setText("");
        view.getProductLine().setText("");
        view.getProductScale().setText("");
        view.getProductVendor().setText("");
        view.getProductDescription().setText("");
        view.getQuantityInStock().setText("");
        view.getBuyPrice().setText("");
        view.getMSRP().setText("");
    }
}
