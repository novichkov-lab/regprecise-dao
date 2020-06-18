package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class RegulonRef {

	private Integer regulonId;
	private String genomeName;
	private String regulatorName;
	
	private String  foundObjType;
	private String foundObjName;
	
	public Integer getRegulonId() {
		return regulonId;
	}
	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}
	public String getGenomeName() {
		return genomeName;
	}
	public void setGenomeName(String genomeName) {
		this.genomeName = genomeName;
	}
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}
	public String getFoundObjType() {
		return foundObjType;
	}
	public void setFoundObjType(String foundObjType) {
		this.foundObjType = foundObjType;
	}
	public String getFoundObjName() {
		return foundObjName;
	}
	public void setFoundObjName(String foundObjName) {
		this.foundObjName = foundObjName;
	}
}
