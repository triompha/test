package adapter.brunch2;

//���ַ�ʽ�������Ը���
public class Test {
	public static void main(String[] args) {
		Target target = new Adapter(new Adaptee());
		target.request();
	}
}
