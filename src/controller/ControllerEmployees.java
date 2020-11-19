/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.*;

/**
 *
 * @author psc88
 */
public class ControllerEmployees {
    public static void showView(){
        ControllerMain.hide();
        ControllerEmployees.show();
    }
    
    public static ViewEmployees  view = new ViewEmployees();
    
    public static void show() {
        view.setVisible(true);
    };
    public static void hide() {
        view.setVisible(false);
    };
    public static void returnView(){
        ControllerMain.show();
        hide();
    }
}
