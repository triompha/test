package iterator;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Aggregate<String> a = new ConcreteAggregate<String>();
		a.add("111");
		a.add("222");
		a.add("333");
		
		Iterator<String> it = a.createIterator();
		for(String s=it.first();it.hasNext();s=it.next()){
			System.out.println(s);
		}
		
		java.util.Iterator<String> ix ;
		List<String> list1 = new ArrayList<String>();
		
	}
}