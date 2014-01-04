package chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

public class StringFilterChainImpl implements StringFilterChain {
	
	private	List<StringFilter> filters = new ArrayList<StringFilter>(0);
	private int pos = 0 ;
	private int n = 0 ;
	
	public void addFilter(StringFilter filter){
		filters.add(filter);
		n = filters.size();
	}
	
	public String doChain(String chainString) {
		if(pos < n){
		    return	filters.get(pos++).doFilter(chainString, this);
		}
		return chainString;
	}
}
