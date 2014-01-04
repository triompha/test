package facade;

public class Facade {
	
	Hotel1 hotel1 = new Hotel1();
	Hotel2 hotel2 = new Hotel2();
	public void bookRoom(){
		if(hotel1.hasRoom()){
			hotel1.bookRoom();
		}else if(hotel2.hasRoom()){
			hotel2.bookRoom();
		}
	}
}
