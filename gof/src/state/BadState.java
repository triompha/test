package state;

public class BadState implements State {

	public String shot() {
		System.out.println("Bad shot . you are not yourself today");
		return "bad";
	}

}
