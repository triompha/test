package observer;

import java.util.Observable;
import java.util.Observer;

public class ConcreteObserver1 implements Observer {

	public void update(Observable o, Object arg) {
		System.out.println("ConcreteObserver1 called");
	}

}
