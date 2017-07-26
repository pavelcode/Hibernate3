package com.cblue.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.cblue.entity.onetomany.Department;
import com.cblue.entity.onetomany.Employee;
import com.cblue.session.HibernateSessionFactory;

public class Test04_2OneToMany {
	
	//级联添加 添加一个部门，及部门下的员工
	@Test
	public void testSave(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		
		Department d1 = new Department();
		d1.setDname("高中部");
		  
		Employee e1 = new Employee();
		e1.setEname("e111");
		
		Employee e2 = new Employee();
		e2.setEname("e222");
		
		d1.getEmployees().add(e1);
		d1.getEmployees().add(e2);
		session.save(d1);
		
		transaction.commit();
		session.close();
		
	}
	
	//更改员工的部门
	@Test
	public void testChangeDepartment(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		
		Employee e1 = (Employee)session.get(Employee.class, 1);
		Department d2 = (Department)session.get(Department.class, 2);
		
		e1.setDepartment(d2);
		
		transaction.commit();
		session.close();
	}
	
	//遣散一个部门的全部员工
	@Test
	public void testDeleteDepartment(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		
	
		Department d1 = (Department)session.get(Department.class, 1);
		Set<Employee> employees = d1.getEmployees();
		for(Employee emp:employees){
			emp.setDepartment(null);
		}
		transaction.commit();
		session.close();
	}

}
