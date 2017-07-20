package com.cblue.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.Customer;

public class Test02_1Select {
	
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
		
		//初始化数据
		@Test
		public void init() {
			// TODO Auto-generated method stub
			Transaction trans = session.beginTransaction();
			for(int i=0;i<20;i++){
				Customer customer = new Customer("eee"+i);
				session.save(customer);
				
			}
			trans.commit();
			session.close();
		}
		
		//简单查询
		//HQL查询
		@Test
		public void simpleSelect1(){
			Query q = session.createQuery("from Customer c");
			List<Customer> customers = q.list();
			for(Customer customer:customers){
				System.out.println(customer);
			}
		}
		
        //QBC查询
		@Test
		public void simpleSelect2(){
//			Criteria criteria = session.createCriteria(Customer.class);
//			List<Customer> customers = criteria.list();
			//或
			List<Customer> customers = session.createCriteria(Customer.class).list();
			
			for(Customer customer:customers){
				System.out.println(customer);
			}
		}
		
		//sql检索
		@Test
		public void simpleSelect3(){
			SQLQuery sqlQuery = session.createSQLQuery("select {c.*} from Customer c");
			sqlQuery.addEntity("c",Customer.class);
			List<Customer> customers = sqlQuery.list();
			for(Customer customer:customers){
				System.out.println(customer);
			}
		}
		
		
}
