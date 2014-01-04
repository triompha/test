package composite;

public class Leaf extends Component {

	String name ;
	public Leaf(String name){
		this.name = name ;
	}
	
	@Override
	public void operation() {
		System.out.println("I'm leaf :"  + name );
	}

}
