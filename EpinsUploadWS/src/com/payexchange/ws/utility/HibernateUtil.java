package com.payexchange.ws.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.payexchange.ws.beans.GetParams;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            GetParams gp = new GetParams();
            System.out.println(gp.getDialect());
            System.out.println(gp.getDriver());
            System.out.println(gp.getUrl());
            System.out.println(gp.getUser());
            System.out.println(gp.getPass());
            gp.getValues();
            AnnotationConfiguration cfg = new AnnotationConfiguration().configure();
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/epinscard");
            cfg.setProperty("hibernate.connection.username", "root");
            cfg.setProperty("hibernate.connection.password", "");
            cfg.setProperty("connection.pool_size", "1");
            cfg.setProperty("hibernate.current_session_context_class", "thread");
            cfg.setProperty("cache.provider_class", "org.hibernate.cache.NoCacheProvider");
            cfg.setProperty("show_sql", "true");
            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
