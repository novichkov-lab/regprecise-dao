package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "ComputationalValidation")
public class ComputationalValidation extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "compValidationId")
	Integer id;
	
	@Column(nullable=false)
	Integer orderIndex;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "articleId", nullable=false)		
	Article article;
	
	@ManyToMany
	@JoinTable(name="ComputationalValidation2Regulog",
			joinColumns={@JoinColumn(name="compValidationId")},
			inverseJoinColumns={@JoinColumn(name="regulogId")}
	)	
	List<Regulog> regulogs = new ArrayList<Regulog>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Regulog> getRegulogs() {
		return regulogs;
	}

	public void setRegulogs(List<Regulog> regulogs) {
		this.regulogs = regulogs;
	}
	
}
