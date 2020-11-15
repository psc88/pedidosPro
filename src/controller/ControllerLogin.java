
package controller;

import javax.swing.JOptionPane;
import view.*;


public class ControllerLogin {
    public static ViewLogin view = new ViewLogin();
    public static void mostrar() { view.setVisible(true); };
    public static void ocultar() { view.setVisible(false); };
    
    
    public static void actionButtonEnter(){
        String usuario = view.getEmail().getText();
        String password = view.getPassword().getText();
        
        if (usuario.equals("admin") && password.equals("1234")) {
            ocultar();
            ControllerOrder.mostrar();
        } else {
            JOptionPane.showMessageDialog(null,"Usuario y/o Clave Incorrecta.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        
    }
}
