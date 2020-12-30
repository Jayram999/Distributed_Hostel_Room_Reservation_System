public class Complaint implements java.io.Serializable {   
   private int regid;   
   private String query,date_time;    
  
   public int getregID() { 
      return regid; 
   } 
   public String getQuery() { 
      return query; 
   } 
   public String getDate_time() { 
      return date_time; 
   } 

   public void setregID(int regid) { 
      this.regid = regid; 
   } 
   public void setQuery(String query) { 
      this.query = query; 
   } 
   public void setDate_time(String date_time) { 
      this.date_time = date_time; 
   } 

   
}