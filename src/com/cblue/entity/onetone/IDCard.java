package com.cblue.entity.onetone;

public class IDCard implements java.io.Serializable {

	 private int cid;  
	  
     private int cnumber;  //身份证号
     
     private Person person;
     
    
	@Override
	public String toString() {
		return "IDCard [cid=" + cid + ", cnumber=" + cnumber + ", person="
				+ person + "]";
	}

	public IDCard() {
		super();
	}

	
	public IDCard(int cnumber) {
		super();
		this.cnumber = cnumber;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCnumber() {
		return cnumber;
	}

	public void setCnumber(int cnumber) {
		this.cnumber = cnumber;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
 
}
