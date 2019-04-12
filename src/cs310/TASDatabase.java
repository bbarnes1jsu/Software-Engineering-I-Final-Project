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
import java.text.SimpleDateFormat;



public class TASDatabase{
    
    //Objects for get methods
    //private Punch punchQuery = new Punch();
    //private Badge badgeQuery = new Badge();
    //private Shift shiftQuery = new Shift(); 
    
    Connection conn;
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
            System.out.println("Connection closing...");
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
       boolean hasresults;
       ResultSet resultset = null;
       PreparedStatement pstSelect = null, pstUpdate = null;
       ResultSetMetaData metadata = null;
       int columnCount, resultCount, updateCount = 0;
       String key, query;

       //Query for badge info
       try{
           
           if (conn.isValid(0)){
               
               // Prepare Select Query
               query = "SELECT * FROM badge WHERE id = ?";
               pstSelect = conn.prepareStatement(query);
               pstSelect.setString(1, id);
               
               //Execute Select Query
               System.out.println("Submitting Query...");
               hasresults = pstSelect.execute();
               
               //Get Results
               while ( hasresults || pstSelect.getUpdateCount() != -1 ) {

                    if ( hasresults ) {
                        
                        /* Get ResultSet Metadata */
                        
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        columnCount = metadata.getColumnCount();
                        

                        /* Get Data; Print as Table Rows */
                        
                        while(resultset.next()) {
                            
                            /* Begin Next ResultSet Row */

                            System.out.println();
                            
                            /* Loop Through ResultSet Columns; Print Values */

                            String badgeID = resultset.getString("id");
                            String description = resultset.getString("description");
                            
                            badgeQuery = new Badge(badgeID, description);

                        }
                        
                    }

                    else {

                        resultCount = pstSelect.getUpdateCount();  

                        if ( resultCount == -1 ) {
                            break;
                        }

                    }
                    
                    /* Check for More Data */

                    hasresults = pstSelect.getMoreResults(); 

                } 
           
            }
           
       }
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
       
