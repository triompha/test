package chainofresponsibility;

public interface StringFilter {
	public String doFilter(String chainString ,StringFilterChain chain);
}
