
package model;


public class Order {
    private int orderNumber;
    private String orderDate;
    private int customerNumber; 

    public Order() {
    }

    public Order(int orderNumber, String orderDate, int customerNumber) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    
}
