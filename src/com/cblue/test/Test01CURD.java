package com.cblue.test;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.User;


//Hibernate实现CRUD
public class Test01CURD {
	
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
	  public void testSave(){
			//开启事物
			Transaction transaction = session.beginTransaction();
			User user = new User(1,"zhaoliu",10);
			session.save(user);
			transaction.commit();
			session.close();
	     }
	  
	  @Test
		public void update() {
			// TODO Auto-generated method stub
			//开启事物
			Transaction transaction = session.beginTransaction();
			User user1 = new User();
			user1.setUid(1);
			user1.setName("zhangsan");
			user1.setAge(20);
			session.update(user1);
			transaction.commit();
			session.close();
		}
	  
	    @Test
		public void selectById() {
			// TODO Auto-generated method stub
			User user = (User)session.get(User.class,1);
			System.out.println(user);
			session.close();
		}
	    
	    @Test
	    public void selectAll() {
			// TODO Auto-generated method stub
			//这里是实体名
			List<User> users = session.createQuery("from User").list();
			for(User user:users){
				System.out.println(user);
			}
			session.close();
		}
	    
	    
	    @Test
		public void delete() {
			// TODO Auto-generated method stub
			//开启事务
			Transaction transaction = session.beginTransaction();
			User user = (User)session.get(User.class, 1);
			session.delete(user);
			transaction.commit();
			session.close();
		}
     
	 
}
