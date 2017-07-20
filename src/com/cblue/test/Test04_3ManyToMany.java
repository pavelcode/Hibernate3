package com.cblue.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.manytomany.Course;
import com.cblue.entity.manytomany.Student;
import com.cblue.session.HibernateSessionFactory;

public class Test04_3ManyToMany {

	private Session session;
	
	@Before
	public  void before(){
		 session = HibernateSessionFactory.getSession();
		
	}
	
	//级联添加课程 学生
	@Test
	public void save(){
	
		Transaction transaction = session.beginTransaction();
		
		Student s1 = new Student();
		s1.setSname("学生1");
		
		Student s2 = new Student();
		s2.setSname("学生2");
		
		Course c1 = new Course();
		c1.setCname("课程1");
		
		Set<Student> students = new HashSet<Student>();
		students.add(s1);
		students.add(s2);
		c1.setStudents(students);
		
		session.save(c1);
		transaction.commit();
		session.close();
	}
	//解除课程和学生的关系
	@Test
	public void removeStudentAndCourse(){
		Transaction transaction = session.beginTransaction();
		Course c1 = (Course)session.get(Course.class, 1);
		c1.setStudents(null);
		transaction.commit();
		session.close();
	}
	//学生换课程
	@Test
	public void changeCourse(){
		
		Transaction transaction = session.beginTransaction();
		
		Student s1 = (Student)session.get(Student.class, 1);
		
		Course c1 = (Course)session.get(Course.class, 1);
		
		Set<Student> students = c1.getStudents();
		students.add(s1);
		
		transaction.commit();
		session.close();
	}
	
	//删除一个学生
	@Test
	public void deleteAStudent(){
		
        Transaction transaction = session.beginTransaction();
		
		Student s1 = (Student)session.get(Student.class, 2);
		
		session.delete(s1);
		transaction.commit();
		session.close();
	}
	
	
	//删除一个课程及下面的学生
	@Test
	public void deleteCourse(){
		  Transaction transaction = session.beginTransaction();
			
			Course c1 = (Course)session.get(Course.class, 1);
			
			session.delete(c1);
			transaction.commit();
			session.close();
	}
	
	
	
}
