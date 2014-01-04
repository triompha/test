package proxy;

public class SubjectImpl implements Subject {

	public void request() {
		System.out.println("From real subject."); 
	}
}
