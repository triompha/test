package iterator;


public interface Iterator<E>{
	public E first();
	
	public boolean hasNext();
	
	public E next();
	
	public E current();
}