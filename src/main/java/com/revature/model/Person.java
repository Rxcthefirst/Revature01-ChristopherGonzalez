package com.revature.model;

/*
 * I typically create my Java models as "Java Beans". A Java Bean typically includes:
 * 
 * 1) Private fields
 * 2) a No-args constructor
 * 3) a constructor using all of the fields
 * 4) getters and setters
 * 5) a hashCode and equals method
 * 6) a toString method
 * 7) Technically, a Java Bean should implement the Serializable interface, but this is a common convention
 */
public class Person {

	private int id;
	private String name;
	private int age;

	public Person() {
		super();
		// TODO Auto-generated constructor stub

	}

	public Person(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
