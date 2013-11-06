package test.jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class M1905Worm {
	private List<Long> users = new ArrayList<Long>();
	private Map<String, Long> map = new HashMap<String, Long>();
	private Set<String> urls = new HashSet<String>();
	
	
	String searchMainReg = "<html(.*)</html>";
	
	private String setIP = "127.0.0.1";
	private String searchURL = "http://www.m1905.com/mdb/film/comment/";
	
	public void init(){
		
	}
	public void loadURLs(){
		
		String searchContext = "";
		
		URL url;
		InputStreamReader isr = null;
		BufferedReader br = null;
		byte bytes[] = new byte[1024 * 100];
		try {
			url = new URL(searchURL);
			HttpURLConnection conn = (HttpURLConnection) url .openConnection();
			isr = new InputStreamReader(conn .getInputStream(),"UTF-8");
			br = new BufferedReader(isr);
			InputStream inputStream = conn.getInputStream();
			int index = 0;
			int count = inputStream.read(bytes, index, 1024 * 100);
			int inc = 100;
			while (count != -1) {
				byte[] newBytes = new byte[bytes.length + inc];
				System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
				bytes = newBytes;
				index += count;
				count = inputStream.read(bytes, index, 100);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		try {
			searchContext = new String(bytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String newFilmEntry = "";
		String newEntry = "";
		String hotEntry = "";
		
		System.out.println("html : " + searchContext);
		
		Pattern pattern = Pattern.compile(searchMainReg);
		Matcher matcher = pattern.matcher(searchContext);
		
		System.out.println("reg : " + searchMainReg);
		
		
		while (matcher.find()) {
			newFilmEntry = matcher.group(1);
//			newEntry = matcher.group(2);
//			hotEntry = matcher.group(3);
			
			System.out.println(newFilmEntry);
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		new M1905Worm().loadURLs();
	}
	
	public void loadUser(){
		
	}

}
