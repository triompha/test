package test.rmi;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Test {
	
	public static void main(String[] args) { 

		try { 

		String host = (args.length > 0) ? args[0] : "127.0.0.1"; //从命令行读取远程主机名 

		//通过URL在远程主机上查找对象，并把它转化为本地接口Rem类型 

		Rem remObject=(Rem)Naming.lookup("rmi://"+host + ":56720/Rem"); 

		System.out.println(remObject.getMessage()); //调用远程对象的方法 

		} catch(RemoteException re) {System.out.println("RemoteException: " + re); 

		} catch(NotBoundException nbe) {System.out.println("NotBoundException: " + nbe); 

		} catch(MalformedURLException mfe){System.out.println("MalformedURLException:"+ mfe); 

		}}
}
