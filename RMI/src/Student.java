public class Student implements java.io.Serializable {   
   private int id,duration;   
   private String fname,address;    
  
   public int getId() { 
      return id; 
   } 
   public String getfName() { 
      return fname; 
   } 
   public String getAddress() { 
      return address; 
   } 
   public int getDuration() { 
      return duration; 
   } 
   public void setID(int id) { 
      this.id = id; 
   } 
   public void setfName(String fname) { 
      this.fname = fname; 
   } 
   public void setAddress(String address) { 
      this.address = address; 
   } 
   public void setDuration(int duration) { 
      this.duration = duration; 
   } 
   
}