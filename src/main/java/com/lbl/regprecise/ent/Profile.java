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
@Table(name = "Profile")
public class Profile extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "profileId")
	Integer id;
	
	String fileName;
	
	@Column(columnDefinition="Text")
	private	String pattern;
	
	private String name;
	private Integer length;
	private Integer trainingSetSize;
	private Float informationContent;
	private String consensus;
	private Float minScore;
	private Integer typeTermId;
	private Boolean isSymmetric;
	private Boolean isFixedDirection;
	private String status;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	public String getPattern() {
		return pattern;
	}

	public String getOldPattern()
	{
		return "";
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setLength(Integer length) {
		this.length = length;
	}


	public Integer getLength() {
		return length;
	}


	public void setTrainingSetSize(Integer trainingSetSize) {
		this.trainingSetSize = trainingSetSize;
	}


	public Integer getTrainingSetSize() {
		return trainingSetSize;
	}


	public void setInformationContent(Float informationContent) {
		this.informationContent = informationContent;
	}


	public Float getInformationContent() {
		return informationContent;
	}


	public void setConsensus(String consensus) {
		this.consensus = consensus;
	}


	public String getConsensus() {
		return consensus;
	}
	public void setMinScore(Float minScore) {
		this.minScore = minScore;
	}
	public Float getMinScore() {
		return minScore;
	}


	public void setTypeTermId(Integer typeTermId) {
		this.typeTermId = typeTermId;
	}


	public Integer getTypeTermId() {
		return typeTermId;
	}


	public void setIsSymmetric(Boolean isSymmetric) {
		this.isSymmetric = isSymmetric;
	}


	public Boolean getIsSymmetric() {
		return isSymmetric;
	}


	public void setIsFixedDirection(Boolean isFixedDirection) {
		this.isFixedDirection = isFixedDirection;
	}


	public Boolean getIsFixedDirection() {
		return isFixedDirection;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getStatus() {
		return status;
	}
}
