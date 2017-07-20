package com.cblue.entity.onetone;

import java.util.Set;

public class Person implements java.io.Serializable {

	  private int pid;  
	  
      private String pname; 
      
     private Set<IDCard> idCards;

	public Set<IDCard> getIdCards() {
		return idCards;
	}







	public void setIdCards(Set<IDCard> idCards) {
		this.idCards = idCards;
	}







	public Person() {
		super();
	  }
      
      


	

	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	
      
}
