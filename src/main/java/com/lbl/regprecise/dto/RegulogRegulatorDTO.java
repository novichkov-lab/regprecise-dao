package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class RegulogRegulatorDTO {

	private Integer regulogId;
	private Integer regulonId;
	private String regulatorName;
	private Integer regulatorId;
	private Integer regulatorVimssId;
	private String regulatorLocusTag;
	private BigInteger regulonSiteCount;
	private String aaSequence;
	private String genomeName;
	private Integer taxonId;
	private String taxonName;
	private String phylum;
	private String pathway;
	private String effector;
	
	
	public Integer getRegulogId() {
		return regulogId;
	}
	public void setRegulogId(Integer regulogId) {
		this.regulogId = regulogId;
	}
	public Integer getRegulonId() {
		return regulonId;
	}
	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}
	public Integer getRegulatorId() {
		return regulatorId;
	}
	public void setRegulatorId(Integer regulatorId) {
		this.regulatorId = regulatorId;
	}
	public Integer getRegulatorVimssId() {
		return regulatorVimssId;
	}
	public void setRegulatorVimssId(Integer regulatorVimssId) {
		this.regulatorVimssId = regulatorVimssId;
	}
	public String getRegulatorLocusTag() {
		return regulatorLocusTag;
	}
	public void setRegulatorLocusTag(String regulatorLocusTag) {
		this.regulatorLocusTag = regulatorLocusTag;
	}
	public int getRegulonSiteCount() {
		return regulonSiteCount.intValue();
	}
	public void setRegulonSiteCount(BigInteger regulonSiteCount) {
		this.regulonSiteCount = regulonSiteCount;
	}
	public void setAaSequence(String aaSequence) {
		this.aaSequence = aaSequence;
	}
	public String getAaSequence() {
		return aaSequence;
	}
	public void setGenomeName(String genomeName) {
		this.genomeName = genomeName;
	}
	public String getGenomeName() {
		return genomeName;
	}
	public void setTaxonId(Integer taxonId) {
		this.taxonId = taxonId;
	}
	public Integer getTaxonId() {
		return taxonId;
	}
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
	public String getTaxonName() {
		return taxonName;
	}
	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}
	public String getPhylum() {
		return phylum;
	}
	public void setPathway(String pathway) {
		this.pathway = pathway;
	}
	public String getPathway() {
		return pathway;
	}
	public void setEffector(String effector) {
		this.effector = effector;
	}
	public String getEffector() {
		return effector;
	}
}
