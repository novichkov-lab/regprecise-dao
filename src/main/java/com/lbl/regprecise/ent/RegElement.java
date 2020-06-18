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
@Table(name = "RegElement")
public class RegElement {
	@Id
	@GeneratedValue
	@Column(name = "regElementId")
	private Integer id;
	
	private String name;
	
	private Integer regulationTypeTermId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}

	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}
		
	public boolean equals(Object obj)
	{
		return obj instanceof RegElement 
			   && ((RegElement)obj).getId().equals(id); 
	}
}
