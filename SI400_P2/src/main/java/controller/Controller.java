/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import model.DAO;

/**
 *
 * @author Gritti
 */
public class Controller {
    
    public static boolean authentication (String user, String password){
        Connection con =  DAO.getConnection(user, password);
        
        if(con == null){
            return false;
        }
        
        return true;
    }
    
    public static boolean getStatusConnection(){
        return DAO.getStatusCon();
    }
    
    public static void closeConnection(){
        DAO.close();
    }
  
}
