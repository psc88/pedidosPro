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
    private String date;
    private double amount;

    public Payment() {
    }

    public Payment(int customerNumber, String checkNumber, String date, double amount) {
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
        this.date = date;
        this.amount = amount;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public String getDate() {
        return date;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
