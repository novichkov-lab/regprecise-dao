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
@Table(name = "PropagatedGeneScore")
public class PropagatedGeneScore {

	@Id
	@GeneratedValue
	@Column(name = "propagatedGeneScoreId")
    Integer id;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "propagatedGeneId")			
    PropagatedGene propagatedGene;
	
	Integer typeTermId;
	Float score;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PropagatedGene getPropagatedGene() {
		return propagatedGene;
	}
	public void setPropagatedGene(PropagatedGene propagatedGene) {
		this.propagatedGene = propagatedGene;
	}
	public Integer getTypeTermId() {
		return typeTermId;
	}
	public void setTypeTermId(Integer typeTermId) {
		this.typeTermId = typeTermId;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}

}
