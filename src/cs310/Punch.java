package cs310;

import java.util.GregorianCalendar;
import java.util.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Brandon Barnes
 */
public class Punch {
    
    
    GregorianCalendar cal = new GregorianCalendar();
    
    private int terminalid;
    private int punchtypeid;
    private int id;
    private long timeStamp = 0;
    private long adjustedTimeStamp = 0;
    private String badgeId;
    
    //Constructor
    public Punch(Badge badge,int terminalid,int punchtypeid){
        
        this.badgeId = badge.getID();
        this.punchtypeid = punchtypeid;
        this.id = id;
        this.terminalid = terminalid;
        this.timeStamp = timeStamp;
        
    }
    
    //This method will print the original time stamp based on what type of punch employee makes. 
    public String printOriginalTimestamp(){
        String punch = null;
        cal = new GregorianCalendar();
        cal.setTimeInMillis(timeStamp);
        
        switch (punchtypeid) {
            case 0:
                punch = "#" + badgeId + "CLOCKED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 1:
                punch = "#" + badgeId + "CLOCKED IN: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 2:
                punch = "#" + badgeId + "TIMED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            default:
                break;
        }
        return punch.toUpperCase();
    }
    
    public void adjust(Shift s){
        
    }
    /*public String printAdjustedTimestamp(){
        String punch = null;
        cal = new GregorianCalendar();
        cal.setTimeInMillis(timeStamp);
        
        switch (punchtypeid) {
            case 0:
                punch = "#" + badgeId + "CLOCKED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 1:
                punch = "#" + badgeId + "CLOCKED IN: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 2:
                punch = "#" + badgeId + "TIMED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            default:
                break;
        }
        return punch.toUpperCase(); // + " ("+eventData+")" taken out
    }*/
    
    public String getBadgeId(){
        return badgeId;
    }
    
    public int getTerminalId(){
        return terminalid;
    }
    
    public int getPunchTypeId(){
        return punchtypeid;
    }
    
    public long getOriginalTimeStamp(){
        return timeStamp;
    }
    
    public long getAdjustedTimeStamp(){
        return adjustedTimeStamp;
    }
    
}



