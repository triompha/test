package test;

public class DrinkBeer {

	int beer = 0;
	int bottle = 0;
	int cap = 0;

	int total = 0;

	public void drink() {
		if (beer > 0) {
			total += beer;
			bottle+=beer;
			cap+=beer;
			beer = 0;
			if (bottle >= 2 || cap >= 4) {
				change();
			}else{
				System.out.println("completed   total drink:" + total);
			}
		}

	}

	public void change() {
		beer += (bottle / 2);
		bottle %= 2;
		beer += (cap / 4);
		cap %= 4;
		if (beer > 0) {
			drink();
		} else {
			System.out.println("completed   total drink:" + total);
		}

	}

	public DrinkBeer(int init) {
		this.beer = init;
	}

	public static void main(String[] args) {
		DrinkBeer drinkBeer = new DrinkBeer(10);
		drinkBeer.drink();
	}

}
