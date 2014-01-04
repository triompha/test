package factorymethod;

public class NokiaMobileFactory implements MobileFactory{
	
	public Mobile createMobile() {
		return new NokiaMobile();
	}

}
