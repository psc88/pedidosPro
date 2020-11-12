/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import model.DB;
import view.ViewProduct;

/**
 *
 * @author veliz
 */
public class ControllerProduct {
    public static ViewProduct view = new ViewProduct();
    
    public static void show() {
        view.setVisible(true);
    };
    
    public static void hide() {
        view.setVisible(false);
    };
    
    public static void addProduct() {
        // DB.addProduct(product);
        
        System.out.println("Agregar");
    }
    
    public static void modifyProduct() {
        System.out.println("Agregar");
    }
    
    public static void deleteProduct() {
        System.out.println("Agregar");
    }
}
