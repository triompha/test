package test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileTest {
	
	public static void main(String[] args) throws IOException {
		byte[] buffer = new byte[1024];
		StringBuffer sb = new StringBuffer();
		
		InputStream is = new FileInputStream(new File("/home/triompha/hs_err_pid2519.log"));
		
		int a = is.read();
		
		while (a>0) {
			sb.append((char)a);
			a = is.read();
		}
		System.out.println(sb);
	}

}
