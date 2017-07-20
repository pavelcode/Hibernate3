package com.cblue.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.single.onetomany.Student;
import com.cblue.entity.single.onetomany.Teacher;
import com.cblue.session.HibernateSessionFactory;

public class Test06_1Fetch {
	
	
	private Session session;

	@Before
	public void before() {
		session = HibernateSessionFactory.getSession();
	}
	
	/**
	 * 抓取策略
	 * 会把所有的老师，不管有没有学生，都会查询一遍，效率低效
	 * 默认值<set name="students" cascade="all" fetch="select">
	 */
	@Test
	public void getTeacherAndStudent_Fetch_select(){
		List<Teacher> teachers =session.createQuery("from Teacher").list();
		for(Teacher teacher:teachers){
			Set<Student> students = teacher.getStudents();
			for(Student student:students){
				System.out.println(student.getSname());
			}
		}
		
		session.close();
		
	}
	/**
	 * <set name="students" cascade="all" fetch="subselect">
	 * 使用了子查询，效率更高
	 */
	@Test
	public void getTeacherAndStudent_Fetch_subselect(){
		List<Teacher> teachers =session.createQuery("from Teacher").list();
		for(Teacher teacher:teachers){
			Set<Student> students = teacher.getStudents();
			for(Student student:students){
				System.out.println(student.getSname());
			}
		}
		
		session.close();
		
	}
	
	/**
	 * <set name="students" cascade="all" fetch="join">
	 * 使用了子查询，无效
	 */
	@Test
	public void getTeacherAndStudent_Fetch_join1(){
		List<Teacher> teachers =session.createQuery("from Teacher").list();
		for(Teacher teacher:teachers){
			Set<Student> students = teacher.getStudents();
			for(Student student:students){
				System.out.println(student.getSname());
			}
		}
		
		session.close();
		
	}
	
	/**
	 * <set name="students" cascade="all" fetch="join">
	 * 没有子查询，使用左外连接
	 */
	@Test
	public void getTeacherAndStudent_Fetch_join2(){
		    Teacher teacher =(Teacher)session.get(Teacher.class, 1);
			Set<Student> students = teacher.getStudents();
			for(Student student:students){
				System.out.println(student.getSname());
			}
		session.close();
		
	}

}
