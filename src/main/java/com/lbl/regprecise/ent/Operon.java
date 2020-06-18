package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Operon")
public class Operon extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "operonId")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulonId")		
	Regulon regulon;
	
	String firstGeneLocusTag;
	Integer firstGeneMOId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cronId")
	Cron cron;

		
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "operonId")
	})	
	@OrderBy("operonIndex")
	List<Gene> genes = new ArrayList<Gene>();
	
	
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

	public String getFirstGeneLocusTag() {
		return firstGeneLocusTag;
	}

	public void setFirstGeneLocusTag(String firstGeneLocusTag) {
		this.firstGeneLocusTag = firstGeneLocusTag;
	}

	public Integer getFirstGeneMOId() {
		return firstGeneMOId;
	}

	public void setFirstGeneMOId(Integer firstGeneMOId) {
		this.firstGeneMOId = firstGeneMOId;
	}

	public List<Gene> getGenes() {
		return genes;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public void addGene(Gene gene) {
		genes.add(gene);
		gene.setOperon(this);		
	}

	public void setCron(Cron cron) {
		this.cron = cron;
	}

	public Cron getCron() {
		return cron;
	}
	
	public List<Site> getSites()
	{
		List<Site> sites = new ArrayList<Site>();
		for(Gene gene: genes)
		{
			sites.addAll(gene.getSites());			
		}
		Collections.sort(sites);
		
		return sites;
	}
	
	public Gene getFirstGene()
	{
		return genes.get(0);
	}
	
	
	public void populateGeneMoIds(HashSet<Integer> moIds)
	{
		for(Gene gene: genes)
		{
			moIds.add(gene.getMoId());
		}		
	}
	
	public String getName()
	{
		StringBuffer sb = new StringBuffer();
		for(Gene gene: genes)
		{
			if(sb.length() > 0) sb.append("-");
			sb.append( gene.getName() != null? gene.getName(): gene.getLocusTag());
		}
		return sb.toString();
	}
	
	public boolean hasSites()
	{
		
		for(Gene gene : genes)
		{
			if(gene.getSites().size() > 0)
			{
				return true;
			}
		}
		return false;
	}

	public void reIndexGenes() {
		int index = 0;
		for(Gene gene: genes)
		{
			gene.setOperonIndex(index++);
		}
	}

	public boolean hasMajorSites() {
		
		for(Gene gene : genes)
		{
			for(Site site: gene.getSites())
			{
				if(site.isMajor) return true;
			}
		}
		return false;
	}

	public String exportGenes() {
		StringBuffer sb = new StringBuffer();
		for(Gene gene: genes)
		{
			sb.append(gene.getMoId());
			sb.append("\t" + gene.getLocusTag());
			sb.append("\t" + gene.getName());
			sb.append("\n");
		}
		
		return sb.toString();
	}
		
}
