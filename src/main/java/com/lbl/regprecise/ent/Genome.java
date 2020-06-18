package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Genome")
public class Genome extends IdObject implements Comparable<Genome>{
	@Id
	@GeneratedValue
	@Column(name = "genomeId")
	Integer id;
	
	String name;
	
	String phylum;
	Integer moId;
	private String kbaseId;
	
	@Transient
	private Integer index;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhylum() {
		return phylum;
	}
	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}
	public Integer getMoId() {
		return moId;
	}
	public void setMoId(Integer moId) {
		this.moId = moId;
	}
	

	public int compareTo(Genome o) {
		
		return name.compareTo(o.getName());
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getIndex() {
		return index;
	}
	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}
	public String getKbaseId() {
		return kbaseId;
	}
	
}
