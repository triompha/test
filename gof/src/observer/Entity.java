package observer;

import java.util.Observable;

//具体的被观察者来通知观察者来执行何操作。。

public class Entity extends Observable{
	public void count(int number){
		for( ; number >=0 ; number-- ){
			setChanged();
			//注意notifyObservers()有两种形式：一种带有参数而另一种没有。当用参数调用notifyObservers( )方法时，该对象被传给观测程序的update( )方法作为其第二个参数。否则，将给update( )方法传递一个null。可以使用第二个参数传递适合于你的应用程序的任何类型的对象。 
			//也就是说notifyObservers()内部实际调用的是notifyObservers(null);
			notifyObservers(number);
		}
	}

}
