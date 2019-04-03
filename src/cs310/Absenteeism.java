package cs310;

import java.util.GregorianCalendar;
import java.util.*;
import java.time.format.DateTimeFormatter;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hbdam
 */
public class Absenteeism {
    
    private String badgeId;
    private long payPeriodTimestamp;
    private double absenteeismPercentage;
    GregorianCalendar payDay = new GregorianCalendar();
   
    public Absenteeism(String badgeId,long payPeriodTimestamp, double absenteeismPercantage){
        this.badgeId = badgeId;
        this.payPeriodTimestamp = payPeriodTimestamp;
        this.absenteeismPercentage = absenteeismPercentage;
    }
    
        public String getId(){
            return badgeId;
        }

        public void setId(String badgeId){
            this.badgeId = badgeId;
        }

        public long getPayPeriodTimestamp(){
            return payPeriodTimestamp;
        }
    
        public void setPayPeriodTimestmap(long payPeriodTimestamp){
            this.payPeriodTimestamp = payPeriodTimestamp;
        }

        public double getAbsenteeismPercentage(){
            return absenteeismPercentage;
        }

        public void setAbsenteeismPercantage(float absenteeismPercentage){
            this.absenteeismPercentage = absenteeismPercentage;
        }
        
        @Override
        public String toString(){
            payDay.setTimeInMillis(payPeriodTimestamp);
            String string = "#" + badgeId + " (Pay Period Starting " + payDay.toZonedDateTime().format(DateTimeFormatter.ofPattern("E MM-dd-uuuu")) + "): " + absenteeismPercentage + "%";
            return string;
        }
                
    
}
