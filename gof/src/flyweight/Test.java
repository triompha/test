package flyweight;

public class Test {
	public static void main(String[] args) {
		ExtrinsicState state1 = new ExtrinsicState();
		state1.setSubject("科目1");
		state1.setLocation("上海");
		ExtrinsicState state2 = new ExtrinsicState();
		state2.setSubject("科目2");
		state2.setLocation("北京");
		SignInfoFactory.getSignInfo(state1);
		SignInfoFactory.getSignInfo(state2);
		long currentTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			SignInfoFactory.getSignInfo(state1);
			SignInfoFactory.getSignInfo(state2);
		}
		long tailTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (tailTime - currentTime) + " ms");
	}
}
