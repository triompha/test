package singleton;

public class Singleton {
	
	//内部唯一静态实例
	private static Singleton instance = new Singleton();

	//封装构造方法，只供内部使用
	private Singleton(){
	}
	
	//提供外部获得实例唯一方法
	public static  Singleton getInstance(){
		return instance;
	}
	
}
