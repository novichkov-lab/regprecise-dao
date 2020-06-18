package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class RiboswitchStatDTO {
	
	private Integer riboswitchId;
	private String riboswitchName;
	private String rfamId;
	private BigInteger genomeCount;
	private BigInteger regulogCount;
	private BigInteger siteCount;
	private BigInteger regulonCount;
	private BigInteger taxonCount;	
	
	public Integer getRiboswitchId() {
		return riboswitchId;
	}
	public void setRiboswitchId(Integer riboswitchId) {
		this.riboswitchId = riboswitchId;
	}

	public String getRfamId() {
		return rfamId;
	}
	public void setRfamId(String rfamId) {
		this.rfamId = rfamId;
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
	public BigInteger getSiteCount() {
		return siteCount;
	}
	public void setSiteCount(BigInteger siteCount) {
		this.siteCount = siteCount;
	}
	public void setRiboswitchName(String riboswitchName) {
		this.riboswitchName = riboswitchName;
	}
	public String getRiboswitchName() {
		return riboswitchName;
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
