package mediator;

public class ConcreteColleagueA extends Colleague {

	public ConcreteColleagueA(Mediator mediator) {
		super(mediator);
		mediator.register(this);
	}

	@Override
	public void action() {
		System.out.println("AAAAAAAAAAAAAAA");
	}

}
