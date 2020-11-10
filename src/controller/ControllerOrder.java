
package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import view.*;

public class ControllerOrder {
    public static ViewOrder view = new ViewOrder();
    
    public static void mostrar() { view.setVisible(true); };
    public static void ocultar() { view.setVisible(false); };
    
    public static double totalVenta;
    public static int linea = 1;
    
    public static void focusLostCode(){
    
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/classicmodels","root","");
            PreparedStatement s = c.prepareStatement(
                    "select * from products where productCode=? ");
            s.setString(1,view.getCode().getText());
            ResultSet res = s.executeQuery();
            
            if (res.next()){
                String nombre = res.getString("productName");
                String precio = res.getString("buyPrice");
                view.getDescription().setText(nombre);
                view.getPrice().setText(precio);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void focusLostSubtotal(){
        
        double precio = Double.parseDouble(view.getPrice().getText() );
        int cantidad = Integer.parseInt(view.getQuantity().getText() );
        double subtotal = precio * cantidad;
        view.getSubtotal().setText(""+subtotal);
        
    }
    
    public static void actionAdd(){
    
        Object[] fila = new Object[6];
        fila[0] = linea++;
        fila[1] = view.getCode().getText();
        fila[2] = view.getDescription().getText();
        fila[3] = view.getPrice().getText();
        fila[4] = view.getQuantity().getText();
        fila[5] = view.getSubtotal().getText();
        
        Double precio = Double.parseDouble(view.getSubtotal().getText());
        totalVenta = totalVenta + precio;
        view.getTotalOrder().setText(""+ totalVenta);
        
        DefaultTableModel datos = (DefaultTableModel) view.getOrdertable().getModel();
        datos.addRow(fila);
    
    }
    
    public static void buttonFinishOrder(){
        
    }
    
    
    
}
