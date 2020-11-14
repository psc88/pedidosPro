/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.DB;
import model.ProductLine;
import view.ViewProductLine;

/**
 *
 * @author veliz
 */
public class ControllerProductLine {
    public static ViewProductLine view = new ViewProductLine();  
    
    public static void show() {
        
        view.setVisible(true);
        getProductLines();
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    
    public static ProductLine buildProductLineInstance() {
        ProductLine productLine = new ProductLine();
        
        productLine.setProductLine(view.getProductLine().getText());
        productLine.setTextDescription(view.getTextDescription().getText());
        productLine.setHtmlDescription(view.getHtmlDescription().getText());
        productLine.setImage(view.getImage().getText());
        
        return productLine;
    }
    
    public static void addProductLine() {
        ProductLine productLine = buildProductLineInstance();
        DB.addProductLine(productLine);
    }
    
    public static void modifyProductLine() {
        ProductLine productLine = buildProductLineInstance();
        DB.modifyProductLine(productLine);
    }
    
    public static void deleteProductLine(String selectedProductCode) {
        DB.deleteProductLine(selectedProductCode);
    }
    
    public static void getProductLines() {
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();      
        ArrayList<ProductLine> productLines = DB.getProductLines();
        
        // Clear table
        data.setNumRows(0);

        for (int i = 0; i < productLines.size(); i++) {
            Object[] row = new Object[9];

            row[0] = productLines.get(i).getProductLine();
            row[1] = productLines.get(i).getTextDescription();
            row[2] = productLines.get(i).getHtmlDescription();
            row[3] = productLines.get(i).getImage();

            data.addRow(row);
        }
    }
}
