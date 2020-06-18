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
@Table(name = "Effector")
public class Effector extends DictionaryItem {
	@Id
	@GeneratedValue
	@Column(name = "effectorId")
	private Integer id;
	private String name;
		
	@ManyToOne
	@JoinColumn(name = "effectorClassId")			
	private EffectorClass effectorClass;
		
	private Boolean isApproved;	

	@ManyToOne
	@JoinColumn(name = "userId")			
	private User user;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulog2Effector",
			joinColumns={@JoinColumn(name="effectorId")},
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
	public EffectorClass getEffectorClass() {
		return effectorClass;
	}
	public void setEffectorClass(EffectorClass effectorClass) {
		this.effectorClass = effectorClass;
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
