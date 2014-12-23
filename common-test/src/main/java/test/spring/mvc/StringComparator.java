package test.spring.mvc;


import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component
public class StringComparator implements Comparator<String>{

	public int compare(String o1, String o2) {
		
		return o1.compareTo(o2);
	}
	
}