       return badgeQuery;

       
   }
   
   public Shift getShift(int shiftid){
       
       Shift shiftQuery = null;
       String shiftidString = Integer.toString(shiftid);
       
       try{
           ResultSet resultset = stmt.executeQuery("SELECT * FROM shift WHERE id=" + shiftidString); 
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
       
       boolean hasresults;
       ResultSet resultset = null;
       PreparedStatement pstSelect = null, pstUpdate = null;
       ResultSetMetaData metadata = null;
       int columnCount, resultCount, updateCount = 0;
       String key, query;

       //Query for badge info
       try{
           
           if (conn.isValid(0)){
               
               // Prepare Select Query
               query = "SELECT * FROM employee WHERE badgeid = ?";
               pstSelect = conn.prepareStatement(query);
               pstSelect.setString(1, badgeid);
               
               //Execute Select Query
               System.out.println("Submitting Query...");
               hasresults = pstSelect.execute();
               
               //Get Results
               while ( hasresults || pstSelect.getUpdateCount() != -1 ) {

                    if ( hasresults ) {
                        
                        /* Get ResultSet Metadata */
                        
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        columnCount = metadata.getColumnCount();
                        

                        /* Get Data; Print as Table Rows */
                        
                        while(resultset.next()) {
                            
                            /* Begin Next ResultSet Row */

                            System.out.println();
                            
                            /* Loop Through ResultSet Columns; Print Values */

                            int shiftID = resultset.getInt("shiftid");
                            
                            shiftQuery = getShift(shiftID);

                        }
                        
                    }

                    else {

                        resultCount = pstSelect.getUpdateCount();  

                        if ( resultCount == -1 ) {
                            break;
                        }

                    }
                    
                    /* Check for More Data */

                    hasresults = pstSelect.getMoreResults();

                } 
           
            }
           
       }
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
       
       return shiftQuery;
       
   }
   
   public Punch getPunch(int punchid){
       
       Punch punchQuery = null;
       String id = Integer.toString(punchid);
       boolean hasresults;
       ResultSet resultset = null;
       PreparedStatement pstSelect = null, pstUpdate = null;
       ResultSetMetaData metadata = null;
       int columnCount, resultCount, updateCount = 0;
       String key, query;

       //Query for badge info
       try{
           
           if (conn.isValid(0)){
               
               // Prepare Select Query
               query = "SELECT *, UNIX_TIMESTAMP(`ORIGINALTIMESTAMP`) * 1000 AS ts FROM punch WHERE id = ?";
               pstSelect = conn.prepareStatement(query);
               pstSelect.setString(1, id);
               
               //Execute Select Query
               System.out.println("Submitting Query...");
               hasresults = pstSelect.execute();
               
               //Get Results
               while ( hasresults || pstSelect.getUpdateCount() != -1 ) {

                    if ( hasresults ) {
                        
                        /* Get ResultSet Metadata */
                        
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        columnCount = metadata.getColumnCount();
                        

                        /* Get Data; Print as Table Rows */
                        
                        while(resultset.next()) {
                            
                            /* Begin Next ResultSet Row */

                            System.out.println();
                            
                            /* Loop Through ResultSet Columns; Print Values */

                        int terminalID = resultset.getInt("terminalid");
                        int ptID = resultset.getInt("punchtypeid");
                        long timeStamp = resultset.getLong("ts");
                        String badgeID = resultset.getString("badgeId");
                        int ID = resultset.getInt("id");

                        Badge badge = getBadge(badgeID);

                        punchQuery = new Punch(badge, terminalID, ptID);
                        punchQuery.setTimeStamp(timeStamp); 
                        punchQuery.setID(ID); 
                        }
                        
                    }

                    else {

                        resultCount = pstSelect.getUpdateCount();  

                        if ( resultCount == -1 ) {
                            break;
                        }

                    }
                    
                    /* Check for More Data */

                    hasresults = pstSelect.getMoreResults();

                } 
           
            }
           
       }
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
       
       return punchQuery;
       
   }
   
    public int insertPunch(Punch p){
        PreparedStatement pstSelect = null, pstUpdate = null;
        ResultSet resultset = null;
        String query;
        int updateCount = 0;
        Punch punch = p;

        int id = 0;
        Long timeStamp = punch.getOriginalTimeStamp();
        int punchTypeId = punch.getPunchTypeId();
        String badgeId = punch.getBadgeId();
        int terminalid = punch.getTerminalId();

        try{
            
            if (conn.isValid(0)){
                Statement stmt = conn.createStatement( );

                query = "INSERT INTO punch ( badgeid, terminalid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)";
                pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                pstUpdate.setString(1,badgeId);
                pstUpdate.setInt(2, terminalid);                        
                pstUpdate.setLong(3,timeStamp);
                pstUpdate.setInt(4,punchTypeId);

               
                updateCount = pstUpdate.executeUpdate();
                if (updateCount > 0) {
                    resultset = pstUpdate.getGeneratedKeys();
                }
                
                ResultSet result = stmt.executeQuery("SELECT * FROM punch ORDER BY id DESC limit 1;");
                
                if (result != null){
                    result.next();
                    id = result.getInt("id");
                }
            }
        }

        catch (Exception e){
        System.err.println(e.toString());
        }

        finally {

        if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }

        if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }

        if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }

        }
        return id;
        }
   
   public ArrayList<Punch> getDailyPunchList(Badge b, Long ts){
       
       String badgeid = b.getID();
       
       boolean hasresults;
       ResultSet resultset = null;
       PreparedStatement pstSelect = null, pstUpdate = null;
       ResultSetMetaData metadata = null;
       int columnCount, resultCount, updateCount = 0;
       String key, query;
       
        ArrayList<Punch> dailyPunch = new ArrayList<>();
        
        //Initialize Gregorian Calendar
        GregorianCalendar gcStart = new GregorianCalendar();
        gcStart.setTimeInMillis(ts);
        gcStart.set(Calendar.HOUR_OF_DAY, 0);
        gcStart.set(Calendar.MINUTE, 0);
        gcStart.set(Calendar.SECOND, 0);
            //System.out.println("gcStop=" + gcStop.getTimeInMillis()); //gives 1536123600381

        GregorianCalendar gcStop = new GregorianCalendar();
        gcStop.setTimeInMillis(ts);
        gcStop.set(Calendar.HOUR_OF_DAY, 23);
        gcStop.set(Calendar.MINUTE, 59);
        gcStop.set(Calendar.SECOND, 59);
            //System.out.println("gcStop=" + gcStop.getTimeInMillis()); //gives 1536209999381
       
       
       try{
           
           if (conn.isValid(0)){
               
               query = "SELECT *, UNIX_TIMESTAMP(`ORIGINALTIMESTAMP`) * 1000 AS ts\n"
               + "FROM punch\n" 
               + "WHERE badgeid = ?\n"
               + "HAVING ts >= ?\n"
               + "AND ts <= ?\n";
               pstSelect = conn.prepareStatement(query);
               pstSelect.setString(1, badgeid);
               pstSelect.setLong(2, gcStart.getTimeInMillis());
               pstSelect.setLong(3, gcStop.getTimeInMillis());
               
               //Execute Select Query
               System.out.println("Submitting Query...");
               hasresults = pstSelect.execute();
               
               //Get Results
               while ( hasresults || pstSelect.getUpdateCount() != -1 ) {

                    if ( hasresults ) {
                        
                        /* Get ResultSet Metadata */
                        
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        columnCount = metadata.getColumnCount();
                        
                       //System.out.println("It made it!");
                        

                        /* Get Data; Print as Table Rows */
                        
                        while(resultset.next()) {
                            
                            int id = resultset.getInt("id");
                            dailyPunch.add(getPunch(id));
                        }
                        
                    }

                    else {

                        resultCount = pstSelect.getUpdateCount();  

                        if ( resultCount == -1 ) {
                            break;
                        }

                    }
                    
                    /* Check for More Data */

                    hasresults = pstSelect.getMoreResults();

                }
           }
       }
       
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
       
       return dailyPunch; 
   }
   
   public ArrayList<Punch> getPayPeriodPunchList(Badge b, Long ts){
       //A pay period is one week
       ArrayList<Punch> weeklyPunch = new ArrayList<>();
       ArrayList<Punch> dailyPunch;
       for(int i = 1; i < 8; i++){
           dailyPunch = getDailyPunchList(b, ts);
           
           for(int p = 0; p < dailyPunch.size(); p++){
               weeklyPunch.add(dailyPunch.get(p));
               
           }
           ts += 86400000; //cycles a day
       }
       return weeklyPunch;

   }
   
   public Absenteeism getAbsenteeism(String badge, long pts){
       Absenteeism absentQuery = null;
       String badgeid = badge;
       boolean hasresults;
       ResultSet resultset = null;
       PreparedStatement pstSelect = null, pstUpdate = null;
       ResultSetMetaData metadata = null;
       int columnCount, resultCount, updateCount = 0;
       String key, query;

       //Query for badge info
       try{
           
           if (conn.isValid(0)){
               
               // Prepare Select Query
               query = "SELECT * FROM absenteeism WHERE badgeid = ?";
               pstSelect = conn.prepareStatement(query);
               pstSelect.setString(1, badgeid);
               
               //Execute Select Query
               System.out.println("Submitting Query...");
               hasresults = pstSelect.execute();
               
               //Get Results
               while ( hasresults || pstSelect.getUpdateCount() != -1 ) {

                    if ( hasresults ) {
                        
                        /* Get ResultSet Metadata */
                        
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        columnCount = metadata.getColumnCount();
                        

                        /* Get Data; Print as Table Rows */
                        
                        while(resultset.next()) {
                            
                            /* Begin Next ResultSet Row */

                            System.out.println();
                            
                            /* Loop Through ResultSet Columns; Print Values */

                        //String badgeID = resultset.getString("badgeid");
                        long payperiod = resultset.getLong("payperiod");
                        double percentage = resultset.getDouble("percentage");

                        absentQuery = new Absenteeism(badgeid, payperiod, percentage);
                        
                        }
                        
                    }

                    else {

                        resultCount = pstSelect.getUpdateCount();  

                        if ( resultCount == -1 ) {
                            break;
                        }

                    }
                    
                    /* Check for More Data */

                    hasresults = pstSelect.getMoreResults();

                } 
           
            }
           
       }
       catch(Exception e){
           
           System.err.println(e.toString());
           
       }
       
       finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
    
       return absentQuery;
   }
   
   public void insertAbsenteeism(Absenteeism absent){
       
    Connection conn = null;
    PreparedStatement pstSelect = null, pstUpdate = null;
    ResultSet resultset = null;
    String query;
    int updateCount = 0;
                   
                  
    String badgeid = absent.getId();
    long payperiod = absent.getPayPeriodTimestamp();
    double percentage = absent.getAbsenteeismPercentage();
    Timestamp payday = new Timestamp(System.currentTimeMillis());
                  
                    
    try{ 
          
        Class.forName("com.mysql.jdbc.Driver").newInstance();
                               
        Statement stmt = conn.createStatement( );
        payday.setTime(payperiod);
        query = "INSERT INTO absenteeism (badgeid, payperiod, percentage) VALUES (?, ?, ?)";
        pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                              
                              
        pstUpdate.setString(1,badgeid);
        pstUpdate.setTimestamp(2, payday);                        
        pstUpdate.setDouble(3,percentage);
                               
        // Get New Key; Print To Console
        updateCount = pstUpdate.executeUpdate();
        if (updateCount > 0) {
            
            resultset = pstUpdate.getGeneratedKeys();
                                    
        }

        conn.close( );
                              
        }
                   
        catch (Exception e){
            System.err.println(e.toString());
        }
                   
        finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
   }
}
