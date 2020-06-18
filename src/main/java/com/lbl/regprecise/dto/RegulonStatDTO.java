package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class RegulonStatDTO {
	private Integer regulonId;
	private Integer regulogId;
	private Integer genomeId;
	private BigInteger operonsWithSiteCount;

	public Integer getRegulogId() {
		return regulogId;
	}

	public void setRegulogId(Integer regulogId) {
		this.regulogId = regulogId;
	}

	public Integer getGenomeId() {
		return genomeId;
	}

	public void setGenomeId(Integer genomeId) {
		this.genomeId = genomeId;
	}



	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}

	public Integer getRegulonId() {
		return regulonId;
	}

	public void setOperonsWithSiteCount(BigInteger operonsWithSiteCount) {
		this.operonsWithSiteCount = operonsWithSiteCount;
	}

	public int getOperonsWithSiteCount() {
		return operonsWithSiteCount.intValue();
	}
	
	
}
