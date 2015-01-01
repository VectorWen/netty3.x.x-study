package com.vector.netty.one.echoobject;

public class Person {
	private int id;
	private String name;
	private String passwd;
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", passwd=" + passwd
				+ "]";
	}
	public Person(int id, String name, String passwd) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
	}
	
}
