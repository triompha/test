package com.alibaba.dubbo.demo.provider;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.alibaba.dubbo.demo.TestDemoService;

public class Test122 {
	public static void main(String[] args) {
		try {
			final RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
			rmiProxyFactoryBean.setServiceUrl("rmi://10.1.10.204:22124/com.alibaba.dubbo.demo.TestDemoService");
			rmiProxyFactoryBean.setServiceInterface(com.alibaba.dubbo.demo.TestDemoService.class);
			rmiProxyFactoryBean.setCacheStub(true);
			rmiProxyFactoryBean.setLookupStubOnStartup(true);
			rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
			rmiProxyFactoryBean.afterPropertiesSet();
			TestDemoService testDemoService = (TestDemoService) rmiProxyFactoryBean.getObject();
			System.out.println(testDemoService.sayHello("fbw"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
