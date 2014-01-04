package iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate<E> implements Aggregate<E>{
	private List<E> items = new ArrayList<E>();
	
	public Iterator<E> createIterator() {
		return new ConcreteIterator<E>(this); 
	}
	
	public int length(){
		return items.size();
	}
	
	public E get(int index){
		return (E)items.get(index);
	}
	
	public void add(E obj){
		items.add(obj);
	}
}