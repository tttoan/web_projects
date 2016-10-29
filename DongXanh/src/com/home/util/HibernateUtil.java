package com.home.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.opensymphony.xwork2.ActionSupport;

public class HibernateUtil extends ActionSupport{
	private static SessionFactory sessionFactory ;

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory(ActionSupport action) {
		try {
			//Version old
			//return new Configuration().configure().buildSessionFactory();
			
			//Version new
			// loads configuration and mappings
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry
			= new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).build();

			// builds a session factory from the service registry
			return configuration.buildSessionFactory(serviceRegistry);           
		}
		catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			if(action != null){
				action.addActionError(ex.getMessage());
			}
			//throw new ExceptionInInitializerError(ex);
			return null;
		}
	}

	public static SessionFactory getSessionFactory(ActionSupport action) {
		if(sessionFactory == null){
			sessionFactory = buildSessionFactory(action);
		}
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null){
			sessionFactory = buildSessionFactory(null);
		}
		return sessionFactory;
	}
}