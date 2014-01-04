package test.rmi;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemImpl extends UnicastRemoteObject implements Rem { 
	private static final long serialVersionUID = 1L; 
	//构造函数抛出RemoteException异常 
	public RemImpl() throws RemoteException {} 
	//向RMI客户返回一个消息串 
	public String getMessage() throws RemoteException { 

	return("Here is a remote message."); 
	} 
	} 
