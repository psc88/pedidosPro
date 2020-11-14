/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static controller.ControllerOrder.view;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author veliz
 */

// This is the singleton design pattern. We can instantiate this class just once.
public class DB {
    public static Connection connection;
    
    public  static Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:mysql://localhost/classicmodels";
            String user = "root";
            String password = "";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("DB: Connected");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("DB: Not connected!");
                System.out.println(e.getMessage());
            }
        }
        
        return connection;
    }
    
    // Product methods
    public static void addProduct(Product product) {       
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products ("
                    + "productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP"
                    + ") VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        
            statement.setString(1, product.getProductCode());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductLine());
            statement.setString(4, product.getProductScale());
            statement.setString(5, product.getProductVendor());
            statement.setString(6, product.getProductDescription());
            statement.setInt(7, product.getQuantityInStock());
            statement.setDouble(8, product.getBuyPrice());
            statement.setDouble(9, product.getMSRP());
            
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al insertar producto!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void modifyProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET productCode = ?, productName = ?, productLine = ?, productScale = ?, productVendor = ?, productDescription = ?, quantityInStock = ?, buyPrice = ?, MSRP = ? WHERE productCode = ?");
            
            // TODO: Check updating a product code
            statement.setString(1, product.getProductCode());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductLine());
            statement.setString(4, product.getProductScale());
            statement.setString(5, product.getProductVendor());
            statement.setString(6, product.getProductDescription());
            statement.setInt(7, product.getQuantityInStock());
            statement.setDouble(8, product.getBuyPrice());
            statement.setDouble(9, product.getMSRP());
            statement.setString(10, product.getProductCode());
            
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al actualizar producto!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void deleteProduct(String selectedProductCode) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE productCode = ?");
                        
            statement.setString(1, selectedProductCode);
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al eliminar producto!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        
        try {
            PreparedStatement s = connection.prepareStatement("select * from products ORDER BY productCode DESC");
            ResultSet res = s.executeQuery();

            while(res.next()) {
                Product product = new Product();
                product.setProductCode(res.getString("productCode"));
                product.setProductName(res.getString("productName"));
                product.setProductLine(res.getString("productLine"));
                product.setProductScale(res.getString("productScale"));
                product.setProductVendor(res.getString("productVendor"));
                product.setProductDescription(res.getString("productDescription"));
                product.setQuantityInStock(res.getInt("quantityInStock"));
                product.setBuyPrice(res.getDouble("buyPrice"));
                product.setMSRP(res.getDouble("MSRP"));                
                
                products.add(product);
            }            
        } catch (SQLException e) {
            showMessageDialog(null, "Error al obtener productos!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        
        return products;
    }
    
    public static ResultSet getProduct(String productCode) {
        ResultSet res = null;

        try {
            PreparedStatement s = connection.prepareStatement("select * from products where productCode=? ");
            s.setString(1, productCode);
            res = s.executeQuery();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al obtener producto!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }

        return res;
    }
    
    // ProductLine methods
    public static void addProductLine(ProductLine productLine) {       
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO productLines (productLine, textDescription, htmlDescription, image) VALUE (?, ?, ?, ?)");
                        
            statement.setString(1, productLine.getProductLine());
            statement.setString(2, productLine.getTextDescription());
            statement.setString(3, productLine.getHtmlDescription());
            statement.setString(4, productLine.getImage());
            
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al insertar línea de producto!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void modifyProductLine(ProductLine productLine) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE productLines SET textDescription = ?, htmlDescription = ?, image = ? WHERE productLine = ?");
                       
            statement.setString(1, productLine.getTextDescription());
            statement.setString(2, productLine.getHtmlDescription());
            statement.setString(3, productLine.getImage());
            statement.setString(4, productLine.getProductLine());
            
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al actualizar línea de producto!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void deleteProductLine(String selectedProductLine) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM productLines WHERE productLine = ?");
                        
            statement.setString(1, selectedProductLine);
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al eliminar línea de producto!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<ProductLine> getProductLines() {
        ArrayList<ProductLine> productLines = new ArrayList<ProductLine>();
        
        try {
            PreparedStatement s = connection.prepareStatement("select * from productLines ORDER BY productLine DESC");
            ResultSet res = s.executeQuery();

            while(res.next()) {
                ProductLine productLine = new ProductLine();
                productLine.setProductLine(res.getString("productLine"));
                productLine.setTextDescription(res.getString("textDescription"));
                productLine.setHtmlDescription(res.getString("htmlDescription"));
                productLine.setImage(res.getString("image"));         
                
                productLines.add(productLine);
            }            
        } catch (SQLException e) {
            showMessageDialog(null, "Error al obtener líneas de productos!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        
        return productLines;
    }
    
    // Payment methods
    public static void addPayment(Payment payment) {       
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO payments (customerNumber, checkNumber, paymentDate, amount) VALUE (?, ?, ?, ?)");
                        
            statement.setInt(1, payment.getCustomerNumber());
            statement.setString(2, payment.getCheckNumber());
            statement.setString(3, payment.getPaymentDate());
            statement.setDouble(4, payment.getAmount());
            
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al insertar pago!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void modifyPayment(Payment payment) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE payments SET customerNumber = ?, paymentDate = ?, amount = ? WHERE checkNumber = ?");
                       
            statement.setInt(1, payment.getCustomerNumber());
            statement.setString(2, payment.getPaymentDate());
            statement.setDouble(3, payment.getAmount());
            statement.setString(4, payment.getCheckNumber());
            
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al actualizar pago!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void deletePayment(String selectedCheckNumber) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM payments WHERE checkNumber = ?");
                        
            statement.setString(1, selectedCheckNumber);
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al eliminar pago!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Payment> getPayments() {
        ArrayList<Payment> payments = new ArrayList<Payment>();
        
        try {
            PreparedStatement s = connection.prepareStatement("select * from payments ORDER BY paymentDate DESC");
            ResultSet res = s.executeQuery();

            while(res.next()) {
                Payment payment = new Payment();
                payment.setCustomerNumber(res.getInt("customerNumber"));
                payment.setCheckNumber(res.getString("checkNumber"));
                payment.setPaymentDate(res.getString("paymentDate"));
                payment.setAmount(res.getDouble("amount"));         
                
                payments.add(payment);
            }            
        } catch (SQLException e) {
            showMessageDialog(null, "Error al obtener pagos!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        
        return payments;
    }
    
    // Customer methods
    public static void addCustomer(Customer customer) {       
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customers (customerNumber, customerName, contactFirstName, contactLastName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        
            statement.setInt(1, customer.getCustomerNumber());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getContactFirstName());
            statement.setString(4, customer.getContactLastName());
            statement.setString(5, customer.getPhone());
            statement.setString(6, customer.getAddressLine1());
            statement.setString(7, customer.getAddressLine2());
            statement.setString(8, customer.getCity());
            statement.setString(9, customer.getStateField());
            statement.setString(10, customer.getPostalCode());
            statement.setString(11, customer.getCountry());
            statement.setInt(12, customer.getSalesRepEmployeeNumber());
            statement.setDouble(13, customer.getCreditLimit());
            
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al insertar cliente!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void modifyCustomer(Customer customer) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE customers SET customerNumber = ?, customerName = ?, contactFirstName = ?, contactLastName = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ? WHERE customerNumber = ?");
            
            statement.setInt(1, customer.getCustomerNumber());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getContactFirstName());
            statement.setString(4, customer.getContactLastName());
            statement.setString(5, customer.getPhone());
            statement.setString(6, customer.getAddressLine1());
            statement.setString(7, customer.getAddressLine2());
            statement.setString(8, customer.getCity());
            statement.setString(9, customer.getStateField());
            statement.setString(10, customer.getPostalCode());
            statement.setString(11, customer.getCountry());
            statement.setInt(12, customer.getSalesRepEmployeeNumber());
            statement.setDouble(13, customer.getCreditLimit());
            statement.setInt(14, customer.getCustomerNumber());

            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al actualizar cliente!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    public static void deleteCustomer(int selectedCustomerNumber) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE customerNumber = ?");
                        
            statement.setInt(1, selectedCustomerNumber);
            statement.execute();
        } catch (SQLException e) {
            showMessageDialog(null, "Error al eliminar cliente!");
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        
        try {
            PreparedStatement s = connection.prepareStatement("select * from customers ORDER BY customerNumber DESC");
            ResultSet res = s.executeQuery();

            while(res.next()) {
                Customer customer = new Customer();
                
                customer.setCustomerNumber(res.getInt("customerNumber"));
                customer.setCustomerName(res.getString("customerName"));
                customer.setContactFirstName(res.getString("contactFirstName"));
                customer.setContactLastName(res.getString("contactLastName"));
                customer.setPhone(res.getString("phone"));
                customer.setAddressLine1(res.getString("addressLine1"));
                customer.setAddressLine2(res.getString("addressLine2"));
                customer.setCity(res.getString("city"));
                customer.setStateField(res.getString("state"));
                customer.setPostalCode(res.getString("postalCode"));
                customer.setCountry(res.getString("country"));
                customer.setSalesRepEmployeeNumber(res.getInt("salesRepEmployeeNumber"));
                customer.setCreditLimit(res.getDouble("creditLimit"));

                customers.add(customer);
            }            
        } catch (SQLException e) {
            showMessageDialog(null, "Error al obtener clientes!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        
        return customers;
    }
}