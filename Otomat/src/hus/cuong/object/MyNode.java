package hus.cuong.object;

public class MyNode {

	int id = 0;
	String name;

	public MyNode(String name) {
		this.id++;
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
