package test;

public class DrinkBeer2 {
	public static int drink(int beer, int bottle, int cap) {
		if ((beer + bottle) < 2 && (beer + cap) < 4) {
			return beer + 0;
		}
		return (beer + drink(((beer + bottle) / 2 + (beer + cap) / 4),
				(beer + bottle) % 2, (beer + cap) % 4));
	}

	public static void main(String[] args) {
		System.out.println("total drink!~" + DrinkBeer2.drink(10, 0, 0));
	}

}
