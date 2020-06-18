package com.lbl.regprecise.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.lbl.regprecise.ent.Term;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class RegulonDTO {

	private Integer regulonId;
	private Integer regulogId;
	private Integer genomeId;	
	
	private String taxonName;
	private String phylum;
	private String regulatorName;
	private String tfFamily;
	private String rnaFamily;
	private String regulatorModeOfAction;
	private String effector;
	private String function;
	private Integer regulationTypeTermId;
	
	
	public Integer getRegulonId() {
		return regulonId;
	}
	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}
	public Integer getRegulogId() {
		return regulogId;
	}
	public void setRegulogId(Integer regulogId) {
		this.regulogId = regulogId;
	}
	public String getTaxonName() {
		return taxonName;
	}
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
	public String getPhylum() {
		return phylum;
	}
	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}
	public String getRegulatorFamily() {
		return regulationTypeTermId == Term.TERM_REGULATION_TYPE_TF ? tfFamily : rnaFamily;
	}

	public String getRegulatorModeOfAction() {
		return regulatorModeOfAction;
	}
	public void setRegulatorModeOfAction(String regulatorModeOfAction) {
		this.regulatorModeOfAction = regulatorModeOfAction;
	}
	public String getEffector() {
		return effector;
	}
	public void setEffector(String effector) {
		this.effector = effector;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}
	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}
	public Integer getGenomeId() {
		return genomeId;
	}
	public void setGenomeId(Integer genomeId) {
		this.genomeId = genomeId;
	}
}
