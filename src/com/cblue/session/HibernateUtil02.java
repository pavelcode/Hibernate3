package com.cblue.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil02 {

	 //初始化一个ThreadLocal对象，有get和set方法
    private static final ThreadLocal<Session> sessionTL=new ThreadLocal<Session>();
    
    private static Configuration configuration;
    
    private final static SessionFactory sessionFactory;
    static{
        
        configuration=new Configuration().configure();
        sessionFactory=configuration.buildSessionFactory();
    }
    //获得session对象
    public static Session currentSession() {
        //sessionTL的get方法根据当前线程返回其对应的线程内部变量，即Session对象，多线程情况下共享数据库连接是不安全的。
        //ThreadLocal保证了每个线程都有自己的session对象
        Session session=(Session)sessionTL.get();
        if (session==null) {
            session=sessionFactory.openSession();
            sessionTL.set(session);
        }
        
        return session;
    }
    //关闭session对象
    public static void closeSession() {
        Session session=(Session)sessionTL.get();
        sessionTL.set(null);
        session.close();
    }

}
