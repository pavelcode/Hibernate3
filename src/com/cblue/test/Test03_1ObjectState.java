package com.cblue.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.Customer;
import com.cblue.entity.Student;


//hibernate对象的状态
public class Test03_1ObjectState {

	/**
	 * hibernate有三种状态，transient(瞬时状态)，persistent(持久化状态)以及detached(离线状态)
	 * 瞬时状态就是刚new出来一个对象，还没有被保存到数据库中，
                  持久化状态就是已经被保存到数据库中
                   离线状态就是数据库中有，但是session中不存在该对象
	 */
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
     public void testSaveStudent(){
    	 Transaction transaction = session.beginTransaction();
    	 Student student = new Student("zhangsan1");//瞬时状态
    	 session.save(student); //持久化状态
    	 transaction.commit();	//持久化状态 
    	 session.close();//离线状态
     }
     
     /**
      * 如果一个持久化对象，可以直接修改
      */
     @Test 
     public void  testUpdateStudent(){
    	 Transaction transaction = session.beginTransaction();
    	 Student student = (Student) session.get(Student.class,1); //得到一个持久状态对象
    	 student.setName("wangwu");
    	 //session.update(student);  //把一个对象变成持久化状态对象
    	 transaction.commit();	
    	 session.close();
     }
     
   
     
    
     
}
