package adapter.brunch1;

/*****
 * 适配器类，用户对 用户需要调用的借口的继承（子类），和实现原来就存在的功能
 * @author zyzhao
 *
 */

public class Adapter extends Adaptee implements Target {
	public void request() {
		this.specificRequest();
	}
}
