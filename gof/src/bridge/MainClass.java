package bridge;

//桥接模式的核心在于
// 它的主要特点是把抽象（abstraction）与行为实现（implementation）分离开来
// 这样就提高了复用和减少了类的亮
public class MainClass {
	public static void main(String[] args) {
		
		Engine engine2000 = new Engine2000();
		Engine engine2200 = new Engine2200();
		
		Car car1 = new Bus(engine2000);
		car1.installEngine();
		
		Car car2 = new Bus(engine2200);
		car2.installEngine();
		
		Car jeep1 = new Jeep(engine2000);
		jeep1.installEngine();
		
		Car jeep2 = new Jeep(engine2200);
		jeep2.installEngine();
		
	}
}