package adapter.brunch1;

/*****
 * �������࣬�û��� �û���Ҫ���õĽ�ڵļ̳У����ࣩ����ʵ��ԭ���ʹ��ڵĹ���
 * @author zyzhao
 *
 */

public class Adapter extends Adaptee implements Target {
	public void request() {
		this.specificRequest();
	}
}
