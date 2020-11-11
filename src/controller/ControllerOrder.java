
package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.*;
import model.*;

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
        boolean existe = false;  
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/classicmodels","root","");
            PreparedStatement s = c.prepareStatement(
                    "select * from customers where customerNumber=? ");
            s.setString(1,view.getCustomer().getText());
            ResultSet res = s.executeQuery();
            
            existe=res.next();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        if(existe){
        
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
             
        else
        {
            JOptionPane.showMessageDialog(null,"Cliente no existente.","Warning",JOptionPane.WARNING_MESSAGE);
        }
            
        
        
    
    }
    
    public static void buttonFinishOrder( Order o){
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/classicmodels","root","");
            
            PreparedStatement s = c.prepareStatement(
                    "INSERT INTO orders (orderNumber,orderDate,customerNumber) values (?,?,?) ");
            
//            s.setString(1, o.getgetDate());
//            s.setDouble(2, prod.getPrecio());
            
            s.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
}
