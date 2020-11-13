/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static controller.ControllerOrder.view;
import java.sql.*;
import java.util.ArrayList;

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
        } catch (Exception e) {
            System.out.println("Error al insertar producto!");
            System.out.println(e);
        }
    }
    
    public static void modifyProduct(Product product, String selectedProductCode) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET productCode = ?, productName = ?, productLine = ?, productScale = ?, productVendor = ?, productDescription = ?, quantityInStock = ?, buyPrice = ?, MSRP = ? WHERE productCode = ?");
                        
            statement.setString(1, product.getProductCode());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductLine());
            statement.setString(4, product.getProductScale());
            statement.setString(5, product.getProductVendor());
            statement.setString(6, product.getProductDescription());
            statement.setInt(7, product.getQuantityInStock());
            statement.setDouble(8, product.getBuyPrice());
            statement.setDouble(9, product.getMSRP());
            statement.setString(10, selectedProductCode);

            
            statement.execute();
        } catch (Exception e) {
            System.out.println("Error al actualizar producto!");
            System.out.println(e);
        }
    }
    
    public static void deleteProduct(String selectedProductCode) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE productCode = ?");
                        
            statement.setString(1, selectedProductCode);
            statement.execute();
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto!");
            System.out.println(e);
        }
    }
    
    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        
        try {
            PreparedStatement s = connection.prepareStatement("select * from products");
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
            System.out.println(e.getMessage());
        }
        
        return res;
    }

    // Customer methods
    public static void addCustomer(Product product) {}
    public static void modifyCustomer() {}
    public static void deleteCustomer() {}
    public static void getCustomers() {}
    
}