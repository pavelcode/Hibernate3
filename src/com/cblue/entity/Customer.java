package com.cblue.entity;



/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer  implements java.io.Serializable {


	private int cid;
	private String cname;
	private int age;
	
    // Fields    

     public Customer(String cname, int age) {
		super();
		this.cname = cname;
		this.age = age;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + "]";
	}

	
     public Customer(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}

	


    // Constructors

    /** default constructor */
    public Customer() {
    }

    
    /** full constructor */
    public Customer(String cname) {
        this.cname = cname;
    }

   
    // Property accessors

    public int getCid() {
        return this.cid;
    }
    
    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return this.cname;
    }
    
    public void setCname(String cname) {
        this.cname = cname;
    }
   








}