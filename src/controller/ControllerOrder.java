
package controller;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.*;
import model.*;

public class ControllerOrder {
    public static void showView(){
        ControllerMain.hide();
        ControllerOrder.mostrar();
    }
    
    public static ViewOrder view = new ViewOrder();
    
    public static void mostrar() { view.setVisible(true); };
    public static void hide() { view.setVisible(false); };
    
    public static double totalVenta;
    public static int linea = 1;
    public static int ajustar;
    
    public static void returnView(){
        ControllerMain.show();
        hide();
    }
    
    public static Order buildOrderInstance(){
        Order order = new Order();
        
        order.setOrderNumber(Integer.parseInt( view.getOrder().getText() ));
        order.setOrderDate(view.getDate().getText() );
        order.setRequiredDate(view.getRequireddate().getText());
        order.setShippedDate(view.getRequireddate().getText());
        order.setStatus("pending");
        order.setCustomerNumber( Integer.parseInt(view.getCustomer().getText()) );
        return order;
    } 
            
    // TODO: CHECK TO RESTORE THIS VALIDATION
    public static void focusLostCode() throws SQLException{
        ResultSet res = DB.getProduct(view.getProductCode().getText());
        
        if (res.next()){
            double venta;
            String nombre = res.getString("productName");
            double precio = res.getDouble("buyPrice");
            venta = Math.round(precio + (precio * 0.3));
            
            view.getProductName().setText(nombre);
            view.getPrice().setText(""+venta);
        }
    }
    
    public static double calculateSubtotal() {
        double price = Double.parseDouble(view.getPrice().getText() );
        int quantity = Integer.parseInt(view.getQuantity().getText() );
        return Math.round(price * quantity);
    }
    
    public static void focusLostSubtotal(){
        double subtotal = calculateSubtotal();
        view.getSubtotal().setText(""+subtotal);
    }
    
    public static void actionAddTable(){
        int q = Integer.parseInt(view.getQuantity().getText());
        int s = Integer.parseInt(view.getStock().getText());
        if( q <= s ){
        Object[] fila = new Object[6];
        fila[0] = linea++;
        fila[1] = view.getProductCode().getText();
        fila[2] = view.getProductName().getText();
        fila[3] = view.getPrice().getText();
        fila[4] = view.getQuantity().getText();
        fila[5] = view.getSubtotal().getText();
        
        Double precio = Double.parseDouble(view.getSubtotal().getText());
        totalVenta = Math.round(totalVenta + precio);
        view.getTotalOrder().setText(""+ totalVenta);
        
        DefaultTableModel datos = (DefaultTableModel) view.getOrdertable().getModel();
        datos.addRow(fila);
        
        ajustar = s - q;
        view.getStock().setText(""+ajustar);
        
        }else {
            JOptionPane.showMessageDialog(null,"Stock insuficiente","Warning",JOptionPane.WARNING_MESSAGE);
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
        cleanOrder();
        
        ControllerPayment.setCustomerNumberAndAmount(order.getCustomerNumber(), totalVenta);
        ControllerPayment.show();
    }
    
    public static void cleanOrder(){
        view.getProductCode().setText("");
        view.getProductName().setText("");
        view.getCustomer().setText("");
        view.getOrder().setText("");
        view.getPrice().setText("");
        view.getQuantity().setText("");
        view.getSubtotal().setText("");
        view.getRequireddate().setText("");
        view.getTotalOrder().setText("");
        DefaultTableModel data = (DefaultTableModel) view.getOrdertable().getModel();
        data.setNumRows(0);
    }
    
    public static void searchCustomer() {
        ControllerSearchCustomer.setViewOrder(view);
        ControllerSearchCustomer.show();
    }
    
    public static void searchProduct() {
        ControllerSearchProduct.setViewOrder(view);
        ControllerSearchProduct.show();
    }
}
