package test.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rem extends Remote { 
	public String getMessage() throws RemoteException; 
} 