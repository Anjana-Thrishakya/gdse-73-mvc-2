/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.ijse.mvc;

import edu.ijse.mvc.view.CustomerView;
import edu.ijse.mvc.view.ItemView;
import edu.ijse.mvc.view.OrderView;

/**
 *
 * @author anjana
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        new OrderView().setVisible(true);
    }
    
}
