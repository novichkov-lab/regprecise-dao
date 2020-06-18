package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class EffectorStatDTO {
	private int effectorId;
	private String effectorName;
	
	private  int effectorClassId;
	private String effectorClassName;
	
	private BigInteger totalGenomeCount;
	private BigInteger totalRegulogCount;
	private BigInteger totalRegulonCount;
	private BigInteger totalTaxonCount;
	
	private BigInteger tfCount;
	private BigInteger tfRegulogCount;
	private BigInteger tfSiteCount;
	private BigInteger tfRegulonCount;

	private BigInteger rnaCount;
	private BigInteger rnaRegulogCount;
	private BigInteger rnaSiteCount;
	private BigInteger rnaRegulonCount;
	
	
	public int getEffectorId() {
		return effectorId;
	}
	public void setEffectorId(int effectorId) {
		this.effectorId = effectorId;
	}
	public String getEffectorName() {
		return effectorName;
	}
	public void setEffectorName(String effectorName) {
		this.effectorName = effectorName;
	}
	public int getEffectorClassId() {
		return effectorClassId;
	}
	public void setEffectorClassId(int effectorClassId) {
		this.effectorClassId = effectorClassId;
	}
	public String getEffectorClassName() {
		return effectorClassName;
	}
	public void setEffectorClassName(String effectorClassName) {
		this.effectorClassName = effectorClassName;
	}
	public BigInteger getTotalGenomeCount() {
		return totalGenomeCount;
	}
	public void setTotalGenomeCount(BigInteger totalGenomeCount) {
		this.totalGenomeCount = totalGenomeCount;
	}
	public BigInteger getTotalRegulogCount() {
		return totalRegulogCount;
	}
	public void setTotalRegulogCount(BigInteger totalRegulogCount) {
		this.totalRegulogCount = totalRegulogCount;
	}
	public BigInteger getTfCount() {
		return tfCount;
	}
	public void setTfCount(BigInteger tfCount) {
		this.tfCount = tfCount;
	}
	public BigInteger getTfRegulogCount() {
		return tfRegulogCount;
	}
	public void setTfRegulogCount(BigInteger tfRegulogCount) {
		this.tfRegulogCount = tfRegulogCount;
	}
	public BigInteger getTfSiteCount() {
		return tfSiteCount;
	}
	public void setTfSiteCount(BigInteger tfSiteCount) {
		this.tfSiteCount = tfSiteCount;
	}
	public BigInteger getRnaCount() {
		return rnaCount;
	}
	public void setRnaCount(BigInteger rnaCount) {
		this.rnaCount = rnaCount;
	}
	public BigInteger getRnaRegulogCount() {
		return rnaRegulogCount;
	}
	public void setRnaRegulogCount(BigInteger rnaRegulogCount) {
		this.rnaRegulogCount = rnaRegulogCount;
	}
	public BigInteger getRnaSiteCount() {
		return rnaSiteCount;
	}
	public void setRnaSiteCount(BigInteger rnaSiteCount) {
		this.rnaSiteCount = rnaSiteCount;
	}
	public void setTotalTaxonCount(BigInteger totalTaxonCount) {
		this.totalTaxonCount = totalTaxonCount;
	}
	public BigInteger getTotalTaxonCount() {
		return totalTaxonCount;
	}
	public void setTfRegulonCount(BigInteger tfRegulonCount) {
		this.tfRegulonCount = tfRegulonCount;
	}
	public BigInteger getTfRegulonCount() {
		return tfRegulonCount;
	}
	public void setRnaRegulonCount(BigInteger rnaRegulonCount) {
		this.rnaRegulonCount = rnaRegulonCount;
	}
	public BigInteger getRnaRegulonCount() {
		return rnaRegulonCount;
	}
	public void setTotalRegulonCount(BigInteger totalRegulonCount) {
		this.totalRegulonCount = totalRegulonCount;
	}
	public BigInteger getTotalRegulonCount() {
		return totalRegulonCount;
	}
	
}
