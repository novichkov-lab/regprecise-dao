package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "TFFamily")
public class TFFamily extends DictionaryItem{

	@Id
	@GeneratedValue
	@Column(name = "tfFamilyId")
	private Integer id;
	private String name;
	private String domains;		
	
	@Column(columnDefinition="Text")	
	private String origin;
	
	@Column(columnDefinition="Text")	
	private String description;	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "tfFamilyId")
	})	
	private List<Regulog> regulogs = new ArrayList<Regulog>();	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDomains(String domains) {
		this.domains = domains;
	}

	public String getDomains() {
		return domains;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOrigin() {
		return origin;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setRegulogs(List<Regulog> regulogs) {
		this.regulogs = regulogs;
	}

	public List<Regulog> getRegulogs() {
		return regulogs;
	}
}
