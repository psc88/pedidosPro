/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.*;
import view.ViewCustomer;

/**
 *
 * @author veliz
 */
public class ControllerCustomer {
    public static void showView(){
        ControllerMain.hide();
        ControllerCustomer.show();
    }
    
    public static ViewCustomer view = new ViewCustomer();  
    
    public static void show() {
        view.setVisible(true);
        // Initial load
        getCustomersAndInsertInTable();
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    public static void returnView(){
        ControllerMain.show();
        hide();
    }
    
    public static Customer buildCustomerInstance() {
        Customer customer = new Customer();
        
        customer.setCustomerNumber(Integer.parseInt(view.getCustomerNumber().getText()));
        customer.setCustomerName(view.getCustomerName().getText());
        customer.setContactFirstName(view.getContactFirstName().getText());
        customer.setContactLastName(view.getContactLastName().getText());
        customer.setPhone(view.getPhone().getText());
        customer.setAddressLine1(view.getAddressLine1().getText());
        customer.setAddressLine2(view.getAddressLine2().getText());
        customer.setCity(view.getCity().getText());
        customer.setStateField(view.getStateField().getText());
        customer.setPostalCode(view.getPostalCode().getText());
        customer.setCountry(view.getCountry().getText());
        customer.setSalesRepEmployeeNumber(Integer.parseInt(view.getSalesRepEmployeeNumber().getText()));
        customer.setCreditLimit(Double.parseDouble(view.getCreditLimit().getText()));

        return customer;
    }
    
    public static void addCustomer() {
        Customer customer = buildCustomerInstance();
        DB.addCustomer(customer);
        
        clearFields();
        getCustomersAndInsertInTable();
    }
    
    public static void modifyCustomer() {
        Customer customer = buildCustomerInstance();
        DB.modifyCustomer(customer);
        
        clearFields();
        getCustomersAndInsertInTable();
    }
    
    public static void deleteCustomer() {
        int customerNumber = Integer.parseInt(view.getCustomerNumber().getText());
        DB.deleteCustomer(customerNumber);
        
        clearFields();
        getCustomersAndInsertInTable();
    }
    
    public static void getCustomersAndInsertInTable() {
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();      
        ArrayList<Customer> customers = DB.getCustomers();
        
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
    
    public static void loadFieldsOnSelectTableRow() {
        int selectedRow = view.getTable().getSelectedRow();
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();

        if (selectedRow >= 0) {
            String customerNumberValue = data.getValueAt(selectedRow, 0).toString();
            String customerNameValue = data.getValueAt(selectedRow, 1).toString();
            String contactFirstNameValue = data.getValueAt(selectedRow, 2).toString();
            String contactLastNameValue = data.getValueAt(selectedRow, 3).toString();
            String phoneValue = data.getValueAt(selectedRow, 4).toString();
            String addressLine1Value = data.getValueAt(selectedRow, 5).toString();
            String addressLine2Value = data.getValueAt(selectedRow, 6) != null ? data.getValueAt(selectedRow, 6).toString() : "";
            String cityValue = data.getValueAt(selectedRow, 7) != null ? data.getValueAt(selectedRow, 7).toString() : "";
            String stateFieldValue = data.getValueAt(selectedRow, 8) != null ? data.getValueAt(selectedRow, 8).toString() : "";
            String postalCodeValue = data.getValueAt(selectedRow, 9) != null ? data.getValueAt(selectedRow, 9).toString() : "";
            String countryValue = data.getValueAt(selectedRow, 10) != null ? data.getValueAt(selectedRow, 10).toString() : "";
            String salesRepEmployeeNumberValue = data.getValueAt(selectedRow, 11) != null ? data.getValueAt(selectedRow, 11).toString() : "";
            String creditLimitValue = data.getValueAt(selectedRow, 12) != null ? data.getValueAt(selectedRow, 12).toString() : "";

            view.getCustomerNumber().setText(customerNumberValue);
            view.getCustomerName().setText(customerNameValue);
            view.getContactFirstName().setText(contactFirstNameValue);
            view.getContactLastName().setText(contactLastNameValue);
            view.getPhone().setText(phoneValue);
            view.getAddressLine1().setText(addressLine1Value);
            view.getAddressLine2().setText(addressLine2Value);
            view.getCity().setText(cityValue);
            view.getStateField().setText(stateFieldValue);
            view.getPostalCode().setText(postalCodeValue);
            view.getCountry().setText(countryValue);
            view.getSalesRepEmployeeNumber().setText(salesRepEmployeeNumberValue);
            view.getCreditLimit().setText(creditLimitValue);
        }
    }
    
    public static void clearFields() {
        view.getCustomerNumber().setText("");
        view.getCustomerName().setText("");
        view.getContactFirstName().setText("");
        view.getContactLastName().setText("");
        view.getPhone().setText("");
        view.getAddressLine1().setText("");
        view.getAddressLine2().setText("");
        view.getCity().setText("");
        view.getStateField().setText("");
        view.getPostalCode().setText("");
        view.getCountry().setText("");
        view.getSalesRepEmployeeNumber().setText("");
        view.getCreditLimit().setText("");
    }
}
