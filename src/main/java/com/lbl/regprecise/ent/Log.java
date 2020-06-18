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
@Table(name = "Log")
public class Log{
	@Id
	@GeneratedValue
	@Column(name = "logId")	
	Integer id;
	
	Integer jobTermId;
	Integer errorTermId;
	String message;
	
	Integer pgpCollectionId;
	Integer collectionId;
	Integer regulogId;
	Integer regulonId;
	Integer genomeId;
	Integer regulatorId;
	Integer cronId;
	Integer operonId;
	Integer geneId;
	Integer siteId;
	Integer orthologId;
	
	@Column(columnDefinition="text")
	String description;

	public Log(){}
	
	public Log(Log log)
	{
		this.id = log.id;
		this.jobTermId = log.jobTermId;
		this.errorTermId = log.errorTermId;
		this.message = log.message;
		this.pgpCollectionId = log.pgpCollectionId;
		this.collectionId = log.collectionId;
		this.regulogId = log.regulogId;
		this.regulonId = log.regulonId;
		this.genomeId = log.genomeId;
		this.regulatorId = log.regulatorId;
		this.cronId = log.cronId;
		this.operonId = log.operonId;
		this.geneId = log.geneId;
		this.siteId = log.siteId;
		this.orthologId = log.orthologId;		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
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
	public Integer getGenomeId() {
		return genomeId;
	}
	public void setGenomeId(Integer genomeId) {
		this.genomeId = genomeId;
	}
	public Integer getOperonId() {
		return operonId;
	}
	public void setOperonId(Integer operonId) {
		this.operonId = operonId;
	}
	public Integer getGeneId() {
		return geneId;
	}
	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public Integer getOrthologId() {
		return orthologId;
	}
	public void setOrthologId(Integer orthologId) {
		this.orthologId = orthologId;
	}
	public Integer getJobTermId() {
		return jobTermId;
	}
	public void setJobTermId(Integer jobTermId) {
		this.jobTermId = jobTermId;
	}
	public Integer getErrorTermId() {
		return errorTermId;
	}
	public void setErrorTermId(Integer errorTermId) {
		this.errorTermId = errorTermId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setCronId(Integer cronId) {
		this.cronId = cronId;
	}
	public Integer getCronId() {
		return cronId;
	}
	public Integer getRegulatorId() {
		return regulatorId;
	}
	public void setRegulatorId(Integer regulatorId) {
		this.regulatorId = regulatorId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPgpCollectionId(Integer pgpCollectionId) {
		this.pgpCollectionId = pgpCollectionId;
	}
	public Integer getPgpCollectionId() {
		return pgpCollectionId;
	}
	
	
}
