package com.jcasey.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static Configuration configuration;
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
        	// Create the SessionFactory from hibernate.cfg.xml
        	if(configuration == null)
        	{
        		configuration = new Configuration();
        		configuration.configure("hibernate.cfg.xml");
        	}
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        	        .applySettings(configuration.getProperties()).build();
        	return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null)
        {
        	sessionFactory = buildSessionFactory();
        }
    	return sessionFactory;
    }

	public static Configuration getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(Configuration configuration) {
		HibernateUtil.configuration = configuration;
	}

}