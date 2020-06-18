package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "PropertyValue")
public class PropertyValue {
	@Id
	@GeneratedValue
	@Column(name = "propertyValueId")
	Integer id;	
	
	Integer objectId;
	Integer objectTypeTermId;
	Integer propertyTypeTermId;
	
	@Column(columnDefinition="text")
	String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectTypeTermId() {
		return objectTypeTermId;
	}

	public void setObjectTypeTermId(Integer objectTypeTermId) {
		this.objectTypeTermId = objectTypeTermId;
	}

	public Integer getPropertyTypeTermId() {
		return propertyTypeTermId;
	}

	public void setPropertyTypeTermId(Integer propertyTypeTermId) {
		this.propertyTypeTermId = propertyTypeTermId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
