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
@Table(name = "ProcessState")
public class ProcessState extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "processId")
	Integer id;

	private Integer percentDone;
	private String description;
	private Boolean done;
	private String modelId;	
	
	
	@Override
	public Integer getId() {
		return id;
	}


	public Integer getPercentDone() {
		return percentDone;
	}


	public void setPercentDone(Integer percentDone) {
		this.percentDone = percentDone;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean getDone() {
		return done;
	}


	public void setDone(Boolean done) {
		this.done = done;
	}


	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

}
