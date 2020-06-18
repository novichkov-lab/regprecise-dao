package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class Gene2Regulon {
	
	private Integer geneId;
	private String locusTag;

	private Integer regulonId;
	private String regulatorName;
	private String taxonName;
	
	private String phylum;
	private String function;
	private String effector;
	private String pathway;
	
	public Integer getGeneId() {
		return geneId;
	}
	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}
	public String getLocusTag() {
		return locusTag;
	}
	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}
	public Integer getRegulonId() {
		return regulonId;
	}
	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}
	public String getTaxonName() {
		return taxonName;
	}
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
	public String getPhylum() {
		return phylum;
	}
	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getEffector() {
		return effector;
	}
	public void setEffector(String effector) {
		this.effector = effector;
	}
	public String getPathway() {
		return pathway;
	}
	public void setPathway(String pathway) {
		this.pathway = pathway;
	}

		
}
