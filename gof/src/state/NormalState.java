package state;

public class NormalState implements State {

	public String shot() {
		System.out.println("Normal shot . Not good ,not bad");
		return "normal";
	}

}
