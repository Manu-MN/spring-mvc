package com.demo.hibernate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static final SessionFactory sessionFactory = buildSessionfactory();
	
	private static SessionFactory buildSessionfactory() {
		
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
