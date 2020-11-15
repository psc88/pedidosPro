/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.DB;
import model.Order;
import model.OrderDetail;
    import view.*;
/**
 *
 * @author psc88
 */
public class ControllerMasterOrder {
    public static ViewMasterOrder  view = new ViewMasterOrder();
    
    public static void show() {
        view.setVisible(true);
        masterConsultant();
    };
    public static void hide() {
        view.setVisible(false);
    };
    
    public static Order buildOrderInstance(){
        Order order = new Order();
        
        order.setOrderNumber(Integer.parseInt( view.getNumberOrder().getText() ));
        order.setRequiredDate(view.getRequired().getText() );
        order.setStatus (view.getStatusOrder().getText() );
        
        return order;
    }
    
    public static OrderDetail buildOrderDetailInstance(){
        OrderDetail orderdetail = new OrderDetail();
        
        orderdetail.setOrderLineNumber(Integer.parseInt(view.getLine().getText()));
        orderdetail.setQuantityOrdered(Integer.parseInt(view.getQuantity().getText()) );
        
        return orderdetail;
    }
    
    public static void cleanTableOrder(){
        
    }
    
    public static void masterConsultant(){
        
        DefaultTableModel data = (DefaultTableModel) view.getOrderTable().getModel();
        ArrayList<Order> orders = DB.getMasterConsultant();
        data.setNumRows(0);
        
        for (int i=0; i < orders.size(); i++){
            Object [] row = new Object[6];
            
            row[0] = orders.get(i).getOrderNumber();
            row[1] = orders.get(i).getOrderDate();
            row[2] = orders.get(i).getRequiredDate();
            row[3] = orders.get(i).getShippedDate();
            row[4] = orders.get(i).getStatus();
            row[5] = orders.get(i).getCustomerNumber();
            
            data.addRow(row); 
            
        }
        
    }
    public static void selectOrderDetail(){
        int nfila = view.getOrderDetailsTable().getSelectedRow();
        if (nfila >=0){
            String code = view.getOrderDetailsTable().getValueAt(nfila,2).toString();
            String number = view.getOrderDetailsTable().getValueAt(nfila, 1).toString();
            String quantity = view.getOrderDetailsTable().getValueAt(nfila, 4).toString();
            String line = view.getOrderDetailsTable().getValueAt(nfila, 0).toString();
            view.getNumberOrderDetail().setText(number);
            view.getCodeProduct().setText(code);
            view.getQuantity().setText(quantity);
            view.getLine().setText(line);
        }
    }
    
    public static void selectOrder(){
        int nfila = view.getOrderTable().getSelectedRow();
        if (nfila >=0){
            String orderNumber = view.getOrderTable().getValueAt(nfila,0).toString();
            String date = view.getOrderTable().getValueAt(nfila, 2).toString();
            String status = view.getOrderTable().getValueAt(nfila, 4).toString();
            view.getNumberOrder().setText(orderNumber);
            view.getRequired().setText(date);
            view.getStatusOrder().setText(status);
            
            getaddDetail(orderNumber, date, status);
        }
    }
    
    public static void getaddDetail(String orderNumber, String date, String status){
        
        DefaultTableModel data = (DefaultTableModel) view.getOrderDetailsTable().getModel();
        ArrayList<OrderDetail> orderdetails = DB.getaddDetail(orderNumber);
        data.setNumRows(0);
        
        for (int i=0; i < orderdetails.size(); i++){
            Object [] row = new Object[6];
            
            row[0] = orderdetails.get(i).getOrderLineNumber();
            row[1] = orderdetails.get(i).getOrderNumber();
            row[2] = orderdetails.get(i).getProductCode();
            row[3] = orderdetails.get(i).getPriceEach();
            row[4] = orderdetails.get(i).getQuantityOrdered();
            
            data.addRow(row);
        }
    }
    
    public static void modifyOrders(String selectedOrderNumber) {
        Order order = buildOrderInstance();
        DB.modifyOrder(order, selectedOrderNumber);
        
        masterConsultant();
    }
    
    public static void delete(String selectedOrderNumber){
        DB.deleteOrderDetail(selectedOrderNumber);
        DB.deleteOrder(selectedOrderNumber);
    }
    
    public static void deleteDetail(String selectedline){
        DB.deletelineOrder(selectedline);
    }
    
    public static void modifyOrderDetail(){
        OrderDetail orderdetail = buildOrderDetailInstance();
        DB.modifyOrderDetail(orderdetail);
        
        selectOrder();
    }
}
