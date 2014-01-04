package chainofresponsibility;

public class Test {
	public static void main(String[] args) {
		StringFilter filterA = new StringFilterAImpl();
		StringFilter filterAt = new StringFilterAtImpl();
		StringFilterChain chain = new StringFilterChainImpl();
		chain.addFilter(filterA);
		chain.addFilter(filterAt);
		String theString = "xca@ffxv..c";
		System.out.println(chain.doChain(theString));
		
	}
}
