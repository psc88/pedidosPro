
package model;

// GENERAL INFORMATION (LIKE A SALE): DATE, CLIENT
public class Order {
    private int orderNumber;
    private String orderDate;
    private String requiredDate;
    private String shippedDate;
    private String status;
    private String comments;
    private int customerNumber; 

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", customerNumber=" + customerNumber + '}';
    }

    public Order() {
    }

    public Order(int orderNumber, String orderDate, String requiredDate, String shippedDate, String status, String comments, int customerNumber) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comments = comments;
        this.customerNumber = customerNumber;
    }
    

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public String getComments() {
        return comments;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
    
    
}
