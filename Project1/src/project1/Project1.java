/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import connection.SCOTTConnection;
import java.sql.Connection;

/**
 *
 * @author Ericka
 */
public class Project1 {

    public static Connection connection = null;
    
    public static void main(String[] args){
        try{
            SCOTTConnection c = new SCOTTConnection();
            connection = c.getConnection();
        }catch(Exception e){
            System.out.println("Error en la conexión");
        }
    }
    
}
