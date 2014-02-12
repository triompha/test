package test.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class TestServlet {
	public static void main(String[] args) throws Exception {
		Server server = new Server(); 
		 Connector connector = new SelectChannelConnector(); 
		 connector.setPort(8080); 
		 server.setConnectors(new Connector[]{ connector }); 
		 ServletContextHandler root = new 
		 ServletContextHandler(null,"/",ServletContextHandler.SESSIONS); 
		 server.setHandler(root); 
		 root.addServlet(new ServletHolder(new HelloServlet()),"/"); 
		 server.start(); 
		 server.join();
		
		
	}
}
