package prototype;

public class Computer implements Cloneable{
	private String RAM ;
	private String CPU;
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String ram) {
		RAM = ram;
	}
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String cpu) {
		CPU = cpu;
	}
	public Object clone(){
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public String getString(){
		return "CPU:"+this.CPU+"_"+"RAM:"+this.RAM;
	}
}
