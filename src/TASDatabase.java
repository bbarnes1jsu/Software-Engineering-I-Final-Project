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
import org.json.simple.*;

public class TASDatabase {

    public static void main(String[] args){
        JSONArray results = getJSONData();
    }
    
    public static JSONArray getJSONData(){
    
        JSONArray results = new JSONArray();
    
        Connection conn = null;
        PreparedStatement pstSelect = null, pstUpdate = null; 
        ResultSet resultset = null;
        ResultSetMetaData metadata = null;

        String query;

        boolean hasresults;
        int resultCount, columnCount, updateCount = 0;

        try {

            /* Identify the Server */

            String server = ("jbdc:mysql://localhost/tas");
            String username = "teamc";
            String password = "CS310";
            System.out.println("Connecting to " + server + "...");
            
            /* Load the MySQL JDBS Drive */
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            /* Open Connection */
        
            conn = DriverManager.getConnection(server, username, password);
            
            /* Test Connection */
            
            if (conn.isValid(0)){
                
                /* Connection Open! */
                
                System.out.println("Connected Succesfully!"); 
            }
        }
        
        
        
        return results; 
    }
}
