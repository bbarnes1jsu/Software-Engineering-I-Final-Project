/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dustin Moody
 */
import java.sql.*;
import java.util.*;
import org.json.simple.*;


public class TASDatabase{
    
    public static void main(String[] args){
        
        new TASDatabase().openConnection();
        
    } 
    
    Connection conn;
    ResultSet resultset;
    ResultSetMetaData metadata;
    Statement stmt;

    public void openConnection(){

        
        //Opens connection to database
        
        try{

            /* Identify the Server */

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String server = "jdbc:mysql://localhost/tas";
            String username = "teamc";
            String password = "CS310";
            System.out.println("Connecting to " + server + "...");
            
            
            /* Open Connection and statement*/
        
            conn = DriverManager.getConnection(server, username, password);
            stmt = conn.createStatement();
            
            /* Test Connection */
            
            if(conn.isValid(0)){
                
                /* Connection Open! */
                
                System.out.println("Connected Succesfully!"); 

            }
            
            else{
                
                System.out.println("Connection failed!"); 
                
            }
            
            
        }
        
        
        catch (Exception e) {
            System.err.println(e.toString());
        }
        
    }
    
    public void closeConnection(){
        
        try{
            conn.close();
        }
        catch(Exception e){
            System.err.println(e.toString());
        }
        
    }
    
    public void closeStatement(Statement stmt){
        
        try{
            stmt.close();
        }
        
        catch(Exception e){
            System.err.println(e.toString());
        }
    }
    
   /*public Badge getBadge(String id){
       
       try{
           ResultSet resultset = stmt.executeQuery("SELECT * FROM badge WHERE id =' " + id + " ' ");
           if (resultset != null){
               resultset.next();
               String badge = resultset.getString("id");
        }
           
       }
       
   }*/
   
   public void getShift(){
       
   }
   
   public void getPunch(){
       
   }
}
