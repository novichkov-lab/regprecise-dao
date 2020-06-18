package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class Regulog {

	private Integer regulogId;
	private String regulationType;
	private String regulatorName;
	private String regulatorFamily;
	private String taxonName;	
	private String effector;
	private String pathway;

	
	public Integer getRegulogId() {
		return regulogId;
	}

	public void setRegulogId(Integer regulogId) {
		this.regulogId = regulogId;
	}

	public String getRegulatorName() {
		return regulatorName;
	}

	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}

	public String getTaxonName() {
		return taxonName;
	}

	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}

	public String getEffector() {
		return effector;
	}

	public void setEffector(String effector) {
		this.effector = effector;
	}

	public String getPathway() {
		return pathway;
	}

	public void setPathway(String pathway) {
		this.pathway = pathway;
	}

	public String getRegulatorFamily() {
		return regulatorFamily;
	}

	public void setRegulatorFamily(String regulatorFamily) {
		this.regulatorFamily = regulatorFamily;
	}

	public String getRegulationType() {
		return regulationType;
	}

	public void setRegulationType(String regulationType) {
		this.regulationType = regulationType;
	}
}
