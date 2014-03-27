package com.ucfgroup.zookeeper.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.I0Itec.zkclient.IZkChildListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerRegisterUtil {
	
	public static final int initServiceSize = 100;
	
	public static final Logger logger = LoggerFactory.getLogger(ServerRegisterUtil.class);
	private static Map<String, List<String>> servicesCache = new HashMap<String, List<String>>(initServiceSize);
	private static List<String> substribeList = new ArrayList<String>(initServiceSize);
	private static List<String> emptyServices = new ArrayList<String>(0);
	
	private static String getServicePrefix(String serviceName){
		return "/services/"+serviceName+"/servers";
	}
	
	public static void register(String host , int port , String serviceName){
		 ZKClient zkClient =   ZKClient.getInstance();
		 String ipPrefix = getServicePrefix(serviceName);
		 if(!zkClient.exist(ipPrefix)){
			 zkClient.createPersistent(ipPrefix);
		 }
		 
		 if(!zkClient.exist(ipPrefix+"/"+host+":"+port)){
			 zkClient.createEphemeral(ipPrefix+"/"+host+":"+port);
		 }
		 
	}
	
	
	public static void register(int port , String serviceName){
		 String host =  NetUtils.getLocalHost();
		 register(host, port, serviceName);
	}
	
	
	public static List<String> list(String serviceName){
		List<String> servers = servicesCache.get(serviceName);
		System.out.println("servers : " + servers);
		
		if(servers==null){
			ZKClient zkClient =   ZKClient.getInstance();
			servers  = zkClient.getChildren(getServicePrefix(serviceName));
			if(servers==null){
				servers = emptyServices;  //如果没有任何注册服务，则放置空的 列表 list
			}
		}
		
		//如果还没有注册事件，则自动注册子节点更改信息事件
		if(!substribeList.contains(serviceName)){
			subscribe(serviceName);
		}
		return servers;
	}
	
	
	/***
	 * return the first server of the register servers
	 * @param serviceName
	 * @return
	 */
	
	public static String get(String serviceName){
		List<String> servers = list(serviceName);
		String server = null;
		if(servers.size()>0){
			server = servers.get(0);
		}
		return server;
	}
	
	
	public static void subscribe(final String serverName){
		Executors.newFixedThreadPool(1).execute(new Runnable() {
			public void run() {
				ZKClient.getInstance().subscribeChildChanges(getServicePrefix(serverName), new IZkChildListener() {
					public void handleChildChange(String parentPath, List<String> currentChilds)throws Exception {;
						logger.info("remove cache of service :" + servicesCache.remove(serverName));
						System.out.println("info changed");
					}
				});
			}
		});
	}
	
	

}
