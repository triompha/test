package state;

public class Test {
	public static void main(String[] args) {
		Person person = new Person();
//		person.shot();
		
		person.setState(new GoodState());
		person.shot();
		
	}

}
