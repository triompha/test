package strategy;

public class Context {
	private	String srcString;
	private Strategy strategy;
	
	public String replace(){
		if(srcString == null) srcString = "";
		return strategy.replace(srcString);
	}
	
	public Context(){}
	
	public Context(String srcString, Strategy strategy){
		this.srcString = srcString ;
		this.strategy = strategy ;
	}

	public String getSrcString() {
		return srcString;
	}

	public void setSrcString(String srcString) {
		this.srcString = srcString;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
