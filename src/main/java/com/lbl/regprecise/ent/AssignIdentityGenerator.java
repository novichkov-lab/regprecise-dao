package com.lbl.regprecise.ent;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Assigned;
import org.hibernate.id.IdentityGenerator;

/**
 * @author Pavel Novichkov
 *
 */
public class AssignIdentityGenerator extends IdentityGenerator{

	public static final int IDENTITY_GENERATOR_TYPE = 0;
	public static final int ASSIGN_GENERATOR_TYPE = 1;
	
	public static int generatorType = IDENTITY_GENERATOR_TYPE; 
	
	private static Assigned assignGenerator = new Assigned();
	
	public Serializable generate(SessionImplementor session, Object obj)
			throws HibernateException {
		
		switch(generatorType)
		{
			case IDENTITY_GENERATOR_TYPE: return  super.generate(session, obj);
			case ASSIGN_GENERATOR_TYPE: return assignGenerator.generate(session, obj);
		}
		
		return 0; 
	}

}
