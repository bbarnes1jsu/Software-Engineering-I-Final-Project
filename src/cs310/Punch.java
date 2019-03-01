package cs310;

import java.util.GregorianCalendar;
import java.util.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Brandon Barnes
 */
public class Punch {
    
    public Punch(){} //made this for my get method - Damien
    
    GregorianCalendar cal = new GregorianCalendar();
    
    private int terminalid;
    private int punchtypeid;
    private int id;
    private long timeStamp = 0;
    private long adjustedTimeStamp = 0;
    private String badgeId;
    private String eventData;
    private boolean lunchFlag = false;
    
    public Punch(Badge badge,int terminalid,int punchtypeid, long timeStamp){
        
        this.badgeId = badge.getId();
        this.punchtypeid = punchtypeid;
        this.id = id;
        this.terminalid = terminalid;
        this.timeStamp = timeStamp;
        
    }
    
    public String printOriginalTimeStamp(){
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
    
    public String printAdjustedTimeStamp(){
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
        return punch.toUpperCase() + " ("+eventData+")";
    }
    
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
    
    public String getPunchData(){
        return eventData;
    }
    
    public long getAdjustedTimeStamp(){
        return adjustedTimeStamp;
    }
    
    public boolean getLunchFlag(){
        return lunchFlag;
    }
    
}



