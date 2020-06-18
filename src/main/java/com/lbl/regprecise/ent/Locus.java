package com.lbl.regprecise.ent;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Locus")
public class Locus extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "locusId")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulonId")		
	Regulon regulon;
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "locusId")
	})	
	List<Operon> operons;
	

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



	public List<Operon> getOperons() {
		return operons;
	}



	public void setOperons(List<Operon> operons) {
		this.operons = operons;
	}		
}
