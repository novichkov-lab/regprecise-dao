package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "SearchIndex")
public class SearchIndex extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "searchIndexId")	
	Integer id;
	
	Integer  objType;
	
	String objName;
	Integer objId;
	Integer regulonId;
	String genomeName;
	String regulatorName;
	Integer regulationTypeTermId;	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getObjType() {
		return objType;
	}
	public void setObjType(Integer objType) {
		this.objType = objType;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public Integer getObjId() {
		return objId;
	}
	public void setObjId(Integer objId) {
		this.objId = objId;
	}
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
	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}
	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}
		
	
	
}
