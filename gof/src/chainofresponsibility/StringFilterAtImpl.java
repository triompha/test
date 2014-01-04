package chainofresponsibility;

public class StringFilterAtImpl implements StringFilter{

	public String doFilter(String chainString, StringFilterChain chain) {
		return chain.doChain((chainString.replaceAll("@", "X")));
	}

}
