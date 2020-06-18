package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Elena Novichkova
 *
 */
@Entity
@Table(name = "Pathway")
public class Pathway extends DictionaryItem {
	@Id
	@GeneratedValue
	@Column(name = "pathwayId")
	private Integer id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "pathwayClassId")			
	private PathwayClass pathwayClass;
		
	private Boolean isApproved;	

	@ManyToOne
	@JoinColumn(name = "userId")			
	private User user;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulog2Pathway",
			joinColumns={@JoinColumn(name="pathwayId")},
			inverseJoinColumns={@JoinColumn(name="regulogId")}
	)	
	private List<Regulog> regulogs = new ArrayList<Regulog>();	
	
	
	
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
	public void setPathwayClass(PathwayClass pathwayClass) {
		this.pathwayClass = pathwayClass;
	}
	public PathwayClass getPathwayClass() {
		return pathwayClass;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setRegulogs(List<Regulog> regulogs) {
		this.regulogs = regulogs;
	}
	public List<Regulog> getRegulogs() {
		return regulogs;
	}
}

