package com.cblue.entity;

public class Teacher {
   private String tid;
   private String name;
public String getTid() {
	return tid;
}
public void setTid(String tid) {
	this.tid = tid;
}
@Override
public String toString() {
	return "Teacher [tid=" + tid + ", name=" + name + "]";
}
public Teacher(String name) {
	super();
	this.name = name;
}
public Teacher(String tid, String name) {
	super();
	this.tid = tid;
	this.name = name;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
