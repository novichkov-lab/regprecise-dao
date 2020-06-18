package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class RegulogCollectionStat {

	private String collectionType;
	private Integer collectionId;
	
	private String className;
	private String name;	
	
	private Integer totalGenomeCount;
	private Integer totalRegulogCount;
	
	private Integer tfCount;
	private Integer tfRegulogCount;	
	private Integer tfSiteCount;
	
	private Integer rnaCount;
	private Integer rnaRegulogCount;
	private Integer rnaSiteCount;
	
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalRegulogCount() {
		return totalRegulogCount;
	}
	public void setTotalRegulogCount(Integer totalRegulogCount) {
		this.totalRegulogCount = totalRegulogCount;
	}

	public Integer getTfCount() {
		return tfCount;
	}
	public void setTfCount(Integer tfCount) {
		this.tfCount = tfCount;
	}

	public Integer getTfRegulogCount() {
		return tfRegulogCount;
	}
	public void setTfRegulogCount(Integer tfRegulogCount) {
		this.tfRegulogCount = tfRegulogCount;
	}
	public Integer getTfSiteCount() {
		return tfSiteCount;
	}
	public void setTfSiteCount(Integer tfSiteCount) {
		this.tfSiteCount = tfSiteCount;
	}

	public Integer getRnaCount() {
		return rnaCount;
	}
	public void setRnaCount(Integer rnaCount) {
		this.rnaCount = rnaCount;
	}
	public Integer getRnaRegulogCount() {
		return rnaRegulogCount;
	}
	public void setRnaRegulogCount(Integer rnaRegulogCount) {
		this.rnaRegulogCount = rnaRegulogCount;
	}
	public Integer getRnaSiteCount() {
		return rnaSiteCount;
	}
	public void setRnaSiteCount(Integer rnaSiteCount) {
		this.rnaSiteCount = rnaSiteCount;
	}
	public void setTotalGenomeCount(Integer totalGenomeCount) {
		this.totalGenomeCount = totalGenomeCount;
	}
	public Integer getTotalGenomeCount() {
		return totalGenomeCount;
	}

}
