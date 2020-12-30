import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 
import java.util.*;  
import java.time.*;
import java.sql.*;
import java.lang.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.*;
import java.util.*;
import java.io.*;

public class Client{  
   private Client() {}  
   public static void main(String[] args)throws Exception {  
      String url = "jdbc:mysql://localhost:3306/students_data";
      String user = "root";
      String password = "root";
      Connection myConn = null;
      PreparedStatement myStmt = null,myStmt2 = null;
      try { 
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 
         Hello stub = (Hello) registry.lookup("Hello"); 
    
         
         Scanner scanner = null;
         // 0. read user input from command line: last name, first name and email
         scanner = new Scanner(System.in);
         
         System.out.println("Do you want to Login or Register:");
         String lr = scanner.nextLine();
         if(lr.equals("1")){ 
             
         System.out.println("Enter the username : ");
         String userName = scanner.nextLine();
         
         System.out.println("Enter the password : ");
         String logs = scanner.nextLine();
         boolean status = stub.authenticate(userName, logs);
          
         if(status) {
            System.out.println("You are an authorized user...");
            Thread.sleep(5000);
            String s = "yes";
      try{
            myConn = DriverManager.getConnection(url, user, password);
            while(true){
            System.out.println("1: Apply for an hostel");
            System.out.println("2: Raising complaint");
            System.out.println("3: Notification");
            System.out.println("4: Exit");
            System.out.println("");
            System.out.println("Enter the choice:");
            int choice = scanner.nextInt();
            switch(choice){
               case 1:
               System.out.println("Enter your Details for Room Alllocation");
               System.out.println("Enter your Room No: ");
               int roomId = scanner.nextInt();
               scanner.nextLine();
               System.out.println("Enter the Full Name:");
               String fName = scanner.nextLine();
               System.out.println("2 Seats are Available");
               System.out.println("Fees per month:Rs 4000");
               System.out.println("Duration/Months: ");
               int duration = scanner.nextInt();
               scanner.nextLine();
               System.out.println("Address:");
               String address = scanner.nextLine();
               
               String sq = "insert into room_data "
               + " (Room_id,Full_Name,Duration,Address)" + " values (?, ?, ?, ?)";

         myStmt2 = myConn.prepareStatement(sq);

         // set param values
         myStmt2.setInt(1, roomId);
         myStmt2.setString(2,fName);
         myStmt2.setInt(3, duration);
         myStmt2.setString(4, address);
         myStmt2.executeUpdate();
         myStmt2.close();
         break;
         case 2:
                 SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                 //Current Date Time in GMT
                 String time = gmtDateFormat.format(new Date());
                 System.out.println("Enter your College Id:");
                 int regid = scanner.nextInt();
                 scanner.nextLine();
                 System.out.println("Enter your Query:");
                 String query = scanner.nextLine();
                 String sq1 = "insert into complaint_data "
                           + " (reg_id,query,date_time)" + " values (?, ?, ?)";

                  myStmt2 = myConn.prepareStatement(sq1);
                  //I want to say you that there is less supply of food for students of hostel. 
                  //I am Jayram Nandagiri student of your hostel here to inform you that the food supply is in very bad condition 
                  //there everything is tasteless and 
                  //if we complains to the cookers they scold us instead of checking food materials. we will feel OK if you help us for this.
                  //set param values
                  myStmt2.setInt(1, regid);
                  myStmt2.setString(2,query);
                  myStmt2.setString(3,time);
                  myStmt2.executeUpdate();
                  myStmt2.close();
                  System.out.println();
                  break;
         case 3:
                 System.out.println("Enter the Complaint ID:");
                 int cid = scanner.nextInt();
                 scanner.nextLine();
                 List<Solution> list1 = (List)stub.getSolutions(); 
                   for (Solution c:list1){ 
                   if(cid == c.getcID()){ 
                   // System.out.println("bc "+s.getBranch()); 
                   System.out.println("Complaint ID: " + c.getcID()); 
                   System.out.println("Solution: " + c.getSol()); 
                   System.out.println("Date and Time: " + c.getDate_time());
                   System.out.println();
                      } 
                  }
                  break;         
         case 4:
                  System.out.println("Exiciting.....");
                  System.exit(0);
               default:
                  System.out.println("Invalid choice please enter the choice again");     
            }
         
          }
        }catch(Exception e){System.out.println(e);} 
            // Do Something....
         } else System.out.println("Unauthorized Login Attempt");
         }
         if(lr.equals("0")){
         System.out.println("Enter your Registration No: ");
         int regId = scanner.nextInt();
         scanner.nextLine();
          System.out.println("Enter your First name: ");
         String firstName = scanner.nextLine();

         System.out.println("Enter your Middle name: ");
         String middleName = scanner.nextLine();

         System.out.println("Enter your last name: ");
         String lastName = scanner.nextLine();

          System.out.println("Gender: ");
         String gender = scanner.nextLine();

         System.out.println("Enter your Phone No: ");
         String phoneNo = scanner.nextLine();
         

         System.out.println("Enter your email: ");
         String email = scanner.nextLine();

         System.out.println("Enter your password: ");
         String pass = scanner.nextLine();



         // 1. Get a connection to database
         myConn = DriverManager.getConnection(url, user, password);

         // 2. Create a statement
         String sql = "insert into student_data "
               + " (reg_id,first_name,middle_name,last_name,gender,phoneNo,email,password)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";

         myStmt = myConn.prepareStatement(sql);

         // set param values
         myStmt.setInt(1, regId);
         myStmt.setString(2, firstName);
         myStmt.setString(3, middleName);
         myStmt.setString(4, lastName);
         myStmt.setString(5, gender);
         myStmt.setString(6, phoneNo);
         myStmt.setString(7, email);
         myStmt.setString(8, pass);

         // 3. Execute SQL query
         myStmt.executeUpdate();
         myStmt.close();
         System.out.println("Registration Sucessfull");
         System.out.println("\nConnecting To RMI Server...\n");
         Thread.sleep(5000);
         System.out.println("Enter the username : ");
         String userName = scanner.nextLine();
         
         System.out.println("Enter the password : ");
         String logs = scanner.nextLine();
         boolean status = stub.authenticate(userName, logs);
          
         if(status) {
            System.out.println("You are an authorized user...");
            Thread.sleep(5000);
            String s = "yes";
      try{
            myConn = DriverManager.getConnection(url, user, password);
            System.out.println("Do you want to apply for a Hostel Room");
            String k = scanner.nextLine();
            if(k.equals(s)){
               int count = 0;
               System.out.println("Enter your Details for Room Alllocation");
               System.out.println("Enter your Room No: ");
               int roomId = scanner.nextInt();
               scanner.nextLine();
               System.out.println("Enter the Full Name:");
               String fName = scanner.nextLine();
               System.out.println("2 Seats are Available");
               System.out.println("Fees per month:Rs 4000");
               System.out.println("Duration/Months: ");
               int duration = scanner.nextInt();
               scanner.nextLine();
               System.out.println("Address:");
               String address = scanner.nextLine();
               
               String sq = "insert into room_data "
               + " (Room_id,Full_Name,Duration,Address)" + " values (?, ?, ?, ?)";

         myStmt2 = myConn.prepareStatement(sq);

         // set param values
         myStmt2.setInt(1, roomId);
         myStmt2.setString(2,fName);
         myStmt2.setInt(3, duration);
         myStmt2.setString(4, address);
         myStmt2.executeUpdate();
         myStmt2.close();
         count++;
            // Do Something....
         } else {
            
            System.out.println("Unauthorized Login Attempt");
         }
        }catch(Exception e){System.out.println(e);} 
      }
        myConn.close();
        scanner.close();
         
      }
      } catch (Exception exc) {
         exc.printStackTrace();
      } 
   } 
}