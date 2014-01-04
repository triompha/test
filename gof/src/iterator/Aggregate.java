package iterator;

public interface Aggregate<E>{
	/**
	 * Iterator的泛型由Aggregate决定
	 */
	public Iterator<E> createIterator(); 
	
	public int length();
	
	public E get(int index);
	
	public void add(E obj);
}