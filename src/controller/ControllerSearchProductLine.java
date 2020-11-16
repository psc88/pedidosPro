/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.ProductLine;
import model.DB;
import view.ViewProduct;
import view.ViewSearchProductLine;

/**
 *
 * @author veliz
 */
public class ControllerSearchProductLine {
    public static ViewSearchProductLine view = new ViewSearchProductLine();  
    public static ViewProduct viewProduct;

    public static void show() {
        view.setVisible(true);
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    
    // Used to update parent view fields
    public static void setViewProduct(ViewProduct windowProduct) {
        viewProduct = windowProduct;
    }
            
    public static void searchProductLineAndInsertInTable() {
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();      
        ArrayList<ProductLine> productLines = DB.searchProductLine(view.getProductLineSearch().getText());
        
        // Clear table
        data.setNumRows(0);

        for (int i = 0; i < productLines.size(); i++) {
            Object[] row = new Object[4];

            row[0] = productLines.get(i).getProductLine();
            row[1] = productLines.get(i).getTextDescription();
            row[2] = productLines.get(i).getHtmlDescription();
            row[3] = productLines.get(i).getImage();

            data.addRow(row);
        }
    }
    
    public static void confirmSelectionAndClose() {
        viewProduct.getProductLine().setText(view.getProductLine().getText());
        hide();
    }
    
    public static void cancelAndClose() {
        clearFieldsAndTable();
        hide();
    }
    
    public static void loadSelectedProductLine() {
        int selectedRow = view.getTable().getSelectedRow();
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();

        if (selectedRow >= 0) {
            String productLineValue = data.getValueAt(selectedRow, 0).toString();

            view.getProductLine().setText(productLineValue);
        }
    }
    
    public static void clearFieldsAndTable() {
        // Clear table
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();
        data.setNumRows(0);

        // Clear fields
        view.getProductLineSearch().setText("");
        view.getProductLine().setText("");
    }
    
}
