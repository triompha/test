package mediator;

public abstract class Colleague {
	
	 private	Mediator mediator;
	 
	 public Colleague(Mediator mediator){
	  this.mediator=mediator;
	 }
	 
	 public abstract void action();
	 
	 public void changed(){
		 mediator.ColleagueChanged(this);
	 }

}
