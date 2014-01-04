package mediator;

public interface Mediator {
	 public abstract void register(Colleague colleague);
	 public abstract void ColleagueChanged(Colleague colleague);
}
