/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DB;
import view.*;

/**
 *
 * @author psc88
 */
public class ControllerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        DB.getConnection();
        
        //ControllerOrder.mostrar();
        //ControllerLogin.mostrar();
        //ControllerProduct.show();
        ControllerMasterOrder.show();
        // ControllerLogin.mostrar();
        //ControllerProduct.show();
        //ControllerProductLine.show();
        //ControllerPayment.show();
        // ControllerCustomer.show();
        // show();
    }
    
    public static ViewMain view = new ViewMain();
    
    public static void show() { view.setVisible(true); };
    public static void hide() { view.setVisible(false); };
}
