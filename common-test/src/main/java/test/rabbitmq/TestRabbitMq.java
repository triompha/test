package test.rabbitmq;

import java.io.IOException;  
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory; 

public class TestRabbitMq {
	private final static String QUEUE_NAME = "hello";  
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.99.100");  
//        factory.setPort(32769);
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = "Hello World";  
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
  
        System.out.println("  Sent: '" + message + "'");  
//        channel.close();  
//        connection.close();  
	}
}
