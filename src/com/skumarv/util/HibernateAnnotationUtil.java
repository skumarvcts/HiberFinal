package com.skumarv.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateAnnotationUtil {

	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory(String configFile) {
		try {
			// Create the SessionFactory from hibernate-annotation.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure(configFile);
			System.out.println("Hibernate Annotation Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory(String configFile) {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory(configFile);
		return sessionFactory;
	}
}