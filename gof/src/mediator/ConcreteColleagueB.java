package mediator;

public class ConcreteColleagueB extends Colleague {

	public ConcreteColleagueB(Mediator mediator) {
		super(mediator);
		mediator.register(this);
	}

	@Override
	public void action() {
		System.out.println("BBBBBBBBBBBBBBB");

	}

}
