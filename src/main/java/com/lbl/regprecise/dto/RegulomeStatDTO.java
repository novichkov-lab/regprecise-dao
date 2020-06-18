package com.lbl.regprecise.dto;

import java.math.BigInteger;

/**
 * @author Pavel Novichkov
 *
 */
public class RegulomeStatDTO {
	
	private Integer regulomeId;
	private String regulomeKBaseId;
	private Integer genomeId;
	private String genomeKBaseId;
	private Integer genomeMOId;
	private String genomeName;
	
	private BigInteger tfRegulonCount;
	private BigInteger rnaRegulonCount;
	
	public Integer getRegulomeId() {
		return regulomeId;
	}
	public void setRegulomeId(Integer regulomeId) {
		this.regulomeId = regulomeId;
	}
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
	public BigInteger getRnaRegulonCount() {
		return rnaRegulonCount;
	}
	public void setRnaRegulonCount(BigInteger rnaRegulonCount) {
		this.rnaRegulonCount = rnaRegulonCount;
	}
	public void setRegulomeKBaseId(String regulomeKBaseId) {
		this.regulomeKBaseId = regulomeKBaseId;
	}
	public String getRegulomeKBaseId() {
		return regulomeKBaseId;
	}
	public void setGenomeKBaseId(String genomeKBaseId) {
		this.genomeKBaseId = genomeKBaseId;
	}
	public String getGenomeKBaseId() {
		return genomeKBaseId;
	}
	public void setGenomeMOId(Integer genomeMOId) {
		this.genomeMOId = genomeMOId;
	}
	public Integer getGenomeMOId() {
		return genomeMOId;
	}
}
