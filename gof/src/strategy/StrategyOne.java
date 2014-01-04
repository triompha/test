package strategy;

public class StrategyOne implements Strategy{

	public String replace(String come) {
		return come.replaceAll("aaa", "oneoneone");
	}
	
}
