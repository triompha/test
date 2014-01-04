package abstractfactory;

public class Test {
	public static void main(String[] args) {
		ComputerFactory computerFac = new PCComputerFactory();
		@SuppressWarnings("unused")
		Cpu cpu =  computerFac.createCpu();
		@SuppressWarnings("unused")
		Ram ram = computerFac.createRam();
	}

}
