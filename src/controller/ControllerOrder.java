
package controller;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    public static Order buildOrderInstance(){
        Order order = new Order();
        
        order.setOrderNumber(Integer.parseInt( view.getOrder().getText() ));
        order.setOrderDate(view.getDate().getText() );
//        order.setRequiredDate(view.getDateRequired().getText());
//        order.setShippedDate(view.getDateRequired().getText());
        order.setStatus("pending");
        order.setCustomerNumber( Integer.parseInt(view.getCustomer().getText()) );
        
        
        return order;
    } 
            
    
    public static void focusLostCode() throws SQLException{
        ResultSet res = DB.getProduct(view.getCode().getText());
        
        if (res.next()){
            double venta;
            String nombre = res.getString("productName");
            double precio = res.getDouble("buyPrice");
            venta = Math.round(precio + (precio * 0.3));
            
            view.getDescription().setText(nombre);
            view.getPrice().setText(""+venta);
        }
    }
    
    public static void focusLostSubtotal(){
        
        double precio = Double.parseDouble(view.getPrice().getText() );
        int cantidad = Integer.parseInt(view.getQuantity().getText() );
        double subtotal = Math.round(precio * cantidad);
        view.getSubtotal().setText(""+subtotal);
        
    }
    
    public static void actionAddTable() throws SQLException{
        boolean existe = false;
        ResultSet res = DB.addTable(view.getCustomer().getText());
        existe=res.next();
        
        if(existe){
        Object[] fila = new Object[6];
        fila[0] = linea++;
        fila[1] = view.getCode().getText();
        fila[2] = view.getDescription().getText();
        fila[3] = view.getPrice().getText();
        fila[4] = view.getQuantity().getText();
        fila[5] = view.getSubtotal().getText();
        
        Double precio = Double.parseDouble(view.getSubtotal().getText());
        totalVenta = Math.round(totalVenta + precio);
        view.getTotalOrder().setText(""+ totalVenta);
        
        DefaultTableModel datos = (DefaultTableModel) view.getOrdertable().getModel();
        datos.addRow(fila);
        
        }else {
            JOptionPane.showMessageDialog(null,"Cliente no existente.","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void addOrder(){
        
        Order order = buildOrderInstance();
        DB.addOrder(order);
       
        for (int i=0; i<view.getOrdertable().getModel().getRowCount(); i++ ){
            OrderDetail orderdetail = new OrderDetail();
            
            orderdetail.setOrderLineNumber(Integer.parseInt(view.getOrdertable().getModel().getValueAt(i, 0).toString()));
            orderdetail.setProductCode(view.getOrdertable().getModel().getValueAt(i, 1).toString());
            orderdetail.setPriceEach(Double.parseDouble(view.getOrdertable().getModel().getValueAt(i, 3).toString()));
            orderdetail.setQuantityOrdered(Integer.parseInt(view.getOrdertable().getModel().getValueAt(i, 4).toString()));
            orderdetail.setOrderNumber(Integer.parseInt(view.getOrder().getText()));
            
            DB.addOrderDetails(orderdetail);
        } 
    }
}
