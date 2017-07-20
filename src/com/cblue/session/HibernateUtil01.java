package com.cblue.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil01 {

	private static Configuration configuration;
	private static SessionFactory sessionFactory;
	
	static {
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public static Session openSession(){
		return sessionFactory.openSession();
	}

}
