package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class GenomeStatDTO {
	private Integer genomeId;
	private String genomeName;
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
	public String getGenomeName() {
		return genomeName;
	}
	public void setGenomeName(String genomeName) {
		this.genomeName = genomeName;
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
	public void setTaxonomyId(Integer taxonomyId) {
		this.taxonomyId = taxonomyId;
	}
	public Integer getTaxonomyId() {
		return taxonomyId;
	}
	
	public int getTotalRegulonCount()
	{
		return tfRegulonCount.intValue() + rnaRegulonCount.intValue();
	}

	public int getTotalSiteCount()
	{
		return tfSiteCount.intValue() + rnaSiteCount.intValue();
	}

}

