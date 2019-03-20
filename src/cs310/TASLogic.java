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
        
        /* Create ArrayList Object */
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();

        /* Create HashMap Object (one for every Punch!) */
        HashMap<String, String> punchData = new HashMap<>();

        /* Add Punch Data to HashMap */
        punchData.put("id", String.valueOf(Punch.getId()));

        /* ... continue in the same way with the remaining Punch data ... */

        /* Append HashMap to ArrayList */
        jsonData.add(punchData);
        
        return null; //delete later
    }
    
}
