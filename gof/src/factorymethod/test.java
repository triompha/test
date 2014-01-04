package factorymethod;

public class test {
	public static void main(String[] args) {
		MobileFactory factory = new NokiaMobileFactory();
		Mobile mobile = factory.createMobile();
		System.out.println(mobile.call());
	}

}
