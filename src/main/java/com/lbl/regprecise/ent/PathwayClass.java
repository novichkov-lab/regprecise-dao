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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Elena Novichkova
 *
 */
@Entity
@Table(name = "PathwayClass")
public class PathwayClass extends DictionaryItem {
	@Id
	@GeneratedValue
	@Column(name = "pathwayClassId")
	private Integer id;
	private String name;
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "pathwayClassId")
	})		
	private List<Pathway> pathways = new ArrayList<Pathway>();
	
	
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
	public void setPathways(List<Pathway> pathways) {
		this.pathways = pathways;
	}
	public List<Pathway> getPathways() {
		return pathways;
	}
}
