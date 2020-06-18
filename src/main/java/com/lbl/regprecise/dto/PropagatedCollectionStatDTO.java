package com.lbl.regprecise.dto;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class PropagatedCollectionStatDTO {

	Integer propagatedCollectionId;
	String targetTaxonName;
	BigInteger targetGenomeCount;
	BigInteger targetRegulonCount;
	Integer sourceRegulogCollectionId;
	String sourceRegulogCollectionName;
	BigInteger sourceGenomeCount;
	BigInteger sourceRegulogCount;
	BigInteger sourceRegulonCount;
	
	public Integer getPropagatedCollectionId() {
		return propagatedCollectionId;
	}
	public void setPropagatedCollectionId(Integer propagatedCollectionId) {
		this.propagatedCollectionId = propagatedCollectionId;
	}
	public String getTargetTaxonName() {
		return targetTaxonName;
	}
	public void setTargetTaxonName(String targetTaxonName) {
		this.targetTaxonName = targetTaxonName;
	}
	public int getTargetGenomeCount() {
		return targetGenomeCount.intValue();
	}
	public void setTargetGenomeCount(BigInteger targetGenomeCount) {
		this.targetGenomeCount = targetGenomeCount;
	}
	public int getTargetRegulonCount() {
		return targetRegulonCount.intValue();
	}
	public void setTargetRegulonCount(BigInteger targetRegulonCount) {
		this.targetRegulonCount = targetRegulonCount;
	}
	public Integer getSourceRegulogCollectionId() {
		return sourceRegulogCollectionId;
	}
	public void setSourceRegulogCollectionid(Integer sourceRegulogCollectionId) {
		this.sourceRegulogCollectionId = sourceRegulogCollectionId;
	}
	public String getSourceRegulogCollectionName() {
		return sourceRegulogCollectionName;
	}
	public void setSourceRegulogCollectionName(String sourceRegulogCollectionName) {
		this.sourceRegulogCollectionName = sourceRegulogCollectionName;
	}
	public int getSourceGenomeCount() {
		return sourceGenomeCount.intValue();
	}
	public void setSourceGenomeCount(BigInteger sourceGenomeCount) {
		this.sourceGenomeCount = sourceGenomeCount;
	}
	public void setSourceRegulogCount(BigInteger sourceRegulogCount) {
		this.sourceRegulogCount = sourceRegulogCount;
	}
	public int getSourceRegulogCount() {
		return sourceRegulogCount.intValue();
	}
	public void setSourceRegulonCount(BigInteger sourceRegulonCount) {
		this.sourceRegulonCount = sourceRegulonCount;
	}
	public int getSourceRegulonCount() {
		return sourceRegulonCount.intValue();
	}
	
}
