package test.jmx;

import java.util.Date;

import javax.management.JMX;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.directmemory.jmx.CacheServiceMonitorMBean;
import org.apache.directmemory.measures.Ram;

public class TestJMX {
	
	public static void main(String[] args) throws Exception {

	       final JMXConnector conn = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://10.22.10.161:10086/jmxrmi"));     
	       conn.connect();        
	       final CacheServiceMonitorMBean cacheServiceMonitor = JMX.newMBeanProxy(conn.getMBeanServerConnection(), new ObjectName("CacheServiceMonitorServer:name=direct"), CacheServiceMonitorMBean.class);     
	       System.out.println("堆外容量："+Ram.inMb(cacheServiceMonitor.capacity()));   
	       System.out.println("堆外使用："+Ram.inMb(cacheServiceMonitor.used()));    
	       System.out.println("总键值数："+cacheServiceMonitor.count()+"个"); 
	       System.out.println("get次数："+cacheServiceMonitor.gets()+"次"); 
	       System.out.println("get命中次数："+cacheServiceMonitor.hits()+"次"); 
	       System.out.println("set次数："+cacheServiceMonitor.sets()+"次"); 
	       System.out.println("启动时间："+new Date(cacheServiceMonitor.getStartTime())+""); 
	       conn.close();
	   
	}

}
