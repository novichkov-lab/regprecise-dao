package com.lbl.regprecise.dao.hibernate;

import java.net.URL;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * @author Pavel Novichkov
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static {
		URL url = HibernateUtil.class.getResource("hibernate.cfg.xml");
		try {			
			sessionFactory = new Configuration().configure(url).buildSessionFactory();
//			sessionFactory = new AnnotationConfiguration().configure(url).buildSessionFactory();
		} catch (Throwable ex) {
			// Log exception!
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static String toCommaSeparatedString(List<String> vals) {
		StringBuffer sb = new StringBuffer();
		for(String val: vals)
		{
			if(sb.length() > 0) sb.append(",");
			sb.append("'" + val + "'");
		}
		return sb.toString();
	}
	
	public static String toCommaSeparatedString(int[] genomeIds) {
		StringBuffer sb = new StringBuffer();
		for(int genomeId: genomeIds)
		{
			if(sb.length() > 0) sb.append(",");
			sb.append(genomeId);
		}
		return sb.toString();
	}	
}