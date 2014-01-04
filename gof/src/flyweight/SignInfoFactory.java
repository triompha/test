package flyweight;


import java.util.HashMap;

public class SignInfoFactory {
	// ������Ϣ�Ķ��󹤳�
	private static HashMap<ExtrinsicState, SignInfo> pool = new HashMap<ExtrinsicState, SignInfo>();

	// ������Ϣ�Ķ��󹤳�
	
	public static SignInfo getSignInfo() {
		return new SignInfo();
	}

	// �ӳ��л�ö���
	public static SignInfo getSignInfo(ExtrinsicState key) {
		// ���÷��ض���
		SignInfo result = null;
		// ����û�иö������������������
		if (!pool.containsKey(key)) {
			System.out.println(key + "----�������󣬲����õ�����");
			result = new SignInfo();
			pool.put(key, result);
		} else {
			result = pool.get(key);
			System.out.println(key + "---ֱ�Ӵ�ֱ����ȡ��");
		}
		return result;
	}
}