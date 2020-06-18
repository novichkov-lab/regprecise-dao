package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class CollectionStatDTO {

	private Integer collectionId;
	private Integer collectionTypeTermId;
	private String collectionName;
	private BigInteger totlaGenomeCount;
	private BigInteger totalRegulogCount;
	
	private BigInteger tfGenomeCount;
	private BigInteger tfCount;
	private BigInteger tfFamilyCount;
	private BigInteger tfRegulogCount;	
	private BigInteger tfSiteCount;
	private BigInteger tfRegulonCount;
	private BigInteger tfTaxonCount;
	
	
	private BigInteger rnaGenomeCount;
	private BigInteger rnaCount;
	private BigInteger rnaRegulogCount;
	private BigInteger rnaSiteCount;
	private BigInteger rnaRegulonCount;
	private BigInteger rnaTaxonCount;	
		
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public Integer getCollectionTypeTermId() {
		return collectionTypeTermId;
	}
	public void setCollectionTypeTermId(Integer collectionTypeTermId) {
		this.collectionTypeTermId = collectionTypeTermId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public BigInteger getTotlaGenomeCount() {
		return totlaGenomeCount;
	}
	public void setTotlaGenomeCount(BigInteger totlaGenomeCount) {
		this.totlaGenomeCount = totlaGenomeCount;
	}
	public BigInteger getTotalRegulogCount() {
		return totalRegulogCount;
	}
	public void setTotalRegulogCount(BigInteger totalRegulogCount) {
		this.totalRegulogCount = totalRegulogCount;
	}
	
	public int getTotalRegulonCount()
	{
		return tfRegulonCount.intValue() + rnaRegulonCount.intValue();
	}
	
	public BigInteger getTfCount() {
		return tfCount;
	}
	public void setTfCount(BigInteger tfCount) {
		this.tfCount = tfCount;
	}
	public BigInteger getTfFamilyCount() {
		return tfFamilyCount;
	}
	public void setTfFamilyCount(BigInteger tfFamilyCount) {
		this.tfFamilyCount = tfFamilyCount;
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
	public void setTfGenomeCount(BigInteger tfGenomeCount) {
		this.tfGenomeCount = tfGenomeCount;
	}
	public BigInteger getTfGenomeCount() {
		return tfGenomeCount;
	}
	public void setRnaGenomeCount(BigInteger rnaGenomeCount) {
		this.rnaGenomeCount = rnaGenomeCount;
	}
	public BigInteger getRnaGenomeCount() {
		return rnaGenomeCount;
	}
	public void setTfRegulonCount(BigInteger tfRegulonCount) {
		this.tfRegulonCount = tfRegulonCount;
	}
	public BigInteger getTfRegulonCount() {
		return tfRegulonCount;
	}
	public void setTfTaxonCount(BigInteger tfTaxonCount) {
		this.tfTaxonCount = tfTaxonCount;
	}
	public BigInteger getTfTaxonCount() {
		return tfTaxonCount;
	}
	public void setRnaRegulonCount(BigInteger rnaRegulonCount) {
		this.rnaRegulonCount = rnaRegulonCount;
	}
	public BigInteger getRnaRegulonCount() {
		return rnaRegulonCount;
	}
	public void setRnaTaxonCount(BigInteger rnaTaxonCount) {
		this.rnaTaxonCount = rnaTaxonCount;
	}
	public BigInteger getRnaTaxonCount() {
		return rnaTaxonCount;
	}	
}

