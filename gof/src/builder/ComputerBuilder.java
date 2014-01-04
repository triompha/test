package builder;


//只知道如何构造，不知道构造顺序
public interface ComputerBuilder {
	public void buildCpu();
	public void buildRam();
	public Computer getComputer();
}
