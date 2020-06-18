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
@Table(name = "Regulator")
public class Regulator extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "regulatorId")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulonId")		
	Regulon regulon;
	
	Integer moId;
	private String kbaseId;
	String locusTag;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Regulon getRegulon() {
		return regulon;
	}
	public void setRegulon(Regulon regulon) {
		this.regulon = regulon;
	}
	public Integer getMoId() {
		return moId;
	}
	public void setMoId(Integer moId) {
		this.moId = moId;
	}
	public String getLocusTag() {
		return locusTag;
	}
	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}
	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}
	public String getKbaseId() {
		return kbaseId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
