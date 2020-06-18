package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "ExperimentalValidation")
public class ExperimentalValidation extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "expValidationId")
	Integer id;
	
	String organism;
	String experimentTypes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "articleId")
	private		
	Article article;	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOrganism() {
		return organism;
	}


	public void setOrganism(String organism) {
		this.organism = organism;
	}


	public String getExperimentTypes() {
		return experimentTypes;
	}


	public void setExperimentTypes(String experimentTypes) {
		this.experimentTypes = experimentTypes;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public Article getArticle() {
		return article;
	}	
}
