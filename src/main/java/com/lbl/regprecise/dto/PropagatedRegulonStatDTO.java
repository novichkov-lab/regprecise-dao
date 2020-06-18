package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class PropagatedRegulonStatDTO {
	int propagatedRegulonId;
	int regulogId;
	int genomeId;
	BigInteger geneCount;
	BigInteger regulatorCount;
	
	public int getPropagatedRegulonId() {
		return propagatedRegulonId;
	}
	public void setPropagatedRegulonId(int propagatedRegulonId) {
		this.propagatedRegulonId = propagatedRegulonId;
	}
	public int getRegulogId() {
		return regulogId;
	}
	public void setRegulogId(int regulogId) {
		this.regulogId = regulogId;
	}
	public int getGenomeId() {
		return genomeId;
	}
	public void setGenomeId(int genomeId) {
		this.genomeId = genomeId;
	}
	public int getGeneCount() {
		return geneCount.intValue();
	}
	public void setGeneCount(BigInteger geneCount) {
		this.geneCount = geneCount;
	}
	public int getRegulatorCount() {
		return regulatorCount.intValue();
	}
	public void setRegulatorCount(BigInteger regulatorCount) {
		this.regulatorCount = regulatorCount;
	}
	
	
}
