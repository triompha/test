package strategy;

public class Test {
	public static void main(String[] args) {
		Context context = new Context("xaaaxxvwz", new StrategyOne());
		System.out.println(context.replace());
	}
}
