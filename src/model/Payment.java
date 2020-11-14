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

// REMOVE: MOUNT, DATE, METHOD
public class Payment {
    private int customerNumber;
    private String checkNumber;
    private String paymentDate;
    private double amount;

    public Payment() {
    }

    public Payment(int customerNumber, String checkNumber, String paymentDate, double amount) {
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
