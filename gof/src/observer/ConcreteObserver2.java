package observer;

import java.util.Observable;
import java.util.Observer;

public class ConcreteObserver2 implements Observer{

	public void update(Observable o, Object arg) {
		System.out.println("ConcreteObserver2 called");
	}

}
