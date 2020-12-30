import java.sql.*; 
import java.util.*;  
import java.time.*;

// Implementing the remote interface 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplExample implements Hello {

   protected ImplExample() throws RemoteException {
      
      
   }
   public boolean authenticate(String userName, String password)
         throws RemoteException {

      if ((userName != null && !userName.isEmpty())
            && (password != null && !password.isEmpty())) {

         if((userName.equalsIgnoreCase("Jayram")) 
               && (password.equalsIgnoreCase("12345"))) {
            
            return true;
         }
      }
      return false;
   } 
   public List<Solution> getSolutions() throws Exception {  
      String url = "jdbc:mysql://localhost:3306/students_data";
      String user = "root";
      String password = "root";
      Connection myConn = null;
      Statement myStmt = null;
                  List<Solution> list = new ArrayList<Solution>();   
                  myConn = DriverManager.getConnection(url, user, password);
                  myStmt = myConn.createStatement();  
                  String sql = "SELECT * FROM sol_data"; 
                  ResultSet rs = myStmt.executeQuery(sql);  
      
                  //Extract data from result set 
                  while(rs.next()) { 
                  // Retrieve by column name 
                     int cid  = rs.getInt("Complaint_id"); 
         
                     String sol = rs.getString("Solution"); 
         
                     String date_time = rs.getString("date_time"); 
         
                     // Setting the values 
                     Solution c = new Solution(); 
                     c.setcID(cid); 
                     c.setSol(sol); 
                     c.setDate_time(date_time); 
                     list.add(c); 
                  }
                  rs.close(); 
        return list;  
      } 
}







