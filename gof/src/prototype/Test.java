package prototype;

public class Test {
	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.setCPU("我的CUP");
		computer.setRAM("我的RAM");
		System.out.println(computer+ computer.getString());
		
		Computer computer2 = (Computer) computer.clone();
		System.out.println(computer2 + computer2.getString());
		
		
		Computer computer3 = computer;
		System.out.println(computer3 + computer3.getString());
		
	}

}
