package memento;

public class Test {

	public static void main(String[] args) {
		Originator originator = new Originator();
		originator.setState(20);
		originator.setRecover();
		originator.setState(100);
		originator.recover();
		
		System.out.println(originator.getState());
		
		
	}
	
}
