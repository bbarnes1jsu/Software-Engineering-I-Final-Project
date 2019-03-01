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


public class TASDatabase{
    
    //Objects for get methods
    private Punch punchQuery = new Punch();
    private Badge badgeQuery = new Badge();
    private Shift shiftQuery = new Shift(); 
    
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
       
       //Query for badge info
       try{
           ResultSet resultset = stmt.executeQuery("SELECT * FROM badge WHERE id =' " + id + " ' ");
           if (resultset != null){
               resultset.next();
               String badgeid = resultset.getString("id");
               
               badgeQuery = new Badge(badgeid);
        }
           
       }
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return badgeQuery;
       
   }
   
   public Shift getShift(int shiftid){
       
       String shiftidString = Integer.toString(shiftid);
       
       try{
           ResultSet resultset = stmt.executeQuery("SELECT * FROM shift WHERE id=" + shiftidString); 
           if (resultset != null){
               resultset.next();
               String id = resultset.getString("id");
               String description = resultset.getString("description");
               String gp = resultset.getString("gracePeriod");
               String dock = resultset.getString("dock");
               String start = resultset.getString("start");
               String stop = resultset.getString("stop");
               String lunchStart = resultset.getString("lunchStart");
               String lunchStop = resultset.getString("lunchStop");
               String lunchDeduct = resultset.getString("lunchDeduct");
               
               shiftQuery = new Shift(id, description, gp, dock, start, stop, lunchStart, lunchStop, lunchDeduct);
               
               
           }
        }
       
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return shiftQuery; 

       
   }
   
   public Shift getShift(Badge badge){
       
       try {
           ResultSet resultset = stmt.executeQuery("SELECT * FROM employee WHERE badgeid=' " + badgeid + " ' ");
           if (resultset != null){
               
               resultset.next();
               String shiftid = resultset.getString("shiftid");
               
               shiftQuery = getShift(shiftid); 
           }
       }
       
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return shiftQuery;
   }
   
   public Punch getPunch(int punchid){
       
       String idString = Integer.toString(punchid);
       
       try{
           ResultSet resultset = stmt.executeQuery("SELECT *, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS 'timestamp' FROM event WHERE id=' " + idString +" '" );
           if (resultset != null){
               resultset.next();
               String id = resultset.getString("id");
               String terminalID = resultset.getString("terminalid");
               String ptID = resultset.getString("punchtypeid");
               String timeStamp = resultset.getString("timeStamp");
               String atimeStamp = resultset.getString("adjustedTimeStamp");
               String badgeID = resultset.getString("badgeId");
               String eventData = resultset.getString("eventData");
               String lunchFlag = resultset.getString("lunchFlag");
               
               int terminalIDint = Integer.parseInt(terminalID);
               int eventID = Integer.parseInt(eventData);
               long longTimeStamp= Long.parseLong(timeStamp);
               
               punchQuery = new Punch(badgeID, terminalIDint, eventID, longTimeStamp);
           }
       }
       
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       return punchQuery;
   }
}