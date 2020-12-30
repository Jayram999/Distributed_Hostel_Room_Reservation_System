import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 
import java.util.*;  
import java.sql.*;
import java.lang.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.*;
import java.util.*;
import java.io.*;

public class InstituteClient{  
   private InstituteClient() {}  
   public static void main(String[] args)throws Exception { 
      int count = 0; 
      String url = "jdbc:mysql://localhost:3306/students_data";
      String user = "root";
      String password = "root";
      Connection myConn = null;
      PreparedStatement myStmt = null,insStmt = null,delStmt = null,myStmt2 = null;
      try { 
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 
         Inst stub = (Inst) registry.lookup("Inst"); 
    
         
         Scanner scanner = null;
         // 0. read user input from command line: last name, first name and email
         scanner = new Scanner(System.in);
         
         System.out.println("Do you want to Login or Register:");
         String lr = scanner.nextLine();
         if(lr.equals("1")){ 
             
         System.out.println("Enter the Institute Id : ");
         int id = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Enter the password : ");
         String logs = scanner.nextLine();
         boolean status = stub.instauthenticate(id, logs);
         myConn = DriverManager.getConnection(url, user, password); 
         if(status) {
            System.out.println("You are an authorized user...");
            while(true){
            System.out.println("1: Add Rooms");
            System.out.println("2: Removing Rooms");
            System.out.println("3: List of Rooms Allocated");
            System.out.println("4: List of Rooms Vacant");
            System.out.println("5: Show Complaints");
            System.out.println("6: Responding Complaints");
            System.out.println("7: Exit");
            System.out.println("");
            System.out.println("Enter the choice:");
            int choice = scanner.nextInt();
            switch(choice){
               case 1:
                  System.out.println("Enter the Total No Rooms you want to add:");
                  int totalrooms = scanner.nextInt();
                  while(totalrooms > 0){
                     System.out.println("Enter the Room Id:");
                     int rid = scanner.nextInt();
                     System.out.println("Enter the No of Seats:");
                     int nofSeats = scanner.nextInt();
                     String ir = "insert into institute_room_data "
               + " (Room_id,No_of_Seats)" + " values (?, ?)";

                     insStmt = myConn.prepareStatement(ir);

                     // set param values
                     insStmt.setInt(1, rid);
                     insStmt.setInt(2, nofSeats);

                     // 3. Execute SQL query
                     insStmt.executeUpdate();
                     totalrooms--;
                     count++;
                  }
                   break;
               case 2:
                  System.out.println("Enter the Room id you want to Delete:");
                  int rid = scanner.nextInt();
                  String del = "delete from institute_room_data where Room_id = ? ";
                  delStmt = myConn.prepareStatement(del);
                  // set param values
                  delStmt.setInt(1, rid);
                  delStmt.executeUpdate();
                  break;
               case 3:
                   List<Student> list = (List)stub.getStudents(); 
                   for (Student s:list) { 
            
                   // System.out.println("bc "+s.getBranch()); 
                   System.out.println("ID: " + s.getId()); 
                   System.out.println("Full name: " + s.getfName()); 
                   System.out.println("Duration: " + s.getDuration()); 
                   System.out.println("Address: " + s.getAddress());
                   System.out.println(); 
                  }  
                  break;
               case 4:
                  System.out.println(" "+count+" Rooms are Vacant");
                  break;
               case 5:
                  List<Complaint> list1 = (List)stub.getComplaints(); 
                   for (Complaint c:list1) { 
            
                   // System.out.println("bc "+s.getBranch()); 
                   System.out.println("Reg ID: " + c.getregID()); 
                   System.out.println("Query: " + c.getQuery()); 
                   System.out.println("Date and Time: " + c.getDate_time());
                   System.out.println(); 
                  }
                  break;
                case 6:
                 SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                 //Current Date Time in GMT
                 String time = gmtDateFormat.format(new Date());
                 System.out.println("Enter Complaint Id:");
                 int cid = scanner.nextInt();
                 scanner.nextLine();
                 System.out.println("Enter the Solution regarding Complaint:");
                 String sol = scanner.nextLine();
                 String sq1 = "insert into sol_data "
                           + " (Complaint_id,Solution,date_time)" + " values (?, ?, ?)";

                  myStmt2 = myConn.prepareStatement(sq1);
                  //I want to say you that there is less supply of food for students of hostel. 
                  //I am Jayram Nandagiri student of your hostel here to inform you that the food supply is in very bad condition 
                  //there everything is tasteless and 
                  //if we complains to the cookers they scold us instead of checking food materials. we will feel OK if you help us for this.
                  //set param values
                  myStmt2.setInt(1, cid);
                  myStmt2.setString(2,sol);
                  myStmt2.setString(3,time);
                  myStmt2.executeUpdate();
                  myStmt2.close();
                  System.out.println();
                   break;      
               case 7:
                  System.out.println("Exiciting.....");
                  System.exit(0);
                  break;
               default:
                  System.out.println("Invalid choice please enter the choice again");                            
            }
            System.out.println();
         }
            // Do Something....
         } else {
            
            System.out.println("Unauthorized Login Attempt");
         }
         }
         if(lr.equals("0")){
         System.out.println("Enter your Institute Id: ");
         int instId = scanner.nextInt();
         scanner.nextLine();
          System.out.println("Enter your Institute Name: ");
         String instName = scanner.nextLine();

         System.out.println("Enter your Location: ");
         String location = scanner.nextLine();

         System.out.println("Enter your Phone No: ");
         String phoneNo = scanner.nextLine();
         

         System.out.println("Enter your password: ");
         String pass = scanner.nextLine();

         // 1. Get a connection to database
         myConn = DriverManager.getConnection(url, user, password);

         // 2. Create a statement
         String sql = "insert into institute_data "
               + " (instId,instName,location,phoneNo,password)" + " values (?, ?, ?, ?, ?)";

         myStmt = myConn.prepareStatement(sql);

         // set param values
         myStmt.setInt(1, instId);
         myStmt.setString(2, instName);
         myStmt.setString(3, location);
         myStmt.setString(4, phoneNo);
         myStmt.setString(5, pass);

         // 3. Execute SQL query
         myStmt.executeUpdate();
         System.out.println("Registration Sucessfull");
         System.out.println("\nConnecting To RMI Server...\n");
         Thread.sleep(5000);
         System.out.println("Enter the Institute Id : ");
         int ids = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Enter the password : ");
         String logss = scanner.nextLine();
         boolean status = stub.instauthenticate(ids, logss);
          
         if(status){
           System.out.println("You are an authorized user..."); 
           while(true){
            System.out.println("1: Add Rooms");
            System.out.println("2: Removing Rooms");
            System.out.println("3: List of Rooms Allocated");
            System.out.println("4: List of Rooms Vacant");
            System.out.println("5: Exit");
            System.out.println("");
            System.out.println("Enter the choice:");
            int choice = scanner.nextInt();
            switch(choice){
               case 1:
                  System.out.println("Enter the Total No Rooms you want to add:");
                  int totalrooms = scanner.nextInt();
                  while(totalrooms > 0){
                     System.out.println("Enter the Room Id:");
                     int rid = scanner.nextInt();
                     System.out.println("Enter the No of Seats:");
                     int nofSeats = scanner.nextInt();
                     String ir = "insert into institute_room_data "
               + " (Room_id,No_of_Seats)" + " values (?, ?)";

                     insStmt = myConn.prepareStatement(ir);

                     // set param values
                     insStmt.setInt(1, rid);
                     insStmt.setInt(2, nofSeats);

                     // 3. Execute SQL query
                     insStmt.executeUpdate();
                     totalrooms--;
                     count++;
                  }
                   break;
               case 2:
                  System.out.println("Enter the Room id you want to Delete:");
                  int rid = scanner.nextInt();
                  String del = "delete from institute_room_data where Room_id = ? ";
                  delStmt = myConn.prepareStatement(del);
                  // set param values
                  delStmt.setInt(1, rid);
                  delStmt.executeUpdate();
                  break;
               case 3:
                   List<Student> list = (List)stub.getStudents(); 
                   for (Student s:list) { 
                   // System.out.println("bc "+s.getBranch()); 
                   System.out.println("ID: " + s.getId()); 
                   System.out.println("Full name: " + s.getfName()); 
                   System.out.println("Duration: " + s.getDuration()); 
                   System.out.println("Address: " + s.getAddress());
                   System.out.println(); 
                  }  
                  break;
               case 4:
                  System.out.println(" "+count+" Rooms are Vacant");
                  break;
               case 5:
                  System.out.println("Exiciting.....");
                  System.exit(0);
               default:
                  System.out.println("Invalid choice please enter the choice again");                            
            }
            System.out.println();
         }
         }
      } else System.out.println("Unauthorized Login Attempt");
         scanner.close();
         myConn.close();
      }catch (Exception exc) {exc.printStackTrace();}
   } 
} 