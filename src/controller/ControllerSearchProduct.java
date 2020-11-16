/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.DB;
import model.Product;
import view.ViewOrder;
import view.ViewSearchProduct;

/**
 *
 * @author veliz
 */
public class ControllerSearchProduct {
    public static ViewSearchProduct view = new ViewSearchProduct();  
    public static ViewOrder viewOrder;

    public static void show() {
        view.setVisible(true);
    };
    
    public static void hide() {
        view.setVisible(false);
    };

    // Used to update parent view fields 
    public static void setViewOrder(ViewOrder windowOrder) {
        viewOrder = windowOrder;
    }
            
    public static void searchProductAndInsertInTable() {
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();
        ArrayList<Product> products = DB.searchProduct(view.getProductCode().getText());
        
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
    
    public static void confirmSelectionAndClose() {
        viewOrder.getProductCode().setText(view.getProductCode().getText());
        viewOrder.getProductName().setText(view.getProductName().getText());
        viewOrder.getPrice().setText(view.getProductPrice().getText());
        viewOrder.getStock().setText(view.getStockPresent().getText());
        // Default quantity is 1
        viewOrder.getQuantity().setText("1");
        viewOrder.getSubtotal().setText(view.getProductPrice().getText());
        /////
        hide();
    }
    
    public static void cancelAndClose() {
        clearFieldsAndTable();
        hide();
    }
    
    public static void loadSelectedProductCode() {
        int selectedRow = view.getTable().getSelectedRow();
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();

        if (selectedRow >= 0) {
            String productCode = data.getValueAt(selectedRow, 0).toString();
            String productName = data.getValueAt(selectedRow, 1).toString();
            String productPrice = data.getValueAt(selectedRow, 7).toString();
            String stock = data.getValueAt(selectedRow, 6).toString();

            view.getProductCode().setText(productCode);
            view.getProductName().setText(productName);
            view.getProductPrice().setText(productPrice);
            view.getStockPresent().setText(stock);
        }
    }
    
    public static void clearFieldsAndTable() {
        // Clear table
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();
        data.setNumRows(0);

        // Clear fields
        view.getProductCode().setText("");
        view.getProductName().setText("");
        view.getProductPrice().setText("");
    }
}
