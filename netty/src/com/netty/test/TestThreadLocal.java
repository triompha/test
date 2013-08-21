package com.netty.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestThreadLocal<T> {
	private static ThreadLocal<Map<String, Object>> instance = new ThreadLocal<Map<String,Object>>();


	public static void set(String key , Object obj){
		Map<String, Object> map = 	instance.get();
		if(map ==null){
			map = new HashMap<String, Object>();
			instance.set(map);
		}
		map.put(key, obj);
	}

	public static Object get(String key){
		Map<String, Object> map = 	instance.get();
		if(map!=null){
			return map.get(key);
		}
		return null;
	}

	public static void main(String[] args) {
		String xx = "xxxxxxx";
		TestThreadLocal.set("key1",xx);
		System.out.println(TestThreadLocal.get("key1"));
		List<String> arr = new ArrayList<String>();
		arr.add(xx);
		TestThreadLocal.set("key2",arr);
		System.out.println(TestThreadLocal.get("key2"));


	}

}
