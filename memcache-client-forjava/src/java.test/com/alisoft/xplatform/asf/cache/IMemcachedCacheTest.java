/**
 * 
 */
package com.alisoft.xplatform.asf.cache;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcacheStats;
import com.alisoft.xplatform.asf.cache.memcached.MemcacheStatsSlab;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedResponse;
import com.alisoft.xplatform.asf.cache.memcached.MemcacheStatsSlab.Slab;

/**
 * @author wenchu.cenwc
 *
 */
public class IMemcachedCacheTest
{
	
	static ICacheManager<IMemcachedCache> manager;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		manager = CacheUtil.getCacheManager(IMemcachedCache.class,
			MemcachedCacheManager.class.getName());
		manager.setConfigFile("memcached_cluster.xml");
		manager.setResponseStatInterval(5*1000);
		manager.start();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		manager.stop();
	}
	
	/**
	 * Test method for {@link com.alisoft.xplatform.asf.cache.ICache#get(java.lang.Object)}.
	 */
	@Test
	public void testGet()
	{
		try
		{
			IMemcachedCache cache = manager.getCache("mclient");
			
			for(String key :cache.keySet()){
				System.out.println(key);
			}
			
//			cache.remove("key1");
//			cache.remove("key2���");
			
//			cache.put("key1", "xxxxxdfww");
//			cache.put("key2���", "���123");
//			System.out.println(cache.get("key2"));
//			Assert.assertEquals(cache.get("key1"),"1");
//			Assert.assertEquals(cache.get("key2���"),"���123");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
}
