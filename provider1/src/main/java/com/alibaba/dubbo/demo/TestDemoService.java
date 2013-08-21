package com.alibaba.dubbo.demo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestDemoService extends Remote {

	String sayHello(String name) throws RemoteException;

}