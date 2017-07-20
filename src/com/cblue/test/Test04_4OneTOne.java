package com.cblue.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.cblue.entity.onetone.IDCard;
import com.cblue.entity.onetone.Person;
import com.cblue.session.HibernateSessionFactory;

public class Test04_4OneTOne {
	
	//级联添加人和卡
	@Test
	public void testSave(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		
		Person person = new Person();
		person.setPname("pname1");
		
		
		IDCard idCard = new IDCard();
		idCard.setCnumber(2000);
		//设置关联
		Set<IDCard> idcads = new HashSet<IDCard>();
		idcads.add(idCard);
		person.setIdCards(idcads);
		
		idCard.setPerson(person);
		
		session.save(person);
	
		transaction.commit();
		session.close();
	}
	
	//级联删除
	@Test
	public void testUpdateName(){
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		
		Person person = (Person)session.get(Person.class,2);
		session.delete(person);
		transaction.commit();
		session.close();
		
	}

}
