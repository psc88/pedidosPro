/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Customer;
import model.DB;
import model.Payment;
import view.ViewSearchCustomer;
import view.ViewPayment;

/**
 *
 * @author veliz
 */
public class ControllerPayment {
    public static void showView(){
        ControllerMain.hide();
        ControllerPayment.show();
    }
    
    public static ViewPayment view = new ViewPayment();  
    
    public static void show() {
        view.setVisible(true);
        
        // First load
        getPaymentsAndInsertInTable();
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    public static void returnView(){
        ControllerMain.show();
        hide();
    }
    
    public static Payment buildPaymentInstance() {
        Payment payment = new Payment();
        
        payment.setCustomerNumber(Integer.parseInt(view.getCustomerNumber().getText()));
        payment.setCheckNumber(view.getCheckNumber().getText());
        payment.setPaymentDate(view.getPaymentDate().getText());
        payment.setAmount(Double.parseDouble(view.getAmount().getText()));
        
        return payment;
    }
    
    public static void addPayment() {
        Payment payment = buildPaymentInstance();
        DB.addPayment(payment);
        
        clearFields();
        getPaymentsAndInsertInTable();
        JOptionPane.showMessageDialog(null,"Pago exitoso","(=",JOptionPane.WARNING_MESSAGE);
        hide();
    }
    
    public static void modifyPayment() {
        Payment payment = buildPaymentInstance();
        DB.modifyPayment(payment);
        
        clearFields();
        getPaymentsAndInsertInTable();
    }
    
    public static void deletePayment() {
        String checkNumber = view.getCheckNumber().getText();
        DB.deletePayment(checkNumber);
        
        clearFields();
        getPaymentsAndInsertInTable();
    }
    
    public static void getPaymentsAndInsertInTable() {
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();      
        ArrayList<Payment> payments = DB.getPayments();
        
        // Clear table
        data.setNumRows(0);

        for (int i = 0; i < payments.size(); i++) {
            Object[] row = new Object[4];

            row[0] = payments.get(i).getCustomerNumber();
            row[1] = payments.get(i).getCheckNumber();
            row[2] = payments.get(i).getPaymentDate();
            row[3] = payments.get(i).getAmount();

            data.addRow(row);
        }
    }
    
    public static void loadFieldsOnSelectTableRow() {
        int selectedRow = view.getTable().getSelectedRow();
        DefaultTableModel data = (DefaultTableModel) view.getTable().getModel();

        if (selectedRow >= 0) {
            String customerNumberValue = data.getValueAt(selectedRow, 0).toString();
            String checkNumberValue = data.getValueAt(selectedRow, 1).toString();
            String paymentDateValue = data.getValueAt(selectedRow, 2).toString();
            String amountValue = data.getValueAt(selectedRow, 3).toString();

            view.getCustomerNumber().setText(customerNumberValue);
            view.getCheckNumber().setText(checkNumberValue);
            view.getPaymentDate().setText(paymentDateValue);
            view.getAmount().setText(amountValue);
        }
    }
    
    public static void clearFields() {
        view.getCustomerNumber().setText("");
        view.getCheckNumber().setText("");
        view.getPaymentDate().setText("");
        view.getAmount().setText("");
    }
    
    public static void searchCustomer() {
        ControllerSearchCustomer.setViewPayment(view);
        ControllerSearchCustomer.show();
    }
    
    public static void setCustomerNumberAndAmount(int customerNumber, double amount) {
        view.getCustomerNumber().setText(customerNumber + "");
        view.getAmount().setText(amount + "");
    }
}
