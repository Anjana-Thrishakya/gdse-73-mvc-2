/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.OrderDetailDto;
import edu.ijse.mvc.dto.OrderDto;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author anjana
 */
public class OrderModel {
    public String placeOrder(OrderDto orderDto, ArrayList<OrderDetailDto> orderDetailDtos) throws  Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        
        try {
            connection.setAutoCommit(false);
            
            String orderSQL = "INSERT INTO Orders VALUES (?,?,?)";
            PreparedStatement orderStatement = connection.prepareStatement(orderSQL);
            orderStatement.setString(1, orderDto.getOrderId());
            orderStatement.setString(2, orderDto.getDate());
            orderStatement.setString(3, orderDto.getCustId());
            
            boolean isOrderSaved = orderStatement.executeUpdate() > 0;
            
            if(isOrderSaved){
                
                String orderDetailSQL = "INSERT INTO OrderDetail VALUES (?,?,?,?)";
                
                boolean isOrderDetailSaved = true;
                for (OrderDetailDto orderDetailDto : orderDetailDtos) {
                    PreparedStatement orederDetailStatement = connection.prepareStatement(orderDetailSQL);
                    orederDetailStatement.setString(1, orderDto.getOrderId());
                    orederDetailStatement.setString(2, orderDetailDto.getItemCode());
                    orederDetailStatement.setInt(3, orderDetailDto.getQty());
                    orederDetailStatement.setInt(4, orderDetailDto.getDiscount());
                    
                    if(!(orederDetailStatement.executeUpdate() > 0)){
                        isOrderDetailSaved = false;
                    }
                }
                
                if(isOrderDetailSaved){
                    String itemUpdateSQL = "UPDATE Item SET QtyOnHand = (QtyOnHand - ?) WHERE ItemCode = ?";
                    boolean isItemUpdated = true;
                    
                    for (OrderDetailDto orderDetailDto : orderDetailDtos) {
                        PreparedStatement itemStatemnt = connection.prepareStatement(itemUpdateSQL);
                        itemStatemnt.setInt(1, orderDetailDto.getQty());
                        itemStatemnt.setString(2, orderDetailDto.getItemCode());
                        
                        if(!(itemStatemnt.executeUpdate() > 0)){
                            isItemUpdated = false;
                        }
                    }
                    
                    if(isItemUpdated){
                        connection.commit();
                        return "Success";
                    } else {
                        connection.rollback();
                        return "Item Update Error"
;                    }
                    
                } else {
                    connection.rollback();
                    return "Order Detail Save Error";
                }
                
            } else {
                connection.rollback();
                return "Order Save Error";
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        
    }
}
