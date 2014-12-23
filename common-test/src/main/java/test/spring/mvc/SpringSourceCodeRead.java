package test.spring.mvc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.dubbo.demo.DemoService;

public class SpringSourceCodeRead {

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:application-context.xml");
		DemoService demoService =   (DemoService) ctx.getBean("demoService");
		System.out.println(demoService.sayHello("asdf"));
	}
	
}
