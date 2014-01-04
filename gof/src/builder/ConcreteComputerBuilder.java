package builder;


//ֻ֪������˳�򣬲�֪����ι��졣
public class ConcreteComputerBuilder implements ComputerBuilder{
	
	
	private Computer computer = new Computer();

	public void buildCpu() {
		computer.setCpu(new Cpu("a special cup"));
	}

	public void buildRam() {
		computer.setRam(new Ram("a special ram"));
	}

	public Computer getComputer() {
		return this.computer;
	}

}
