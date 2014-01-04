package composite;


//组合模式就是一个树型数据结构的对象化
public class Test {
	
	
	public static void main(String[] args) {
		Component root = new Composite();
		Component child1 = new Composite();
		Component child2 = new Composite();
		
		
		Component leaf1 = new Leaf("rootLeaf");
		Component child1Leaf1 = new Leaf("child1Leaf1");
		Component child1Leaf2 = new Leaf("child1Leaf2");
		Component child2Leaf1 = new Leaf("child2Leaf1");
		Component child2Leaf2 = new Leaf("child2Leaf2");
		
		child1.add(child1Leaf1);
		child1.add(child1Leaf2);
		
		child2.add(child2Leaf1);
		child2.add(child2Leaf2);
		
		root.add(leaf1);
		root.add(child1);
		root.add(child2);
		
		root.operation();
		
		
	}
}
