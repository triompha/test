package singleton;

public class Singleton {
	
	//�ڲ�Ψһ��̬ʵ��
	private static Singleton instance = new Singleton();

	//��װ���췽����ֻ���ڲ�ʹ��
	private Singleton(){
	}
	
	//�ṩ�ⲿ���ʵ��Ψһ����
	public static  Singleton getInstance(){
		return instance;
	}
	
}
