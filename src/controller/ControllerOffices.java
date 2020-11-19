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
public class ControllerOffices {
    public static void showView(){
        ControllerMain.hide();
        ControllerOffices.show();
    }
    
    public static ViewOffices  view = new ViewOffices();
    
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
