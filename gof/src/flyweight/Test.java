package flyweight;

public class Test {
	public static void main(String[] args) {
		ExtrinsicState state1 = new ExtrinsicState();
		state1.setSubject("��Ŀ1");
		state1.setLocation("�Ϻ�");
		ExtrinsicState state2 = new ExtrinsicState();
		state2.setSubject("��Ŀ2");
		state2.setLocation("����");
		SignInfoFactory.getSignInfo(state1);
		SignInfoFactory.getSignInfo(state2);
		long currentTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			SignInfoFactory.getSignInfo(state1);
			SignInfoFactory.getSignInfo(state2);
		}
		long tailTime = System.currentTimeMillis();
		System.out.println("ִ��ʱ�䣺" + (tailTime - currentTime) + " ms");
	}
}
