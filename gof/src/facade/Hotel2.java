package facade;

public class Hotel2 {
	
	public static int roomCount = 3;
	
	public boolean hasRoom(){
		return roomCount>0 ;
	}
	public void bookRoom(){
		System.out.println("From Hotel2 book room");
		roomCount--;
		return;
	}

}
