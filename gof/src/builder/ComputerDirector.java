package builder;

public class ComputerDirector {
	public Computer makeComputer(ComputerBuilder builder){
		//首先构建CPU
		builder.buildCpu();
		//其次构建RAM
		builder.buildRam();
		return builder.getComputer();
	}

}
