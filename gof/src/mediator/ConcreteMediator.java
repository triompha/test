package mediator;

import java.util.ArrayList;

public class ConcreteMediator implements Mediator {

	private ArrayList<Colleague> colleagueList = new ArrayList<Colleague>();

	public void ColleagueChanged(Colleague colleague) {
		for (int i = 0; i < colleagueList.size(); i++) {
			if (colleagueList.get(i) != colleague) {
				colleagueList.get(i).action();
			}
		}

	}

	public void register(Colleague colleague) {
		colleagueList.add(colleague);
	}

}
