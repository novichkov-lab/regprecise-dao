package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class Genome {

	private Integer genomeId;
	private String name;
	private Integer taxonomyId;
	
	public Integer getGenomeId() {
		return genomeId;
	}
	public void setGenomeId(Integer genomeId) {
		this.genomeId = genomeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTaxonomyId() {
		return taxonomyId;
	}
	public void setTaxonomyId(Integer taxonomyId) {
		this.taxonomyId = taxonomyId;
	}
	
	
}
