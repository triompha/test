package iterator;

public interface Aggregate<E>{
	/**
	 * Iterator�ķ�����Aggregate����
	 */
	public Iterator<E> createIterator(); 
	
	public int length();
	
	public E get(int index);
	
	public void add(E obj);
}