package cs310;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dustin Moody
 */
public class TASLogic {
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        return 0;
    }
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist){
        
        //Loop through dailypunch list
        
        /* Create ArrayList Object */
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();
        
        for (int i = 0; i < dailypunchlist.size(); i++){
            
            Punch punch = dailypunchlist.get(i); 
            
        /* Create HashMap Object (one for every Punch!) */
        HashMap<String, String> punchData = new HashMap<>();

        /* Add Punch Data to HashMap */
        punchData.put("id", String.valueOf(punch.getID()));
        punchData.put("badgeid", String.valueOf(punch.getBadgeId()));
        punchData.put("terminalid", String.valueOf(punch.getTerminalId()));
        punchData.put("punchtypeid", String.valueOf(punch.getPunchTypeId()));
        punchData.put("punchdata", String.valueOf(punch.getPunchData()));
        punchData.put("oringinaltimestamp", String.valueOf(punch.getOriginalTimeStamp()));
        punchData.put("adjustedtimestamp", String.valueOf(punch.getAdjustedTimeStamp()));
        
        /* Append HashMap to ArrayList */
        jsonData.add(punchData);    
        }


        
        return null; //delete later
    }
    
}
