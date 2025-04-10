/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;

import edu.ijse.mvc.dto.CustomerDto;
import edu.ijse.mvc.model.CustomerModel;
import java.util.ArrayList;

/**
 *
 * @author anjana
 */
public class CustomerController {
    
    private final CustomerModel customerModel;
    
    public CustomerController() throws Exception{
        this.customerModel = new CustomerModel();
    }
    
    public String saveCustomer(CustomerDto dto) throws Exception{
        String resp = customerModel.saveCustomer(dto);
        return resp;
    }
    
    public String updateCustomer(CustomerDto dto) throws Exception{
        String resp = customerModel.updateCustomer(dto);
        return resp;
    }
    
    public String deleteCustomer(String customerId) throws Exception{
        String resp = customerModel.deleteCustomer(customerId);
        return resp;
    }
    
    public CustomerDto searchCustomer(String customerId) throws Exception{
        CustomerDto dto = customerModel.searchCustomer(customerId);
        return dto;
    }
    
    public ArrayList<CustomerDto> findAllCustomer() throws Exception{
        ArrayList<CustomerDto> dtos = customerModel.getAllCustomer();
        return dtos;
    }
}
