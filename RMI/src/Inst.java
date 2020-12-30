import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.*;

// Creating Remote interface for our application 
public interface Inst extends Remote {  
   public boolean instauthenticate(int id, String password) throws RemoteException ; 
   public List<Student> getStudents() throws Exception;  
   public List<Complaint> getComplaints() throws Exception;
}