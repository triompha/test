package test.memcache.MemcachedJavaClient;

import com.whalin.MemCached.*;

public class MyClass {
	// create a static client as most installs only need
	// a single instance
	protected static MemCachedClient mcc = new MemCachedClient();
	// set up connection pool once at class load
	static {
		// server list and weights10.22.10.144:11211(
		String[] servers = {"10.22.10.144:11211"};
		// String[] servers = {"10.15.9.33:11212", "10.15.9.33:11213" };10.15.9.33:11212
		Integer[] weights = {3, 3, 2};
		// grab an instance of our connection pool
		SockIOPool pool = SockIOPool.getInstance();
		// set the servers and the weights
		pool.setServers(servers);
		// pool.setWeights(weights);
		// set some basic pool settings
		// 5 initial, 5 min, and 250 max conns
		// and set the max idle time for a conn
		// to 6 hours
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(30);
		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		// initialize the connection pool
		pool.setNagle(true);
		// pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
		pool.initialize();
		// lets set some compression on for the client
		// compress anything larger than 64k
	}

	// from here on down, you can call any of the client calls
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			mcc.get("foo" + i);
			// mcc.get("foo" + i);// , "this is the valueaaaa" + i
			//			mcc.set("foo" + i, "this is the valueaaaa" + i + 1);//
			//			mcc.set("foo" + i, "this" + i + 1);//
			//			mcc.set("foo" + i, new ArrayList());//
			//			mcc.set("foo" + i, new TestBean());

		}
		// if (i < 10 || i > 990) {
		// mcc.delete("foo" + i);
		// }
		//		Map<Integer, NodeStatusSlab> itemMap = new HashMap<Integer, NodeStatusSlab>();
		//		Map<String, Map<String, String>> statsItems = mcc.statsItems();
		//		System.out.println(statsItems.get("10.10.77.192:11211"));
		//		Map<String, String> item = statsItems.get("10.10.77.192:11211");
		//
		//		System.out.println(item.get("items:0:evicted"));

		// for (Entry<String, String> entry : item.entrySet()) {
		// int slabId = 0;
		// try {
		// slabId = Integer.parseInt(StringUtils.substringBefore(entry
		// .getKey(), ":"));
		// } catch (Exception e) {
		// continue;
		// }
		// String subValue = entry.getValue().replaceAll("\\r\\n", "");
		// NodeStatusSlab nodeStatusItem = itemMap.get(slabId);
		// if (nodeStatusItem == null) {
		// nodeStatusItem = new NodeStatusSlab(slabId);
		// itemMap.put(nodeStatusItem.getSlabId(), nodeStatusItem);
		// }
		// try {
		// String key = StringUtils
		// .substringAfterLast(entry.getKey(), ":");
		// long value = Long.parseLong(subValue);
		// PropertyUtils.setProperty(nodeStatusItem, key, value);
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// }
		//
		// }
		// System.out.println(itemMap);
		// System.out.println(itemMap);
		// StringBuffer sb = new StringBuffer();
		// for (int i = 0; i < 30000; i++) {
		// sb.append("text111" + i);
		// }
		// System.out.println(sb.toString().length());
		// mcc.set("2", 1);
		// mcc.set("foox1", "f");
		// mcc.get("foox");
		// mcc.set("foox", "f1");
		// mcc.get("foox");
		// String bar = (String) mcc.get("foo");
		// System.out.println(bar);
		//
		// bar = (String) mcc.get("foo");
		// System.out.println(bar);
		// mcc.set("foo", "This is a test String");
		// bar = (String) mcc.get("foo");
		// System.out.println(bar);
		// Map map = mcc.stats(new String[] { ("10.10.83.88:11212") });
		// System.out.println(map);
	}
}