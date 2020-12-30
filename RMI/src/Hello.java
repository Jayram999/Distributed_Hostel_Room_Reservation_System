import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.*;

// Creating Remote interface for our application 
public interface Hello extends Remote {  
   public boolean authenticate(String userName, String password) throws RemoteException ; 
   public List<Solution> getSolutions() throws Exception;
}