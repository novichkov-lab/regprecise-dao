package com.lbl.regprecise.dto;

/**
 * @author Pavel Novichkov
 *
 */
public class KBaseGene2GeneDTO {
	private String geneKBaseId1;
	private String geneKBaseId2;
	private String regulonKBaseId;
	private String regulatorName;
	private Integer regulationTypeTermId;
	
	public String getGeneKBaseId1() {
		return geneKBaseId1;
	}
	public void setGeneKBaseId1(String geneKBaseId1) {
		this.geneKBaseId1 = geneKBaseId1;
	}
	public String getGeneKBaseId2() {
		return geneKBaseId2;
	}
	public void setGeneKBaseId2(String geneKBaseId2) {
		this.geneKBaseId2 = geneKBaseId2;
	}
	public String getRegulonKBaseId() {
		return regulonKBaseId;
	}
	public void setRegulonKBaseId(String regulonKBaseId) {
		this.regulonKBaseId = regulonKBaseId;
	}
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}
	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}
	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}
	
	
}
