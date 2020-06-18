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
@Table(name = "TaxonGenome")
public class TaxonGenome {

	@Id
	@GeneratedValue
	@Column(name = "taxonGenomeId")	
	private  Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "taxonId")		
	private  Taxon taxon;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "genomeId")		
	private  Genome genome;	
	
	private Boolean isReference;
	private Boolean isForLoad;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Taxon getTaxon() {
		return taxon;
	}
	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}
	public Genome getGenome() {
		return genome;
	}
	public void setGenome(Genome genome) {
		this.genome = genome;
	}
	public Boolean getIsReference() {
		return isReference;
	}
	public void setIsReference(Boolean isReference) {
		this.isReference = isReference;
	}
	public Boolean getIsForLoad() {
		return isForLoad;
	}
	public void setIsForLoad(Boolean isForLoad) {
		this.isForLoad = isForLoad;
	}	
}
