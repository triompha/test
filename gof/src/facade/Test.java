package facade;



//门面模式个人感觉就想死与一个主管，协调将所有的子功能全完成了就行了
//对外提供一个功能接口，这个功能借口，是对外面要完成的一件事。
public class Test {
	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.bookRoom();
		facade.bookRoom();
		facade.bookRoom();
		facade.bookRoom();
		facade.bookRoom();
		facade.bookRoom();
		facade.bookRoom();
		facade.bookRoom();
	}
}
