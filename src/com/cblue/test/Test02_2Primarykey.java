package com.cblue.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.Customer;
import com.cblue.entity.Teacher;

//Hibernate的主键策略
public class Test02_2Primarykey {


    Session session;
	
	@Before
	public void before(){
		//加载了配置文件
		Configuration configuration = new Configuration().configure();
		//获得Session的连接工厂
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//openSession 方法相当于利用session打开了一个数据库的连接
		session = sessionFactory.openSession();
		System.out.println("Session="+session);
	}
	
	
	@Test
	public void testSave() {
		// TODO Auto-generated method stub
		Transaction trans = session.beginTransaction();
		//Customer customer = new Customer("eee"); //increate  identity  uuid
		//Customer customer = new Customer(10,"eee"); //自己分配assigned
		Customer customer = new Customer("eee");
		session.save(customer);
		trans.commit();
		
	}
	
	
	@Test
	public void testSaveUUID() {
		// TODO Auto-generated method stub
		Transaction trans = session.beginTransaction();
		Teacher teacher = new Teacher("zhang");
		session.save(teacher);
		trans.commit();
		
	}
}
