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
@Table(name = "PropagatedGene2GeneOrtholog")
public class PropagatedGene2GeneOrtholog {
	@Id
	@GeneratedValue
	@Column(name = "propagatedGene2GeneOrthologId")
    Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "propagatedGeneId")			
	PropagatedGene propagatedGene;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "geneId")			
	Gene sourceGene;
	
	String orthologRef;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PropagatedGene getPropagatedGene() {
		return propagatedGene;
	}

	public void setPropagatedGene(PropagatedGene propagatedGene) {
		this.propagatedGene = propagatedGene;
	}

	public Gene getSourceGene() {
		return sourceGene;
	}

	public void setSourceGene(Gene sourceGene) {
		this.sourceGene = sourceGene;
	}

	public String getOrthologRef() {
		return orthologRef;
	}

	public void setOrthologRef(String orthologRef) {
		this.orthologRef = orthologRef;
	}
	
	
}
