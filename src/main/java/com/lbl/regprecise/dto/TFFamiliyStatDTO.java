package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class TFFamiliyStatDTO {
	
	private Integer tfFamilyId;
	private String tfFamilyName;
	private String proteinDomains;
	private BigInteger genomeCount;
	private BigInteger regulogCount;
	private BigInteger tfCount;
	private BigInteger siteCount;	
	private BigInteger regulonCount;
	private BigInteger taxonCount;
	

	public String getProteinDomains() {
		return proteinDomains;
	}
	public void setProteinDomains(String proteinDomains) {
		this.proteinDomains = proteinDomains;
	}
	public BigInteger getGenomeCount() {
		return genomeCount;
	}
	public void setGenomeCount(BigInteger genomeCount) {
		this.genomeCount = genomeCount;
	}
	public BigInteger getRegulogCount() {
		return regulogCount;
	}
	public void setRegulogCount(BigInteger regulogCount) {
		this.regulogCount = regulogCount;
	}
	public BigInteger getTfCount() {
		return tfCount;
	}
	public void setTfCount(BigInteger tfCount) {
		this.tfCount = tfCount;
	}
	public BigInteger getSiteCount() {
		return siteCount;
	}
	public void setSiteCount(BigInteger siteCount) {
		this.siteCount = siteCount;
	}
	public void setTfFamilyName(String tfFamilyName) {
		this.tfFamilyName = tfFamilyName;
	}
	public String getTfFamilyName() {
		return tfFamilyName;
	}
	public void setTfFamilyId(Integer tfFamilyId) {
		this.tfFamilyId = tfFamilyId;
	}
	public Integer getTfFamilyId() {
		return tfFamilyId;
	}
	public void setRegulonCount(BigInteger regulonCount) {
		this.regulonCount = regulonCount;
	}
	public BigInteger getRegulonCount() {
		return regulonCount;
	}
	public void setTaxonCount(BigInteger taxonCount) {
		this.taxonCount = taxonCount;
	}
	public BigInteger getTaxonCount() {
		return taxonCount;
	}
	
	
}
