package com.cblue.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.Customer;
import com.cblue.entity.Student;
import com.cblue.session.HibernateSessionFactory;

public class Test03_2Cache {
	
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

	//执行了两次对象查询，只有一条sql语句，第二条语句是对象是直接从一级缓存中获得
	@Test
	public void  select2(){
	   	 Student student1 = (Student) session.get(Student.class,1); //得到一个持久状态对象
	   	 System.out.println(student1);
		 
	   	 Student student2 = (Student) session.get(Student.class,1); //得到一个持久状态对象
	   	 System.out.println(student2);
	}
	
	//副本 hibernate中保存着一个跟数据库数据一致的对象，我们称为副本。
	//获得一个对象，不修改属性，直接update，发现没有更新语句。这是因为对象跟副本比较，没有变化，不执行sql语句
	@Test
	public void update01(){
		 Transaction transaction = session.beginTransaction();
	   	 Student student = (Student) session.get(Student.class,1); //得到一个持久状态对象
	   	 session.update(student);
	   	 transaction.commit();
	   	 session.close();
	}
	
	
	 /**
     * evict 清除缓存中某个对象的缓存
     */
    @Test
    public void testEvict(){
   	 Transaction transaction = session.beginTransaction();
   	 Student student = (Student) session.get(Student.class,1); //得到一个持久状态对象
   	 student.setName("XXX");
   	 session.evict(student); //把对象从持久化状态转化为瞬时状态
   	 session.update(student);  //把一个对象变成持久化状态对象
   	 transaction.commit();	
   	 session.close();
    }
    
    
    /**
     * clear 清除hibernate的全部缓存数据
     */
    
    @Test
    public void testClear(){
   	 Transaction transaction = session.beginTransaction();
   	 Student student = (Student) session.get(Student.class,1); //得到一个持久状态对象
   	 student.setName("XXX");
   	 session.clear(); //清除hibernate的缓存数据
   	 session.update(student);  
   	 transaction.commit();	
   	 session.close();
    }
    
    
    /**
     *  flush 把sql语句一起执行，然后再执行打印操作效率更高
     *  添加不添加flush的方法，打印的结果不同
     *  添加了flush方法，先执行了数据库操作，然后才打印对象
     *  不添加flush方法，按照顺序执行
     */
    @Test
    public void testFlush(){
   	 Transaction transaction = session.beginTransaction();
   	 Student student = (Student) session.get(Student.class,1); 
   	 student.setName("XXX");
   	 
     Student student1 = new Student("BBB");
     session.save(student1);
     session.flush();
     
     Student student2 = (Student) session.get(Student.class,1); //得到一个持久状态对象
     System.out.println(student2);
     
   	 transaction.commit();	
   	 session.close();
    }
    
    /**
     * 
     * 如果是瞬时对象，使用persist保存失败
     * 如果是瞬时对象，使用save保存
     * 
     */
    
    @Test
 	public void save_persist() {
 		// TODO Auto-generated method stub
 		Transaction trans = session.beginTransaction();
 		Student student = (Student) session.get(Student.class,1); 
 		System.out.println(student);
 		student.setName("abc");
 		session.evict(student);
 		
 		session.persist(student);
 		//session.save(student);
 		trans.commit();
 		session.close();
 	}
    
    
    /**
     * 如果是瞬时对象，使用save保存
     * 如果是持久化对象，使用update 
     */
     @Test
  	public void saveOrUpdate() {
  		// TODO Auto-generated method stub
  		Transaction trans = session.beginTransaction();
  		//Student student = new Student("aa");  
  		//如果是瞬时对象，执行保存操作
  		Student student = new Student("t1");
  		
  		//如果是持久化对象，执行更新操作
  		//Student student = (Student)session.get(Student.class, 1);
 		//student.setName("a2");
  		
  		session.saveOrUpdate(student);
  		trans.commit();
  		session.close();
  		
  	}
     
     /**
      * 如果不存在记录id，load抛出ObjectNotFoundException
      * 如果不存在记录id，get返回为空
      */
     @Test
     public void getOrload() {
    		// TODO Auto-generated method stub
    	 //Student student = (Student)session.load(Student.class, 10100);
    	 Student student = (Student)session.get(Student.class, 10100);
    	 System.out.println(student);
    		
    }
     /**
      * delete()与对象状态无关
      * 这个方法没有意义
      */
     @Test
     public void delete() {
    		// TODO Auto-generated method stub
    	 Transaction trans = session.beginTransaction();
    	 Student student = (Student)session.get(Student.class, 2);
    	 session.evict(student);
    	 session.delete(student);
    	 trans.commit();
    	 session.close();
    		
    }
	
}
