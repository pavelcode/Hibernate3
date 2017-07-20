package com.cblue.entity.onetomany;

import java.util.Set;

public class Department {
	
	private int did;
	private String dname;
	private Set<Employee> employees;
	
	
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public int getDid() {
		return did;
	}
	
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}

}
