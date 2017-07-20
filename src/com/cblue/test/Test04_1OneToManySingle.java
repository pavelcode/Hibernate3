package com.cblue.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.cblue.entity.single.onetomany.Student;
import com.cblue.entity.single.onetomany.Teacher;
import com.cblue.session.HibernateSessionFactory;

public class Test04_1OneToManySingle {

//	 级联保存老师和学生
	 @Test
	 public void testSave(){
		 Session session = HibernateSessionFactory.getSession();
		 Transaction transaction = session.beginTransaction();
		  
		 Teacher teacher = new Teacher();
		 teacher.setTname("老师1");
		 
		 Student s1 = new Student();
		 s1.setSname("小会1");
		 
		 Student s2 = new Student();
		 s2.setSname("小灰1");
		 
		 Set<Student> students = new HashSet<Student>();
		 students.add(s1);
		 students.add(s2);
		 
		 teacher.setStudents(students);
		 
		 session.save(teacher);
			
	     transaction.commit();
	     session.close();
	 }
//保存老师级联更新学生
	  @Test
	  public void testSaveAndUpdate(){
		  
		     Session session = HibernateSessionFactory.getSession();
			 Transaction transaction = session.beginTransaction();
			  
			 Teacher teacher = new Teacher();
			 teacher.setTname("刘老师");
			 
			 Student s1 = (Student)session.get(Student.class, 2);
			 
			 Set<Student> students = new HashSet<Student>();
			 students.add(s1);
			 
			 teacher.setStudents(students);
			 
			 session.save(teacher);
				
				
		     transaction.commit();
		     session.close();
	  }
//级联更新老师和学生	  
	  @Test
	  public void testUpdate(){

		     Session session = HibernateSessionFactory.getSession();
			 Transaction transaction = session.beginTransaction();
			  
			 Teacher teacher = (Teacher)session.get(Teacher.class, 1);
			 teacher.setTname("王老师");
			 
			 Set<Student> students = teacher.getStudents();
			 for(Student stu:students){
				 stu.setSname("新学生");
			 }
				
		     transaction.commit();
		     session.close();
	  }
//级联删除 删除某个老师下的所有学生
	  @Test
	  public void testDeleteAllStudent(){
		  Session session = HibernateSessionFactory.getSession();
			 Transaction transaction = session.beginTransaction();
			  
			 Teacher teacher = (Teacher)session.get(Teacher.class, 3);
			 
			 Set<Student> students = teacher.getStudents();
			 teacher.setStudents(null); //解除关系
			 for(Student stu:students){
				  session.delete(stu);
				 
			 }
				
		     transaction.commit();
		     session.close();
	  }
	 /**
	  * 给一个存在的老师添加一个新学生
	  * 添加inverse 是否维护外键关系  true代表不维护  false代表维护 默认为false，
	  */
	  @Test
	  public void testAddStudent(){
		     Session session = HibernateSessionFactory.getSession();
			 Transaction transaction = session.beginTransaction();
			  
			 Teacher teacher = (Teacher)session.get(Teacher.class, 1);
			 
			 Student student = new Student();
			 student.setSname("zhang111");
			 teacher.getStudents().add(student);
			 
		     transaction.commit();
		     session.close();
	  }
	  
	  //把一个已经存在的学生，加入了一个已经存在的班级
	  @Test
	  public void testAddStudentAddClass(){
		    Session session = HibernateSessionFactory.getSession();
			 Transaction transaction = session.beginTransaction();
			  
			 Teacher teacher = (Teacher)session.get(Teacher.class, 2);
			 Student student = (Student)session.get(Student.class, 6);
			 
			 teacher.getStudents().add(student);
			 
		     transaction.commit();
		     session.close();
	  }
	  //把一个学生从一个老师下移交到另一个老师下
	  @Test
	  public void testStudentChangeTeacher(){
		  Session session = HibernateSessionFactory.getSession();
			 Transaction transaction = session.beginTransaction();
			  
			 Teacher teacher1 = (Teacher)session.get(Teacher.class, 1);
			 
			 Student student = (Student)session.get(Student.class, 6);
			 
			 Teacher teacher2 = (Teacher)session.get(Teacher.class, 2);
			 
			//teacher1.getStudents().remove(student);
			teacher2.getStudents().add(student);
			 
		     transaction.commit();
		     session.close();
	  }
	  
	  //级联删除
	  @Test
	  public void testDeleteTeacherAndStudent(){
		  Session session = HibernateSessionFactory.getSession();
		  Transaction transaction = session.beginTransaction();
		  Teacher teacher = (Teacher)session.get(Teacher.class, 1);
		  session.delete(teacher);
		  
		  transaction.commit();
	  }
	  
	  //级联查询
	  @Test
	  public void testSelectAll(){
		  Session session = HibernateSessionFactory.getSession();
		  Transaction transaction = session.beginTransaction();
		  Teacher teacher = (Teacher)session.get(Teacher.class, 3);
		  System.out.println(teacher);
		  Set<Student> students = teacher.getStudents();
		  for(Student stu :students){
			  System.out.println(stu);
		  }
		  transaction.commit();
	  }
	  
	  
}
