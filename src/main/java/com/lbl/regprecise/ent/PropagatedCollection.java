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
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "PropagatedCollection")
public class PropagatedCollection {
	@Id
	@GeneratedValue
	@Column(name = "propagatedCollectionId")
    Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "collectionId")		
	Collection collection;	
	
    Integer taxonId;
	
	String name;
	String longName;
	
	@Column(columnDefinition="Text")
	String description;
	
	Boolean forPropagation;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="PropagatedCollection2Regulon",
			joinColumns={@JoinColumn(name="propagatedCollectionId")},
			inverseJoinColumns={@JoinColumn(name="propagatedRegulonId")}
	)  	
	List<PropagatedRegulon> propagatedRegulons = new ArrayList<PropagatedRegulon>();

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PropagatedRegulon> getPropagatedRegulons() {
		return propagatedRegulons;
	}

	public void setPropagatedRegulons(List<PropagatedRegulon> propagatedRegulons) {
		this.propagatedRegulons = propagatedRegulons;
	}

	public void setTaxonId(Integer taxonId) {
		this.taxonId = taxonId;
	}

	public Integer getTaxonId() {
		return taxonId;
	}

	public void setForPropagation(Boolean forPropagation) {
		this.forPropagation = forPropagation;
	}

	public Boolean getForPropagation() {
		return forPropagation;
	}	
}
