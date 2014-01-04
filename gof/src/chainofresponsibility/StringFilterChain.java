package chainofresponsibility;

public interface StringFilterChain {
	public void addFilter(StringFilter filter);
	public String	doChain(String chainString);
}
