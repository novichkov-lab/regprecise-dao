package com.lbl.regprecise.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Pavel Novichkov
 *
 */
public class PropagatedGenomeStatDTO {
	Integer genomeId;
	String genomeName;
	BigInteger regulonCount;
	BigDecimal siteCount;
	
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
	public int getRegulonCount() {
		return regulonCount.intValue();
	}
	public void setRegulonCount(BigInteger regulonCount) {
		this.regulonCount = regulonCount;
	}
	public int getSiteCount() {
		return siteCount.intValue();
	}
	public void setSiteCount(BigDecimal siteCount) {
		this.siteCount = siteCount;
	}	
}
