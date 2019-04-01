package cs310;

import java.text.SimpleDateFormat;
import java.sql.*;

/**
 *
 * @author Dexter
 */


public class Shift {
   
    private int interval, gracePeriod, lunchDeduct, dock;
    private Time start, stop, lunchStart, lunchStop;
    private String desc, id;
    
   
    Shift(String id, String desc, Time start, Time stop, int interval, int grace, int dock, Time lunchStart, Time lunchStop, int deduct){
                    
                    this.id = id;
                    this.desc = desc;
                    this.start= start;
                    this.stop = stop;
                    this.interval = interval;
                    this.gracePeriod = grace;
                    this.dock = dock;
                    this.lunchStart = lunchStart;
                    this.lunchStop = lunchStop;
                    this.lunchDeduct = deduct;


    }
    
    public String toString(){
         
                    String shift;
                    String startString = (new SimpleDateFormat("HH:mm")).format(start.getTime());
                    String stopString = (new SimpleDateFormat("HH:mm")).format(stop.getTime());
                    String lunchStartString = (new SimpleDateFormat("HH:mm")).format(lunchStart.getTime());
                    String lunchStopString = (new SimpleDateFormat("HH:mm")).format(lunchStop.getTime());
                    shift =  desc+": "+startString+" - "+stopString+" (" +((stop.getTime()-start.getTime()) /1000 /60)+" minutes); Lunch: " + lunchStartString+ " - " + lunchStopString+" (" +((lunchStop.getTime()-lunchStart.getTime()) /1000 /60)+" minutes)";
                    return shift;
                    
    }

    public String getId() {
        return id;
    }

    public int getInterval() {
        return interval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public Time getStart() {
        return start;
    }

    public Time getStop() {
        return stop;
    }

    public Time getLunchStart() {
        return lunchStart;
    }

    public Time getLunchStop() {
        return lunchStop;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public String getDesc() {
        return desc;
    }
    
    public int getLunchTime(){
        int lunchTime = (int) (((lunchStop.getTime()- lunchStart.getTime())/60000));
        return lunchTime;
    }
}
