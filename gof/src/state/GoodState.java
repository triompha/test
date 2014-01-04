package state;

public class GoodState implements State{

	public String shot() {
		System.out.println("Good shot . well done");
		return "good";
	}

}
