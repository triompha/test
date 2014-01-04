package iterator;

public class ConcreteIterator<E> implements Iterator<E>{
	
	
	private Aggregate<E> aggregate;
	private int currentIndex = 0; //±éÀú×´Ì¬ 
	
	public ConcreteIterator(Aggregate<E> aggregate){
		this.aggregate = aggregate;
	}

	public E first() {
		currentIndex = 0;
		if(hasNext()){
			return aggregate.get(currentIndex);
		}else{
			return null;
		}
	}

	public boolean hasNext() {
		return (currentIndex < aggregate.length());
	}
	
	public E next() {
		currentIndex++;
		if(hasNext()){
			return aggregate.get(currentIndex);
		}else{
			return null;
		}
	}
	
	public E current(){
		return aggregate.get(currentIndex);
	}
}
