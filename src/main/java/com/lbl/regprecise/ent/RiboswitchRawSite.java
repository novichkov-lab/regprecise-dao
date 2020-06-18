package com.lbl.regprecise.ent;

import java.util.Date;

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
@Table(name = "RiboswitchRawSite")
public class RiboswitchRawSite {
	@Id
	@GeneratedValue
	@Column(name = "riboswitchRawSiteId")
	private Integer id;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "riboswitchId")
	Riboswitch riboswitch;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "genomeId")
	Genome genome;
	
	private Integer moSaffoldId;
	private Integer posMin;
	private Integer posMax;
	private Character strand;
	private Float score;
	private Double evalue;
	private Date insertDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Riboswitch getRiboswitch() {
		return riboswitch;
	}
	public void setRiboswitch(Riboswitch riboswitch) {
		this.riboswitch = riboswitch;
	}
	public Genome getGenome() {
		return genome;
	}
	public void setGenome(Genome genome) {
		this.genome = genome;
	}
	public Integer getMoSaffoldId() {
		return moSaffoldId;
	}
	public void setMoSaffoldId(Integer moSaffoldId) {
		this.moSaffoldId = moSaffoldId;
	}

	public Character getStrand() {
		return strand;
	}
	public void setStrand(Character strand) {
		this.strand = strand;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Double getEvalue() {
		return evalue;
	}
	public void setEvalue(Double evalue) {
		this.evalue = evalue;
	}
	public void setPosMin(Integer posMin) {
		this.posMin = posMin;
	}
	public Integer getPosMin() {
		return posMin;
	}
	public void setPosMax(Integer posMax) {
		this.posMax = posMax;
	}
	public Integer getPosMax() {
		return posMax;
	}
	public int getPosStart() {
		return strand == '+' ? posMin : posMax;
	}
	
	public int getPosEnd() {
		return strand == '+' ? posMax : posMin;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getInsertDate() {
		return insertDate;
	}
}
