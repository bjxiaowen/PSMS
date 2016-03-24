package com.PSMS.Hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class BaseSessionFactory {


	  
    private static String CONFIG_FILE_LOCATION = "/originData.cfg.xml";
    
    
	private static final ThreadLocal<Session> hibernateSessionThreadLocal = new ThreadLocal<Session>();
    private  static Configuration configuration = new AnnotationConfiguration();    
    private static org.hibernate.SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;

   
	static {
    	try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("error");
			e.printStackTrace();
		}
    }
	
	
    private BaseSessionFactory() {
    }
	
    
    
    public static Session getHibernateSession() throws HibernateException {
        Session session = (Session) hibernateSessionThreadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			hibernateSessionThreadLocal.set(session);
		}

        return session;
    }
    
    
    public static void closeHibernateSession() throws HibernateException {
        Session session = (Session) hibernateSessionThreadLocal.get();
        hibernateSessionThreadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }
    
 
    public static Transaction getHibernateTransaction(){
    	Session session = getHibernateSession();
    	if(session == null || !session.isConnected()){
    		throw new DataAccessException("errer");
    	}
    	return session.getTransaction();
    }
    
   
    public static void begainHibernateTransaction(){
    	Transaction transaction = getHibernateTransaction();
    	if(transaction == null || transaction.wasCommitted() || transaction.wasRolledBack()){
    		throw new DataAccessException("err");
    	}
    	try {
			transaction.begin();
		} catch (Exception e) {
		}
    }
    
   
    public static void commitHibernateTransaction(){
    	Transaction transaction = getHibernateTransaction();
    	if(transaction == null || transaction.wasCommitted() || transaction.wasRolledBack()){
    		throw new DataAccessException("err");
    	}
    	try {
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
   
    public static void rollbackHibernateTransaction(){
    	Transaction transaction = getHibernateTransaction();
    	if(transaction == null || transaction.wasCommitted() || transaction.wasRolledBack()){
    		throw new DataAccessException("err");
    	}
    	try {
			transaction.rollback();
		} catch (Exception e) {
		}
    }
    
    
    
    
    

	
	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err
					.println("err");
			e.printStackTrace();
		}
	}

	

	
	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public static void setConfigFile(String configFile) {
		BaseSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	
	public static Configuration getConfiguration() {
		return configuration;
	}


}
