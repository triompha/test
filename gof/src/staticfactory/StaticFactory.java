package staticfactory;

public class StaticFactory {
	public static Mobile callMobile(String clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return (Mobile) Class.forName(clazz).newInstance();
		
		
		//或者用一下方法来实现
//		if("nokia".equals(clazz)){
//			return new NokiaMobile();
//		}else if ("moto".equals(clazz)) {
//			return new MotoMobile();
//		}
		
		
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Mobile mobile = StaticFactory.callMobile(NokiaMobile.class.getName());
		System.out.println(mobile.call());
		
	}
}
