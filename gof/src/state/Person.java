package state;

public class Person {
	
	private State state = new NormalState();

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public String shot(){
		return state.shot();
	}

}
