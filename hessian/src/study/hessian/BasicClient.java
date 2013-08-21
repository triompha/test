package study.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

public class BasicClient {
	public static void main(String[] args) throws Exception {
		String url = "http://127.0.0.1:8080/hessian/hello";
		HessianProxyFactory factory = new HessianProxyFactory();
		IBasic basic = (IBasic) factory.create(IBasic.class, url);
		System.out.println(basic);
		Person person = basic.getPerson();
		System.out.println("Hello: " + basic.hello());
		System.out.println("Hello: " + person.toString());
	}
}
