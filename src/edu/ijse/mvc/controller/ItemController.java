/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;

import edu.ijse.mvc.dto.ItemDto;
import edu.ijse.mvc.model.ItemModel;
import java.util.ArrayList;

/**
 *
 * @author anjana
 */
public class ItemController {
    
    private ItemModel itemModel = new ItemModel();

    public String saveItem(ItemDto itemDto) throws Exception{
        String resp = itemModel.saveItem(itemDto);
        return resp;
    }
    
    public String updateItem(ItemDto itemDto) throws Exception{
        String resp = itemModel.updateItem(itemDto);
        return resp;
    }
    
    public String deleteItem(String code) throws Exception{
        String resp = itemModel.deleteItem(code);
        return resp;
    }
    
    public ItemDto searchItem(String code) throws Exception{
        ItemDto itemDto = itemModel.searchItem(code);
        return itemDto;
    }
    
    public ArrayList<ItemDto> getAll() throws Exception{
        ArrayList<ItemDto> itemDtos= itemModel.findAllItems();
        return itemDtos;
    }
    
}
