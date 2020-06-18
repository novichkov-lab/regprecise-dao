package com.lbl.regprecise.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class TestSearchIndexStatDTO {
	private Integer  objType;	
	private String objName;
	private Integer objId;
	private Integer regulonId;
	private String genomeName;
	private String regulatorName;
	
	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public Integer getObjType() {
		return objType;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}

	public Integer getRegulonId() {
		return regulonId;
	}

	public void setGenomeName(String genomeName) {
		this.genomeName = genomeName;
	}

	public String getGenomeName() {
		return genomeName;
	}

	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}

	public String getRegulatorName() {
		return regulatorName;
	}	
}