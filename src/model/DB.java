/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static controller.ControllerOrder.view;
import java.sql.*;

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
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO producto (nombre, precio) VALUE (?, ?)");
            
            //sentencia.setString(1, product.getNombre());
            //sentencia.setDouble(2, product.getPrecio());

            sentencia.execute();            
        } catch (Exception e) {
            System.out.println("Error al insertar producto!");
            System.out.println(e);
        }
    }
    
    public static void modifyProduct() {}
    public static void deleteProduct() {}
    public static void getProducts() {}
    
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