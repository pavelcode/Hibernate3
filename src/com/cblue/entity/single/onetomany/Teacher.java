package com.cblue.entity.single.onetomany;

import java.util.Set;

public class Teacher {
	
	private int tid;
	
	private String tname;
	
	private Set<Student> students;
	
	
	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}


	public String getTname() {
		return tname;
	}


	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + "]";
	}


	public void setTname(String tname) {
		this.tname = tname;
	}


	public Set<Student> getStudents() {
		return students;
	}


	public void setStudents(Set<Student> students) {
		this.students = students;
	}


	

}
