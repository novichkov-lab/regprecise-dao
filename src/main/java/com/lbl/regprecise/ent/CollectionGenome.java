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
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "CollectionGenome")
public class CollectionGenome {
	
	@Id
	@GeneratedValue
	@Column(name = "collectionGenomeId")	
	Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "collectionId")		
	Collection collection;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "genomeId")		
	Genome genome;	
	
	Integer orderIndex;

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

	public Genome getGenome() {
		return genome;
	}

	public void setGenome(Genome genome) {
		this.genome = genome;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	
	
}
