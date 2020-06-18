package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Elena Novichkova
 *
 */
@Entity
@Table(name = "Project")
public class Project {
	@Id
	@GeneratedValue
	@Column(name = "projectId")
	private Integer id;
	
	private String name;
	
	@Column(columnDefinition="Text")
	private	String description;	
	
	private Integer regulationTypeTermId;
	
	private Integer statusTermId;
	
	private Date createDate;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Project2Collection",
			joinColumns={@JoinColumn(name="projectId")},
			inverseJoinColumns={@JoinColumn(name="collectionId")}
	)
	private List<Collection> collections = new ArrayList<Collection>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Project2RegElement",
			joinColumns={@JoinColumn(name="projectId")},
			inverseJoinColumns={@JoinColumn(name="regElementId")}
	)
	private List<RegElement> regElements = new ArrayList<RegElement>();

	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "projectId")
	})
	private List<ProjectRegulog> regulogs = new ArrayList<ProjectRegulog>();
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}

	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}

	public Integer getStatusTermId() {
		return statusTermId;
	}

	public void setStatusTermId(Integer statusTermId) {
		this.statusTermId = statusTermId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Collection> getCollections() {
		return collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public List<RegElement> getRegElements() {
		return regElements;
	}

	public void setRegElements(List<RegElement> regElements) {
		this.regElements = regElements;
	}

	public void setRegulogs(List<ProjectRegulog> regulogs) {
		this.regulogs = regulogs;
	}

	public List<ProjectRegulog> getRegulogs() {
		return regulogs;
	}
	
	public Hashtable<Collection, Hashtable<RegElement, Regulog>> getCollection2RegElem2RegulogHash()
	{
		Hashtable<Collection, Hashtable<RegElement, Regulog>> crrHash = new Hashtable<Collection, Hashtable<RegElement, Regulog>>();
		for(ProjectRegulog reg: regulogs)
		{
			Hashtable<RegElement, Regulog> rrHash = crrHash.get(reg.getCollection());
			if(rrHash == null)
			{
				rrHash = new Hashtable<RegElement, Regulog>();
				crrHash.put(reg.getCollection(), rrHash);
			}
			rrHash.put(reg.getRegElement(), reg.getRegulog());
		}
		return crrHash;
	}
	
	public Hashtable<RegElement, Hashtable<Collection, Regulog>> getRegElem2Collection2RegulogHash()
	{
		Hashtable<RegElement, Hashtable<Collection, Regulog>> rcrHash = new Hashtable<RegElement, Hashtable<Collection, Regulog>>();
		for(ProjectRegulog reg: regulogs)
		{
			Hashtable<Collection, Regulog> crHash = rcrHash.get(reg.getRegElement());
			if(crHash == null)
			{
				crHash = new Hashtable<Collection, Regulog>();
				rcrHash.put(reg.getRegElement(), crHash);
			}
			crHash.put(reg.getCollection(), reg.getRegulog());
		}
		return rcrHash;
	}
		
	public Hashtable<Collection, Regulog> getCollection2RegulogHash(int regElementId)
	{
		Hashtable<Collection, Regulog> crHash = new Hashtable<Collection, Regulog>();
		for(ProjectRegulog reg: regulogs)
		{
			if(reg.getRegElement().getId() == regElementId)
			{
				crHash.put(reg.getCollection(), reg.getRegulog());
			}
		}
		return crHash;
	}

}
