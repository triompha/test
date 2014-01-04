package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//AOP编程核心点。。。解耦核心点
public class Test {
	
	public static void main(String[] args) {
		
		  Subject rs = new SubjectImpl();  //在这里指定被代理类
	      InvocationHandler ds = new DynamicSubject(rs);  //初始化代理类
	      Class cls = rs.getClass();
	      Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(),ds );
	      subject.request(); 
	}
}
