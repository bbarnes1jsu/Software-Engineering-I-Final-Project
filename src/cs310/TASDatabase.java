/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dustin Moody
 */
package cs310;

import java.sql.*;
import java.util.*;
import org.json.simple.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;


public class TASDatabase{
    
    //Objects for get methods
    //private Punch punchQuery = new Punch();
    //private Badge badgeQuery = new Badge();
    //private Shift shiftQuery = new Shift(); 
    
    Connection conn;
    ResultSet resultset;
    ResultSetMetaData metadata;
    Statement stmt;

    public TASDatabase(){

        
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
    
    //Closes Connection
    public void closeConnection(){
        
        try{
            conn.close();
        }
        catch(Exception e){
            System.err.println(e.toString());
        }
        
    }
    
    //Closes Statement
    public void closeStatement(Statement stmt){
        
        try{
            stmt.close();
        }
        
        catch(Exception e){
            System.err.println(e.toString());
        }
    }
    
   public Badge getBadge(String id){
       
       Badge badgeQuery = null;

       //Query for badge info
       try{
           ResultSet resultset = stmt.executeQuery("SELECT * FROM badge WHERE id =" + id);
           if (resultset != null){
               resultset.next();
               String badgeid = resultset.getString("id");
               String badgeDesc = resultset.getString("description");
               
               badgeQuery = new Badge(badgeid, badgeDesc);
        }
           
       }
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return badgeQuery;

       
   }
   
   public Shift getShift(int shiftid){
       
       Shift shiftQuery = null;
       String shiftidString = Integer.toString(shiftid);
       
       try{
           ResultSet resultset = stmt.executeQuery("SELECT * FROM shift WHERE id=" + shiftidString); 
           /*ResultSet resultset = "INSERT INTO people (firstname, lastname) VALUES (?, ?)";
                pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstUpdate.setString(1, newFirstName);
                pstUpdate.setString(2, newLastName);*/
           if (resultset != null){
               resultset.next();
               String id = resultset.getString("id");
               String description = resultset.getString("description");
               int gp = resultset.getInt("gracePeriod");
               int dock = resultset.getInt("dock");
               Time start = resultset.getTime("start");
               Time stop = resultset.getTime("stop");
               Time lunchStart = resultset.getTime("lunchStart");
               Time lunchStop = resultset.getTime("lunchStop");
               int lunchDeduct = resultset.getInt("lunchDeduct");
               int interval = resultset.getInt("interval");
               
               shiftQuery = new Shift(id, description, start, stop, interval, gp, dock, lunchStart, lunchStop, lunchDeduct);
               
           }
        }
       
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return shiftQuery; 

       
   }
   
   public Shift getShift(Badge badge){
       
       Shift shiftQuery = null;
       String badgeid = badge.getID();
       
       try {
           ResultSet resultset = stmt.executeQuery("SELECT * FROM employee WHERE badgeid=' " + badgeid + " ' ");
           if (resultset != null){
               
               resultset.next();
               String shiftid = resultset.getString("shiftid");
               int intshiftID = Integer.parseInt(shiftid); 
               
               shiftQuery = getShift(intshiftID); 
               
           }
       }
       
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return shiftQuery;
   }
   
   public Punch getPunch(int punchid){
       
       Punch punchQuery = null;
       String idString = Integer.toString(punchid);
       
       try{
           ResultSet resultset = stmt.executeQuery("SELECT *, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS 'timestamp' FROM punch WHERE id=' " + idString +" '" );
           if (resultset != null){
               resultset.next();
               int id = resultset.getInt("id");
               int terminalID = resultset.getInt("terminalid");
               int ptID = resultset.getInt("punchtypeid");
               String timeStamp = resultset.getString("originaltimestamp");
               String badgeID = resultset.getString("badgeId");
               
               Badge badge = getBadge(badgeID);
               
               punchQuery = new Punch(badge, terminalID, ptID);
           }
       }
       
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return punchQuery;
   }
}