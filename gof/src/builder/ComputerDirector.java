package builder;

public class ComputerDirector {
	public Computer makeComputer(ComputerBuilder builder){
		//���ȹ���CPU
		builder.buildCpu();
		//��ι���RAM
		builder.buildRam();
		return builder.getComputer();
	}

}
