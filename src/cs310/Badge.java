/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310;

/**
 *
 * @author dmang
 */
public class Badge {
    private String id;
    private String description;
    
    public void setID(String newID){
        this.id=newID;
    }
    
    public String getID(){
        return id;
    }
    
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    
   public String getDescription(){
    return description;
}
   public Badge(String id,String description){
       this.id=id; 
           this.description=description;
   }
   
   @Override
   public String toString(){
       String badge;
   badge = "#"+id +" ("+ description+")";
           
   return badge;
           }
            
}
