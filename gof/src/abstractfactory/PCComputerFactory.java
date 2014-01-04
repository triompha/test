package abstractfactory;

public class PCComputerFactory implements ComputerFactory{
	public	Cpu createCpu(){
		return new PCCpu();
	}
	public  Ram createRam(){
		return new PCRam();
	}
}
