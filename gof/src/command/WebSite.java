package command;

import java.util.ArrayList;
import java.util.List;

public class WebSite {
	
	private List<Command> commandList = new ArrayList<Command>();
	
	public void assembly(){
		Command c1 = new UserInterface();
		Command c2 = new Programer();
		Command c3 = new DatabaseManager();
		commandList.add(c1);
		commandList.add(c2);
		commandList.add(c3);
	}
	
	public void build(){
		for(Command command : commandList)
			command.execute();
	}
	
}
