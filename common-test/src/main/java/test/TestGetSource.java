package test;

import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class TestGetSource {
	
	public static void main(String[] args) throws IOException {
		String simpleName = NioServerSocketChannel.class.getSimpleName();
		URL resource = NioServerSocketChannel.class.getResource(simpleName+".class");
		URL url = new URL(resource.toString().replace(".jar", "-sources.jar").replace(".class", ".java"));
		String string = IOUtils.toString(url.openStream());
		ScanSource.findArgsList(string);
		
	}

}
