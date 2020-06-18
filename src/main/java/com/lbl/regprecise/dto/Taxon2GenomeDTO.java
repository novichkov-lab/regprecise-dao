package com.lbl.regprecise.dto;

/**
 * @author Pavel Novichkov
 *
 */
public class Taxon2GenomeDTO {
	
	private Integer genomeId;
	private Boolean isRepresentiveGenome;
	
	private String taxonName;
	private Integer taxonId;
	
	
	public void setGenomeId(Integer genomeId) {
		this.genomeId = genomeId;
	}
	public Integer getGenomeId() {
		return genomeId;
	}

	public Integer getTaxonId() {
		return taxonId;
	}
	public void setTaxonId(Integer taxonId) {
		this.taxonId = taxonId;
	}

	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
	public String getTaxonName() {
		return taxonName;
	}
	public void setIsRepresentiveGenome(Boolean isRepresentiveGenome) {
		this.isRepresentiveGenome = isRepresentiveGenome;
	}
	public Boolean getIsRepresentiveGenome() {
		return isRepresentiveGenome;
	}	
}
