package com.alibaba.dubbo.demo.provider;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.alibaba.dubbo.demo.TestDemoService;

public class Test121 {
	public static void main(String[] args) {
		try {
			TestDemoService demoService = 	(TestDemoService) Naming.lookup("rmi://10.1.10.204:22124/com.alibaba.dubbo.demo.TestDemoService");
			String rst = demoService.sayHello("我勒个去");
			System.out.println(rst);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
