package study.hessian;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 3276641446246664L;
	private String name;
	private String length;
	private String color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
