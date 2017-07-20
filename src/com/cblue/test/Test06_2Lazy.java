package com.cblue.test;

import java.util.Set;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.cblue.entity.User;
import com.cblue.entity.single.onetomany.Student;
import com.cblue.entity.single.onetomany.Teacher;
import com.cblue.session.HibernateSessionFactory;

public class Test06_2Lazy {

	private Session session;

	@Before
	public void before() {
		session = HibernateSessionFactory.getSession();
	}

	// 使用断点测试，get直接执行sql
	@Test
	public void getUser() {
		User user = (User) session.get(User.class, 1);// 开始执行sql语句
		System.out.println("------");
		System.out.println(user.getName());
		session.close();
	}

	// 使用断点测试，load在需要获得属性值的时候才执行sql
	@Test
	public void lazyUser() {
		User user = (User) session.load(User.class, 1);
		System.out.println("------");
		System.out.println(user.getName());// 开始执行sql语句
		session.close();
	}

	// 当使用懒加载，session关闭之后，懒加载会失败，报错
	@Test
	public void lazyError() {
		User user = (User) session.load(User.class, 1);
		System.out.println("------");
		session.close();
		System.out.println(user.getName());// could not initialize proxy
	}

	// 集合懒加载 默认懒加载
	@Test
	public void lazyCollection1() {
		Teacher t1 = (Teacher) session.get(Teacher.class, 1);
		Set<Student> students = t1.getStudents();
		for (Student stu : students) {
			System.out.println(stu); // 在执行到这里的时候，执行sql语句
		}
	}

	// 集合懒加载 取消懒加载 <set name="students" cascade="all" lazy="false">
	@Test
	public void lazyCollection2() {
		Teacher t1 = (Teacher) session.get(Teacher.class, 1);
		Set<Student> students = t1.getStudents();
		for (Student stu : students) {
			System.out.println(stu); // 在执行到这里的时候，执行sql语句
		}
	}

	// 集合懒加载 取消懒加载 <set name="students" cascade="all" lazy="extra">
	/*@Test
	public void lazyCollection3() {
		Teacher t1 = (Teacher) session.get(Teacher.class, 1);
		Set<Student> students = t1.getStudents();
		for (Student stu : students) {
			System.out.println(stu); // 在执行到这里的时候，执行sql语句
		}
	}*/

}
