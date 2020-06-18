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
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Taxon")
public class Taxon {

	@Id
	@GeneratedValue
	@Column(name = "taxonId")
	private Integer id;	
	
	private String phylum;
	private String name;
	private String description;
	
	// Reference taxon means that this taxon will be used for propagation
	private Boolean isReference;
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE, CascadeType.DELETE_ORPHAN})	
	@JoinColumns({
		@JoinColumn(name = "taxonId")
	})
	List<TaxonGenome> taxonGenomes = new ArrayList<TaxonGenome>();	
	
	public List<Genome> getReferenceGenomes()
	{
		List<Genome> refGenomes = new ArrayList<Genome>();
		for(TaxonGenome taxGenome: taxonGenomes)
		{
			if(taxGenome.getIsReference())
			{
				refGenomes.add(taxGenome.getGenome());
			}
		}
		return refGenomes;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhylum() {
		return phylum;
	}
	public void setPhylum(String phylum) {
		this.phylum = phylum;
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
	public void setIsReference(Boolean isReference) {
		this.isReference = isReference;
	}
	public Boolean getIsReference() {
		return isReference;
	}
	public List<TaxonGenome> getTaxonGenomes() {
		return taxonGenomes;
	}
	public void setTaxonGenomes(List<TaxonGenome> taxonGenomes) {
		this.taxonGenomes = taxonGenomes;
	}	
}