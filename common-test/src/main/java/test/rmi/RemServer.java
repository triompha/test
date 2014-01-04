package test.rmi;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemServer { 

	public static void main(String[] args) { 

	try { 
//	System.setProperty("java.security.policy","*.policy");//设置安全策略 
	RemImpl localObject = new RemImpl(); //生成远程对象实现的一个实例 
	Registry reg = LocateRegistry.createRegistry(8000); 
//	System.setSecurityManager(new RMISecurityManager());      
	reg.rebind("Rem", localObject); //将远程对象实例绑定到rmi:///Rem上     

	}catch(Exception ex){ 
	ex.printStackTrace(); 
	} 
	}} 