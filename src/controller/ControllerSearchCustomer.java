/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Customer;
import model.DB;
import view.ViewOrder;
import view.ViewSearchCustomer;
import view.ViewPayment;

/**
 *
 * @author veliz
 */
public class ControllerSearchCustomer {
    public static ViewSearchCustomer view = new ViewSearchCustomer();  
    public static ViewPayment viewPayment;
    public static ViewOrder viewOrder;

    public static void show() {
        view.setVisible(true);
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    
    // Used to update parent view fields
    public static void setViewPayment(ViewPayment windowPayment) {
        viewPayment = windowPayment;
    }
    
    // Used to update parent view fields 
    public static void setViewOrder(ViewOrder windowOrder) {
        viewOrder = windowOrder;
    }
            
    public static void searchCustomerAndInsertInTable() {
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();
        ArrayList<Customer> customers = DB.searchCustomer(view.getCustomerName().getText());
        
        // Clear table
        data.setNumRows(0);

        for (int i = 0; i < customers.size(); i++) {
            Object[] row = new Object[13];

            row[0] = customers.get(i).getCustomerNumber();
            row[1] = customers.get(i).getCustomerName();
            row[2] = customers.get(i).getContactFirstName();
            row[3] = customers.get(i).getContactLastName();
            row[4] = customers.get(i).getPhone();
            row[5] = customers.get(i).getAddressLine1();
            row[6] = customers.get(i).getAddressLine2();
            row[7] = customers.get(i).getCity();
            row[8] = customers.get(i).getStateField();
            row[9] = customers.get(i).getPostalCode();
            row[10] = customers.get(i).getCountry();
            row[11] = customers.get(i).getSalesRepEmployeeNumber();
            row[12] = customers.get(i).getCreditLimit();

            data.addRow(row);
        }
    }
    
    public static void confirmSelectionAndClose() {
        if (viewPayment != null) {
            viewPayment.getCustomerNumber().setText(view.getCustomerNumber().getText());
        } else {
            viewOrder.getCustomer().setText(view.getCustomerNumber().getText());
        }
        
        hide();
    }
    
    public static void cancelAndClose() {
        clearFieldsAndTable();
        hide();
    }
    
    public static void loadSelectedCustomerNumber() {
        int selectedRow = view.getTable().getSelectedRow();
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();

        if (selectedRow >= 0) {
            String customerNumberValue = data.getValueAt(selectedRow, 0).toString();
            
            view.getCustomerNumber().setText(customerNumberValue);
        }
    }
    
    public static void clearFieldsAndTable() {
        // Clear table
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();
        data.setNumRows(0);

        // Clear fields
        view.getCustomerName().setText("");
        view.getCustomerNumber().setText("");
    }
}
