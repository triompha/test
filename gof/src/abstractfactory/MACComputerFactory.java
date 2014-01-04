package abstractfactory;

public class MACComputerFactory implements ComputerFactory {
	public	Cpu createCpu(){
		return new MACCpu();
	}
	public  Ram createRam(){
		return new MACRam();
	}
}
