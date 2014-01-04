package strategy;

public class StrategyTwo implements Strategy{

	public String replace(String come) {
		return come.replaceAll("aaa", "twotwotwo");
	}

}
