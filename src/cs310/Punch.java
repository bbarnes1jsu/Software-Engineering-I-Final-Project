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
    private String note;
    private int id;
    
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

        startInterval.setTimeInMillis(shiftStartInMillis);
        startInterval.add(Calendar.MINUTE, -s.getInterval());
        long startIntervalInMillis = startInterval.getTimeInMillis();
        
        startGrace.setTimeInMillis(shiftStartInMillis);
        startGrace.add(Calendar.MINUTE, s.getGracePeriod());
        long startGraceInMillis = startGrace.getTimeInMillis();
        
        startDock.setTimeInMillis(shiftStartInMillis);
        startDock.add(Calendar.MINUTE, s.getDock());
        long startDockInMillis = startDock.getTimeInMillis();
        
        lunchStart.setTimeInMillis(originalTimeStampInMillis);
        lunchStart.set(Calendar.HOUR_OF_DAY, s.getLunchStart().getHours());
        lunchStart.set(Calendar.MINUTE, s.getLunchStart().getMinutes());
        lunchStart.set(Calendar.SECOND, 0);
        long lunchStartInMillis = lunchStart.getTimeInMillis();
        
        shiftStop.setTimeInMillis(originalTimeStampInMillis);
        shiftStop.set(Calendar.HOUR_OF_DAY,s.getStop().getHours());
        shiftStop.set(Calendar.MINUTE, s.getStop().getMinutes());
        shiftStop.set(Calendar.SECOND, 0);
        long shiftStopInMillis = shiftStop.getTimeInMillis();
        
        stopInterval.setTimeInMillis(shiftStopInMillis);
        stopInterval.add(Calendar.MINUTE, s.getInterval());
        long stopIntervalInMillis = stopInterval.getTimeInMillis();
        
        stopGrace.setTimeInMillis(shiftStopInMillis);
        stopGrace.add(Calendar.MINUTE, -s.getGracePeriod());
        long stopGraceInMillis = stopGrace.getTimeInMillis();
        
        stopDock.setTimeInMillis(shiftStopInMillis);
        stopDock.add(Calendar.MINUTE, -s.getDock());
        long stopDockInMillis = stopDock.getTimeInMillis();
        
        lunchStop.setTimeInMillis(shiftStopInMillis);
        lunchStop.set(Calendar.HOUR_OF_DAY, s.getLunchStop().getHours());
        lunchStop.set(Calendar.MINUTE, s.getLunchStop().getMinutes());
        lunchStop.set(Calendar.SECOND, 0);
        long lunchStopInMillis = lunchStop.getTimeInMillis();
        
        int interval = s.getInterval();
        
        if(shiftStart.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || shiftStart.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            
            if(punchtypeid == 1){
                if(originalTimeStampInMillis >= startIntervalInMillis && originalTimeStampInMillis <= shiftStartInMillis + (s.getInterval() * 60000)){
                    note = "None";
                }
                else{
                    if(cal.get(Calendar.MINUTE) % interval <= interval /2){
                        cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - (cal.get(Calendar.MINUTE) % interval));
                        cal2.set(Calendar.SECOND, 0);
                    }
                    else{
                        cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + (interval - (cal.get(Calendar.MINUTE) % interval)));
                        cal2.set(Calendar.SECOND, 0);
                    }
                    note = "Interval Round";
                }
            }
        }
        
        else if(punchtypeid == 0){
            if(originalTimeStampInMillis <= stopIntervalInMillis && originalTimeStampInMillis >= shiftStopInMillis + (s.getInterval() * 60000)){
                note = "None";
            }
            else{
                if(cal.get(Calendar.MINUTE) % interval >= interval / 2){
                    cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + (interval - (cal.get(Calendar.MINUTE) % interval)));
                    cal2.set(Calendar.SECOND, 0);
                }
                else{
                    cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - (cal.get(Calendar.MINUTE) % interval));
                    cal2.set(Calendar.SECOND, 0);
                }
                note = "Interval Round";
            }
        }
        
        else{
            //Handling Clocking In
            if(punchtypeid == 1){
                if(originalTimeStampInMillis <= shiftStartInMillis && originalTimeStampInMillis >= startIntervalInMillis){
                    cal2.setTimeInMillis(shiftStartInMillis);
                    note = "Shift Start";
                }
                else if(originalTimeStampInMillis >= shiftStartInMillis && originalTimeStampInMillis <= startGraceInMillis){
                    cal2.setTimeInMillis(shiftStartInMillis);
                    note = "Shift Start";
                }
                else if(originalTimeStampInMillis >= lunchStartInMillis && originalTimeStampInMillis <= lunchStopInMillis){
                    cal2.setTimeInMillis(lunchStopInMillis);
                    note = "Lunch Stop";
                }
                else if(originalTimeStampInMillis > startGraceInMillis && cal.get(Calendar.MINUTE) % interval > interval /2){
                    cal2.setTimeInMillis(startDockInMillis);
                    note = "Shift Dock";
                }
                else if(cal.get(Calendar.HOUR_OF_DAY) == shiftStart.get(Calendar.HOUR_OF_DAY) + 1 && cal.get(Calendar.MINUTE) == shiftStart.get(Calendar.MINUTE)){
                        note = "None";
                }
                else{
                    if(cal.get(Calendar.MINUTE) % interval <= interval / 2){
                        cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - (cal.get(Calendar.MINUTE) % interval));
                        cal2.set(Calendar.SECOND, 0);
                    }
                    else{
                        cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + (cal.get(Calendar.MINUTE) % interval));
                        cal2.set(Calendar.SECOND, 0);
                    }
                    note = "Interval Round";
                }
            }
            
            else if(punchtypeid == 0){
                //Handling Clocking Out
                
            }
        }    
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
        return punch.toUpperCase() +" ("+note+")";
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
    
    public int getID(){
        return id;
    }
    public void setTimeStamp(long newTimeStamp){
        timeStamp = newTimeStamp;
    }
    
    public void setID (int newID){
        id = newID;
    }
  
}



