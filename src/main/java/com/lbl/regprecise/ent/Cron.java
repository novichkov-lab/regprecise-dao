package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.Hashtable;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Cron")
public class Cron extends IdObject{
	@Id
	@GeneratedValue	
	@Column(name = "cronId")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulogId")	
	Regulog regulog;	
	
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	@JoinColumns({
		@JoinColumn(name = "cronId")
	})	
	List<Operon> operons = new ArrayList<Operon>();

	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "cronId")
	})
	List<Ortholog> orthologs = new ArrayList<Ortholog>();	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Regulog getRegulog() {
		return regulog;
	}


	public void setRegulog(Regulog regulog) {
		this.regulog = regulog;
	}


	public List<Operon> getOperons() {
		return operons;
	}


	public void setOperons(List<Operon> operons) {
		this.operons = operons;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}
	
	public void setOrthologs(List<Ortholog> orthologs) {
		this.orthologs = orthologs;
	}

	public List<Ortholog> getOrthologs() {
		return orthologs;
	}

	public void addOrtholog(Ortholog ortholog) {
		ortholog.setCron(this);
		orthologs.add(ortholog);		
	}	
	
	public Ortholog getOrthologByMOId(int orthologMoid)
	{
		for(Ortholog ortholog: getOrthologs())
		{
			if(ortholog.getMoId() == orthologMoid)
			{
				return ortholog;
			}
		}			
		return null;
	}	

	public void updateOrthologsNameFunciton() {
		Hashtable<Genome,String> genome2Name = new Hashtable<Genome, String>(); 
		Hashtable<Genome,String> genome2Function = new Hashtable<Genome, String>();
		Hashtable<Genome,String> genome2LocusTag = new Hashtable<Genome, String>();
		
		for(Ortholog ortholog: orthologs)
		{
			genome2Name.clear();
			genome2Function.clear();
			genome2LocusTag.clear();
			
			//Populate hashes
			for(Gene gene: ortholog.getGenes())
			{
				Genome genome = gene.getRegulon().getGenome();
				if(gene.getName() != null) genome2Name.put(genome, gene.getName());
				if(gene.getFunction() != null) genome2Function.put(genome, gene.getFunction());
				if(gene.getLocusTag() != null) genome2LocusTag.put(genome, gene.getLocusTag());
			}
			
			//Search the best name
			String bestName = null;
			String bestFunction = null;
			String bestLocusTag = null;
			for(Regulon regulon: regulog.getRegulons())
			{
				if(bestName == null)
				{
					bestName =  genome2Name.get(regulon.getGenome());
				}
				if(bestLocusTag == null)
				{
					bestLocusTag = genome2LocusTag.get(regulon.getGenome());
				}
				if(bestFunction == null)
				{
					String function = genome2Function.get(regulon.getGenome());						
					bestFunction =  (function != null && function.equals("hypothetical")) ? null : function;
				}		
			}
			if(bestName == null) bestName = bestLocusTag;			
			if(bestName == null) bestName = "ort_" + ortholog.getMoId();
			if(bestFunction == null) bestFunction = "unknown";
			ortholog.setName(bestName);
			ortholog.setFunction(bestFunction);
		}		
	}

	public List<Site> getSites()
	{
		List<Site> sites = new ArrayList<Site>();
		for(Operon operon: operons)
		{
			sites.addAll(operon.getSites());			
		}
		return sites;
	}
	
}
