/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.DB;
import model.Payment;
import view.ViewPayment;

/**
 *
 * @author veliz
 */
public class ControllerPayment {
    public static ViewPayment view = new ViewPayment();  
    
    public static void show() {
        view.setVisible(true);
        getPayments();
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    
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
    }
    
    public static void modifyPayment() {
        Payment payment = buildPaymentInstance();
        DB.modifyPayment(payment);
    }
    
    public static void deletePayment(String selectedProductCode) {
        DB.deletePayment(selectedProductCode);
    }
    
    public static void getPayments() {
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
}
