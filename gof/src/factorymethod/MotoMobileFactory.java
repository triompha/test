package factorymethod;

public class MotoMobileFactory implements MobileFactory{

	public Mobile createMobile() {
		return  new MotoMobile();
	}

}
