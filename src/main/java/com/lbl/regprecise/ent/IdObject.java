package com.lbl.regprecise.ent;



/**
 * @author Pavel Novichkov
 *
 */
public abstract class IdObject {

	public abstract Integer getId();
	
	public boolean equals(Object o)
	{
		IdObject idObject = (IdObject) o;
		if( getId() != null && idObject.getId() != null)
		{
			return getId().equals( idObject.getId() );
		}
		
		
		return this == idObject;
	}	
}
