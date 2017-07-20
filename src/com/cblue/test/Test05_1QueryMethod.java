package com.cblue.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.Customer;


/**
 * 查询方式
 * 实现分页和条件查询
 * @author pavel
 *
 */
public class Test05_1QueryMethod {
	
	
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
			Customer customer = new Customer("eee"+i,20+i);
			session.save(customer);
			
		}
		trans.commit();
		session.close();
	}
	
	
	//分页查询
			//按HQL
			@Test
			public void selectPage1() {
				// TODO Auto-generated method stub
				Query q = session.createQuery("from Customer");
			    q.setFirstResult(1);
			    q.setMaxResults(10);
			    List<Customer> list = q.list();
			    for(Customer customer:list){
			    	System.out.println(customer);
			    }
			}
			//按QBC
			@Test
			public void selectPage2(){
				Criteria criteria = session.createCriteria(Customer.class);
				criteria.setFirstResult(1);
			    criteria.setMaxResults(10);
			    List<Customer> list = criteria.list();
			    for(Customer customer:list){
			    	System.out.println(customer);
			    }
			}
			
			
			
			//查询单个对象
			//按HQL
			@Test
			public void singleObjet1(){
				Query q = session.createQuery("from Customer c order by c.cid  desc");
				q.setMaxResults(1);
				Customer customer = (Customer)q.uniqueResult();
				System.out.println(customer);
			}
			//按QBC
			@Test
			public void singleObjet2(){
				Criteria criteria =  session.createCriteria(Customer.class);
				criteria.addOrder(org.hibernate.criterion.Order.desc("cid"));
				criteria.setMaxResults(1);
				Customer customer = (Customer)criteria.uniqueResult();
				System.out.println(customer);
			}
			
			
			//检索排序
					//HQL查询
					@Test
					public void selectOrderBy01(){ 
						List<Customer> customers =  session.createQuery("from Customer c order by c.cid  desc").list(); //降序
						for(Customer customer:customers){
							System.out.println(customer);
						}
					}
					//QBC查询
					@Test
					public void selectOrderBy02(){
						Criteria criteria =  session.createCriteria(Customer.class);
						criteria.addOrder(org.hibernate.criterion.Order.desc("cid"));
						List<Customer> customers = criteria.list();
						for(Customer customer:customers){
							System.out.println(customer);
						}
					}

	
	/**
	 * 按条件查询
	 */
	//按参数检索
	@Test
	public void selectByParamName(){
	  Query query = session.createQuery("from Customer c where c.cname=:name");
	  query.setString("name", "eee2");	
	  List<Customer> customers =  query.list();
		for(Customer customer:customers){
			System.out.println(customer);
		}
	}
	//按参数名称查询
	@Test
	public void selectByParamPosistion(){
	  Query query = session.createQuery("from Customer c where c.cname=?");
	  query.setString(0, "eee2");	
	  List<Customer> customers =  query.list();
		for(Customer customer:customers){
			System.out.println(customer);
		}
	}
	
	
	//QBC查询
		@Test
		public void simpleSelectByCondition1(){
//			Criteria criteria = session.createCriteria(Customer.class);
//			Criterion criterion = Restrictions.eq("cname", "eee1");
//			criteria.add(criterion);
//			List<Customer> customers = criteria.list();
			//或
			List<Customer> customers = session.createCriteria(Customer.class).add(Restrictions.eq("cname", "eee1")).list();
			
			for(Customer customer:customers){
				System.out.println(customer);
			}
		}
		
		//id大于3的记录
		 @Test
		 public void simpleSelectByCondition2() {
				// TODO Auto-generated method stub
				Criteria criteria= (Criteria)session.createCriteria(Customer.class);
				criteria.add(Restrictions.gt("cid",3));
				List<Customer> list =  criteria.list();
				 for(Customer customer:list){
				    	System.out.println(customer);
				 }
			}
		//模糊查询	
		@Test
		public void simpleSelectByCondition3() {
				// TODO Auto-generated method stub
				Criteria criteria= (Criteria)session.createCriteria(Customer.class);
				criteria.add(Restrictions.like("cname", "%zhang%"));
				List<Customer> list =  criteria.list();
				 for(Customer customer:list){
				    	System.out.println(customer);
				 }
		}		 
	
	//投影查询
		@Test
		public void select01(){
			Query q = session.createQuery("select c.id,c.cname from Customer c");
			List list = q.list();
			for(int i=0;i<list.size();i++){
				Object[] data = (Object[])list.get(i);
				for(int j=0;j<data.length;j++){
					System.out.print(data[j]+"--");
				}
				System.out.println();
			}
		}
		
		@Test
		public void select02(){
			Criteria criteria = session.createCriteria(Customer.class);
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Property.forName("cid"));
			projectionList.add(Property.forName("cname"));
			criteria.setProjection(projectionList);
			List list = criteria.list();
			for(int i=0;i<list.size();i++){
				Object[] data = (Object[])list.get(i);
				for(int j=0;j<data.length;j++){
					System.out.print(data[j]+"--");
				}
				System.out.println();
			}
		}
		
		//使用构造函数
		@Test
		public void select03(){
			Query q = session.createQuery("select new Customer(c.cid, c.cname) from Customer c");
			List<Customer> list = q.list();
			for(Customer c:list){
				System.out.println(c.getCid()+"---"+c.getCname());
			}
		}
		
		//使用聚合函数
		@Test
		public void selFun01(){
			Query q = session.createQuery("select count(*) from Customer c");
			Long count = (Long)q.uniqueResult();
			System.out.println(count);
		}
		
		@Test
		public void selFun02(){
			Query q = session.createQuery("select avg(c.age) from Customer c");
			Double avg = (Double)q.uniqueResult();
			System.out.println(avg);
		}
		
		@Test
		public void selFun03(){
			Query q = session.createQuery("select max(c.age),min(c.age) from Customer c");
			Object[] maxmin = (Object[])q.uniqueResult();
			System.out.println(maxmin[0]);
			System.out.println(maxmin[1]);
		}
		
		//离线条件查询
		@Test
		public void de(){
			DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
			criteria.add(Restrictions.eq("cname", "eee11"));
			
		}
		
	
	
	
	
	
	
	


 
	
	


}
