import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class InstServer{ 
   public InstServer() {} 
   public static void main(String args[]) { 
      try { 
         // Instantiating the implementation class 
         InstImpl obj = new InstImpl(); 
         // Exporting the object of implementation class (
            //here we are exporting the remote object to the stub) 
         Inst stub = (Inst) UnicastRemoteObject.exportObject(obj, 0);  
         
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(); 
         
         registry.bind("Inst", stub);  
         System.err.println("Server ready"); 
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   }  
}