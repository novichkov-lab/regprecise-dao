package com.lbl.regprecise.dto;

import com.lbl.regprecise.ent.Term;

/**
 * @author Pavel Novichkov
 *
 */
public class KBaseRegulonDTO {
	private String queryKBaseId;	
	private String kbaseId;
	private String regulatorName;
	private Integer regulationTypeTermId;
	

	public String getRegulationType()
	{
		return regulationTypeTermId == Term.TERM_REGULATION_TYPE_TF ? "TF" : "RNA";
	}
	
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}
	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}
	public String getKbaseId() {
		return kbaseId;
	}
	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}
	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}
	public void setQueryKBaseId(String queryKBaseId) {
		this.queryKBaseId = queryKBaseId;
	}
	public String getQueryKBaseId() {
		return queryKBaseId;
	}
}
