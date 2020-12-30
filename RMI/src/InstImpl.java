import java.sql.*; 
import java.util.*;  

// Implementing the remote interface 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InstImpl implements Inst {

   protected InstImpl() throws RemoteException {
      
      
   }
   public boolean instauthenticate(int id, String password)
         throws RemoteException {

      if ((password != null && !password.isEmpty())) {

         if((id == 121 ) 
               && (password.equalsIgnoreCase("12345"))) {
            
            return true;
         }
      }
      return false;
   }
    public List<Student> getStudents() throws Exception {  
      String url = "jdbc:mysql://localhost:3306/students_data";
      String user = "root";
      String password = "root";
      Connection myConn = null;
      Statement myStmt = null;
                  List<Student> list = new ArrayList<Student>();   
                  myConn = DriverManager.getConnection(url, user, password);
                  myStmt = myConn.createStatement();  
                  String sql = "SELECT * FROM room_data"; 
                  ResultSet rs = myStmt.executeQuery(sql);  
      
                  //Extract data from result set 
                  while(rs.next()) { 
                  // Retrieve by column name 
                     int id  = rs.getInt("Room_id"); 
         
                     String fname = rs.getString("Full_Name"); 
                     int duration = rs.getInt("Duration"); 
         
                     String address = rs.getString("Address"); 
         
                     // Setting the values 
                     Student student = new Student(); 
                     student.setID(id); 
                     student.setfName(fname); 
                     student.setDuration(duration); 
                     student.setAddress(address); 
                     list.add(student); 
                  }
                  rs.close(); 
        return list;  
      } 
      public List<Complaint> getComplaints() throws Exception {  
      String url = "jdbc:mysql://localhost:3306/students_data";
      String user = "root";
      String password = "root";
      Connection myConn = null;
      Statement myStmt = null;
                  List<Complaint> list = new ArrayList<Complaint>();   
                  myConn = DriverManager.getConnection(url, user, password);
                  myStmt = myConn.createStatement();  
                  String sql = "SELECT * FROM complaint_data"; 
                  ResultSet rs = myStmt.executeQuery(sql);  
      
                  //Extract data from result set 
                  while(rs.next()) { 
                  // Retrieve by column name 
                     int regid  = rs.getInt("reg_id"); 
         
                     String query = rs.getString("query"); 
         
                     String date_time = rs.getString("date_time"); 
         
                     // Setting the values 
                     Complaint c = new Complaint(); 
                     c.setregID(regid); 
                     c.setQuery(query); 
                     c.setDate_time(date_time); 
                     list.add(c); 
                  }
                  rs.close(); 
        return list;  
      } 
}







