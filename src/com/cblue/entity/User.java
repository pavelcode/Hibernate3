package com.cblue.entity;

public class User implements java.io.Serializable {

	private int uid;
	private String name;
	private int age;

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", age=" + age + "]";
	}

	public User() {
		super();
	}

	public User( String name, int age) {
		this.name = name;
		this.age = age;
	}

	public User(int uid, String name, int age) {
		super();
		this.uid = uid;
		this.name = name;
		this.age = age;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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
}
