import java.time.LocalTime;

/**
 *
 * @author Dexter
 */

public class Shift {
   
    private int id, interval, gracePeriod, dock;
    private LocalTime start, stop, lunchStart, lunchStop, lunchDeduct;
    private String desc;
   
    //Setter Methods
    public void setId(int id) {
        this.id = id;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public void setLunchStart(LocalTime lunchStart) {
        this.lunchStart = lunchStart;
    }

    public void setLunchStop(LocalTime lunchStop) {
        this.lunchStop = lunchStop;
    }

    public void setLunchDeduct(LocalTime lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    //Getter Methods

    public int getId() {
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

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public LocalTime getLunchDeduct() {
        return lunchDeduct;
    }

    public String getDesc() {
        return desc;
    }
}
