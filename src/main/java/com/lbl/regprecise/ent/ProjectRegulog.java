package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Elena Novichkova
 *
 */
@Entity
@Table(name = "Project2Regulog")
public class ProjectRegulog {
	@Id
	@GeneratedValue
	@Column(name = "project2RegulogId")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "projectId")			
	private Project project;

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "collectionId")			
	private Collection collection;

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "regElementId")			
	private RegElement regElement;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "regulogId")			
	private Regulog regulog;

	
	public ProjectRegulog(){};
	
	public ProjectRegulog(Project project, Collection collection, RegElement regElement, Regulog regulog)
	{
		this.project = project;
		this.collection = collection;
		this.regElement = regElement;
		this.regulog = regulog;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public RegElement getRegElement() {
		return regElement;
	}

	public void setRegElement(RegElement regElement) {
		this.regElement = regElement;
	}

	public Regulog getRegulog() {
		return regulog;
	}

	public void setRegulog(Regulog regulog) {
		this.regulog = regulog;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}
	
	
}
