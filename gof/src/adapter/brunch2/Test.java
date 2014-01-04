package adapter.brunch2;

//这种方式相对耦合性更低
public class Test {
	public static void main(String[] args) {
		Target target = new Adapter(new Adaptee());
		target.request();
	}
}
