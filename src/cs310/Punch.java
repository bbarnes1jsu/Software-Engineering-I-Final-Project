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
    GregorianCalendar cal2 = new GregorianCalendar();
    
    private int terminalid;
    private int punchtypeid;
    private long timeStamp = 0;
    private long adjustedTimeStamp = 0;
    private String badgeId;
    
    //Constructor
    public Punch(Badge badge,int terminalid,int punchtypeid){
        
        this.badgeId = badge.getID();
        this.punchtypeid = punchtypeid;
        this.terminalid = terminalid;        
    }
    
    //This method will print the original time stamp based on what type of punch employee makes. 
    public String printOriginalTimestamp(){
        
        String punch = null;
        cal = new GregorianCalendar();
        cal.setTimeInMillis(timeStamp);
        
        switch (punchtypeid) {
            case 0:
                punch = "#" + badgeId + " CLOCKED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 1:
                punch = "#" + badgeId + " CLOCKED IN: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 2:
                punch = "#" + badgeId + " TIMED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            default:
                break;
        }
        return punch.toUpperCase();
    }
    
    public void adjust(Shift s){
        
        GregorianCalendar shiftStart = new GregorianCalendar();
        GregorianCalendar shiftStop = new GregorianCalendar();
        GregorianCalendar lunchStart = new GregorianCalendar();
        GregorianCalendar lunchStop = new GregorianCalendar();
        GregorianCalendar startInterval = new GregorianCalendar();
        GregorianCalendar stopInterval = new GregorianCalendar();
        GregorianCalendar startGrace = new GregorianCalendar();
        GregorianCalendar stopGrace = new GregorianCalendar();
        GregorianCalendar startDock= new GregorianCalendar();
        GregorianCalendar stopDock = new GregorianCalendar();
                   
        cal = new GregorianCalendar();
        cal.setTimeInMillis(timeStamp);
        long originalTimeStampInMillis = cal.getTimeInMillis();
        cal2.setTimeInMillis(originalTimeStampInMillis);
        
        shiftStart.setTimeInMillis(originalTimeStampInMillis);
        shiftStart.set(Calendar.HOUR_OF_DAY, s.getStart().getHours());
        shiftStart.set(Calendar.MINUTE, s.getStart().getMinutes());
        shiftStart.set(Calendar.SECOND, 0);
        long shiftStartInMillis = shiftStart.getTimeInMillis();

        startDock.setTimeInMillis(shiftStartInMillis);
        
        startInterval.setTimeInMillis(shiftStartInMillis);
        
        startGrace.setTimeInMillis(shiftStartInMillis);
    }
    public String printAdjustedTimestamp(){
        
        String punch = null;
        
        switch (punchtypeid) {
            case 0:
                punch = "#" + badgeId + " CLOCKED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 1:
                punch = "#" + badgeId + " CLOCKED IN: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            case 2:
                punch = "#" + badgeId + " TIMED OUT: " + cal.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM/dd/uuuu HH:mm:ss"));
                break;
            default:
                break;
        }
        return punch.toUpperCase();
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
    
    public long getAdjustedTimeStamp(){
        return adjustedTimeStamp;
    }
    
    public void setTimeStamp(long newTimeStamp){
        timeStamp = newTimeStamp;
    }
}



