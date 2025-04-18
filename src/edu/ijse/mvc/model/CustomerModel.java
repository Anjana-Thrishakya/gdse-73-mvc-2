/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.CustomerDto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author anjana
 */
public class CustomerModel {
    
    private Connection connection;
    
    public  CustomerModel() throws Exception{
        connection = DBConnection.getInstance().getConnection();
    }
    
    public String saveCustomer(CustomerDto dto) throws Exception{
        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dto.getCustId());
        statement.setString(2, dto.getTitle());
        statement.setString(3, dto.getName());
        statement.setString(4, dto.getDob());
        statement.setDouble(5, dto.getSalary());
        statement.setString(6, dto.getAddress());
        statement.setString(7, dto.getCity());
        statement.setString(8, dto.getProvince());
        statement.setString(9, dto.getPostalCode());
        
        return statement.executeUpdate() > 0 ? "Success" : "Fail";
    } 
    
    public String updateCustomer(CustomerDto dto) throws Exception{
        String sql = "UPDATE Customer SET CustTitle = ?, CustName = ?,"
                + "DOB = ?, salary = ?, CustAddress = ?, City = ?"
                + ", Province = ?, PostalCode = ? WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, dto.getTitle());
        statement.setString(2, dto.getName());
        statement.setString(3, dto.getDob());
        statement.setDouble(4, dto.getSalary());
        statement.setString(5, dto.getAddress());
        statement.setString(6, dto.getCity());
        statement.setString(7, dto.getProvince());
        statement.setString(8, dto.getPostalCode());
        
        statement.setString(9, dto.getCustId());
        
        return statement.executeUpdate() > 0 ? "Success" : "Fail";
    } 
    
    public String deleteCustomer(String custId) throws Exception{
        String sql = "DELETE FROM Customer WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, custId);
        
        return statement.executeUpdate() > 0 ? "Success" : "Fail";
    } 
    
    public CustomerDto searchCustomer(String custId) throws Exception{
        String sql = "SELECT * FROM Customer WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, custId);
        
        ResultSet rst = statement.executeQuery();
        
        if(rst.next()){
            return new CustomerDto(rst.getString("CustID"),
                    rst.getString("CustTitle"), rst.getString("CustName"),
                    rst.getString("DOB"), rst.getDouble("salary"),
                    rst.getString("CustAddress"), rst.getString("City"),
                    rst.getString("Province"), rst.getString("PostalCode"));
        }
        
        return null;
    } 
    
    public ArrayList<CustomerDto> getAllCustomer() throws Exception{
        String sql = "SELECT * FROM Customer";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet rst = statement.executeQuery();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        
        while(rst.next()){
            customerDtos.add(new CustomerDto(rst.getString("CustID"),
                    rst.getString("CustTitle"), rst.getString("CustName"),
                    rst.getString("DOB"), rst.getDouble("salary"),
                    rst.getString("CustAddress"), rst.getString("City"),
                    rst.getString("Province"), rst.getString("PostalCode")));
        }
        
        return customerDtos;
    } 
    
}
