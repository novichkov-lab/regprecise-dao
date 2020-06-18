package com.lbl.regprecise.ent;

import java.util.ArrayList;
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
@Table(name = "Ortholog")
public class Ortholog extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "orthologId")
	Integer id;
	
	String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cronId")
	Cron cron;
	
	Integer moId;
	String function;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "orthologId")
	})	
	List<Gene> genes = new ArrayList<Gene>();

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


	public Integer getMoId() {
		return moId;
	}

	public void setMoId(Integer moId) {
		this.moId = moId;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<Gene> getGenes() {
		return genes;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public void addGene(Gene gene) {
		genes.add(gene);		
	}

	public void setCron(Cron cron) {
		this.cron = cron;
	}

	public Cron getCron() {
		return cron;
	}


	
	
}
