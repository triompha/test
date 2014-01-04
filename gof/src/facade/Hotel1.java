package facade;

public class Hotel1 {
	
	public static int roomCount = 3;
	
	public boolean hasRoom(){
		return roomCount>0 ;
	}
	public void bookRoom(){
		System.out.println("From Hotel1 book room");
		roomCount--;
		return;
	}

}
