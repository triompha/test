/*
 * Copyright 1999-2011 Alibaba Group.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.demo.provider;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.remoting.rmi.RmiServiceExporter;

import com.alibaba.dubbo.demo.TestDemoService;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

public class TestDemoServiceImpl  implements TestDemoService,Serializable {

	protected TestDemoServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -2649520057250197134L;


	public String sayHello(String name) {
		System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
		return "Hello你信么 " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();
	}


	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

		//		try {
		//创建一个远程对象
		TestDemoService test1 = new TestDemoServiceImpl();
		//本地主机上的远程对象注册表Registry的实例，并指定端口为8888，这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上
		//		LocateRegistry.createRegistry(22124);
		//
		//		//把远程对象注册到RMI注册服务器上，并命名为RHello
		//		//绑定的URL标准格式为：rmi://host:port/name(其中协议名可以省略，下面两种写法都是正确的）
		//		Naming.bind("rmi://10.1.10.228:22124/com.alibaba.dubbo.demo.TestDemoService",test1);
		//		//            Naming.bind("//localhost:8888/RHello",rhello);
		//
		//		System.out.println(">>>>>INFO:远程IHello对象绑定成功！");
		//			try {
		//				Thread.currentThread().sleep(1000*1000*10);
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}


		final RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
		//		rmiServiceExporter.setRegistryPort(22124);
		rmiServiceExporter.setServiceName("com.alibaba.dubbo.demo.TestDemoService");
		rmiServiceExporter.setServiceInterface(TestDemoService.class);
		rmiServiceExporter.setService(test1);
		//		rmiServiceExporter.setRegistryHost("10.2.146.12");
		rmiServiceExporter.setRegistry(LocateRegistry.createRegistry(22124));

		try {
			rmiServiceExporter.afterPropertiesSet();
		} catch (RemoteException e) {
			throw new RpcException(e.getMessage(), e);
		}



		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		} catch (RemoteException e) {
		//			System.out.println("创建远程对象发生异常！");
		//			e.printStackTrace();
		//		} catch (AlreadyBoundException e) {
		//			System.out.println("发生重复绑定对象异常！");
		//			e.printStackTrace();
		//		} catch (MalformedURLException e) {
		//			System.out.println("发生URL畸形异常！");
		//			e.printStackTrace();
		//		}
	}

}