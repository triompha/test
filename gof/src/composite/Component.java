package composite;

public abstract class Component {
	
	public void add(Component component){}

	public void remove(Component component){}

	public Component getChild(int i) {
		return null;
	}

	public abstract void operation();

}
