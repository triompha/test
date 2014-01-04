package builder;

public class Test {
	public static void main(String[] args) {
		ComputerBuilder builder = new ConcreteComputerBuilder();
		ComputerDirector director = new ComputerDirector();
		Computer computer =	director.makeComputer(builder);
		System.out.println(computer.getCpu().getName());
		System.out.println(computer.getRam().getName());
	}
}
