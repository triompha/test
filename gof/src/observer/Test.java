package observer;

public class Test {
	public static void main(String[] args) {
		Entity entity = new Entity();
		entity.addObserver(new ConcreteObserver1());
		entity.addObserver(new ConcreteObserver2());
		entity.count(3);
	}
}
