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
@Table(name = "PropagatedSite")
public class PropagatedSite extends IdObject{

	@Id
	@GeneratedValue
	@Column(name = "propagatedSiteId")
	Integer id;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "propagatedRegulonId")			
    PropagatedRegulon propagatedRegulon;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "propagatedGeneId")
	private			
    PropagatedGene propagatedGene;

    Integer position;
    String sequence;
    Float score;
    
    private Boolean isPublic;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PropagatedRegulon getPropagatedRegulon() {
		return propagatedRegulon;
	}
	public void setPropagatedRegulon(PropagatedRegulon propagatedRegulon) {
		this.propagatedRegulon = propagatedRegulon;
	}

	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public void setPropagatedGene(PropagatedGene propagatedGene) {
		this.propagatedGene = propagatedGene;
	}
	public PropagatedGene getPropagatedGene() {
		return propagatedGene;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
    
    
}
