package chainofresponsibility;

public class StringFilterAImpl implements StringFilter{

	public String doFilter(String chainString, StringFilterChain chain) {
		return chain.doChain((chainString.replaceAll("a", "X")));
	}

}
