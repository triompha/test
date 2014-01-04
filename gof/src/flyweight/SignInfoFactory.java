package flyweight;


import java.util.HashMap;

public class SignInfoFactory {
	// 报名信息的对象工厂
	private static HashMap<ExtrinsicState, SignInfo> pool = new HashMap<ExtrinsicState, SignInfo>();

	// 报名信息的对象工厂
	
	public static SignInfo getSignInfo() {
		return new SignInfo();
	}

	// 从池中获得对象
	public static SignInfo getSignInfo(ExtrinsicState key) {
		// 设置返回对象
		SignInfo result = null;
		// 池中没有该对象，则建立，并放入池中
		if (!pool.containsKey(key)) {
			System.out.println(key + "----建立对象，并放置到池中");
			result = new SignInfo();
			pool.put(key, result);
		} else {
			result = pool.get(key);
			System.out.println(key + "---直接从直池中取得");
		}
		return result;
	}
}