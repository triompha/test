package memento;

public class Originator {
	private int state = 90 ;
	private Maintain maintain = new Maintain();
	
	public Originator(int state){
		this.state = state ;
	}
	public Originator(){
	}
	
	public void setState(int state){
		this.state = state;
	}
	public void setRecover(){
		this.maintain.recoverState = state ;
	}
	
	public void recover(){
		this.state = this.maintain.recoverState;
	}
	
	
	private	class  Maintain{
		private int recoverState;
	}




	public int getState() {
		return state;
	}

}
