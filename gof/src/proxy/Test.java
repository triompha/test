package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//AOP��̺��ĵ㡣����������ĵ�
public class Test {
	
	public static void main(String[] args) {
		
		  Subject rs = new SubjectImpl();  //������ָ����������
	      InvocationHandler ds = new DynamicSubject(rs);  //��ʼ��������
	      Class cls = rs.getClass();
	      Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(),ds );
	      subject.request(); 
	}
}
