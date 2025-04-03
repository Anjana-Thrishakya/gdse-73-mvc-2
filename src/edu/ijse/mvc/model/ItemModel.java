/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.ItemDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author anjana
 */
public class ItemModel {
    
    public String saveItem(ItemDto itemDto) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Item VALUES(?,?,?,?,?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemDto.getCode());
        statement.setString(2, itemDto.getDescription());
        statement.setString(3, itemDto.getPackSize());
        statement.setDouble(4, itemDto.getUnitPrice());
        statement.setInt(5, itemDto.getQoh());
        
        return statement.executeUpdate()> 0 ? "Success" : "Fail";
    }
    
    public String updateItem(ItemDto itemDto) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemDto.getDescription());
        statement.setString(2, itemDto.getPackSize());
        statement.setDouble(3, itemDto.getUnitPrice());
        statement.setInt(4, itemDto.getQoh());
        statement.setString(5, itemDto.getCode());
        
        return statement.executeUpdate()> 0 ? "Success" : "Fail";
    }
    
    public String deleteItem(String code) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Item WHERE ItemCode = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
 
        statement.setString(1, code);
        
        return statement.executeUpdate()> 0 ? "Success" : "Fail";
    }
    
    public ItemDto searchItem(String code) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Item WHERE ItemCode = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, code);
        
        ResultSet rst = statement.executeQuery();
        if(rst.next()){
            return new ItemDto(rst.getString("ItemCode"), 
                    rst.getString("Description"), 
                    rst.getString("PackSize"),
                    rst.getDouble("UnitPrice"),
                    rst.getInt("QtyOnHand"));
        }
        return null;
    }
    
    public ArrayList<ItemDto> findAllItems() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Item";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ArrayList<ItemDto> dtos = new ArrayList<>();
        
        ResultSet rst = statement.executeQuery();
        while(rst.next()){
            dtos.add(new ItemDto(rst.getString("ItemCode"), 
                    rst.getString("Description"), 
                    rst.getString("PackSize"),
                    rst.getDouble("UnitPrice"),
                    rst.getInt("QtyOnHand")));
        }
        return dtos;
    }
    
}
