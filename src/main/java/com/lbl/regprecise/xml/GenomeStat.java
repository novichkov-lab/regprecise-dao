package com.lbl.regprecise.xml;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class GenomeStat {

	private Integer genomeId;
	private String name;
	private Integer taxonomyId;
	
	private BigInteger tfRegulonCount;
	private BigInteger tfSiteCount;
	private BigInteger rnaRegulonCount;
	private BigInteger rnaSiteCount;
	
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
	public BigInteger getTfRegulonCount() {
		return tfRegulonCount;
	}
	public void setTfRegulonCount(BigInteger tfRegulonCount) {
		this.tfRegulonCount = tfRegulonCount;
	}
	public BigInteger getTfSiteCount() {
		return tfSiteCount;
	}
	public void setTfSiteCount(BigInteger tfSiteCount) {
		this.tfSiteCount = tfSiteCount;
	}
	public BigInteger getRnaRegulonCount() {
		return rnaRegulonCount;
	}
	public void setRnaRegulonCount(BigInteger rnaRegulonCount) {
		this.rnaRegulonCount = rnaRegulonCount;
	}
	public BigInteger getRnaSiteCount() {
		return rnaSiteCount;
	}
	public void setRnaSiteCount(BigInteger rnaSiteCount) {
		this.rnaSiteCount = rnaSiteCount;
	}
	
	
}
