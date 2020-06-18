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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Gene")
public class Gene extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "geneId")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulonId")	
	Regulon regulon;
	
	private String name;
	Integer moId;
	private String kbaseId;
	String  locusTag;
	String  function;
	String  proteinSequence;
	
	private Character strand;
	private Integer distanceToPrevGene;
	private Integer posMin;
	private Integer posMax;	
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "operonId")		
	Operon  operon;
	
	Integer operonIndex;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name = "orthologId", insertable=false, updatable=true)		
	Ortholog ortholog;
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE, CascadeType.DELETE_ORPHAN})	
	@JoinColumns({
		@JoinColumn(name = "geneId")
	})	
	List<Site> sites = new ArrayList<Site>();

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

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getProteinSequence() {
		return proteinSequence;
	}

	public void setProteinSequence(String proteinSequence) {
		this.proteinSequence = proteinSequence;
	}

	public Ortholog getOrtholog() {
		return ortholog;
	}

	public void setOrtholog(Ortholog ortholog) {
		this.ortholog = ortholog;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public void addToOperon(Operon operon) {
		this.operon = operon;
		operon.addGene(this);
		
	}

	public Operon getOperon() {
		return operon;
	}

	public void setOperon(Operon operon) {
		this.operon = operon;
	}

	public Integer getOperonIndex() {
		return operonIndex;
	}

	public void setOperonIndex(Integer operonIndex) {
		this.operonIndex = operonIndex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public boolean hasMajorSites()
	{
		for(Site site: sites)
		{
			if(site.isMajor) return true;
		}
		return false;
	}

	public boolean hasSites()
	{
		return sites.size() > 0;
	}

	public void setStrand(Character strand) {
		this.strand = strand;
	}

	public Character getStrand() {
		return strand;
	}

	public void setDistanceToPrevGene(Integer distanceToPrevGene) {
		this.distanceToPrevGene = distanceToPrevGene;
	}

	public Integer getDistanceToPrevGene() {
		return distanceToPrevGene;
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

	public int getMajorSitesCount() {
		int count = 0;
		for(Site site: sites)
		{
			if(site.getIsMajor())
			{
				count++;
			}
		}
		return count;
	}

	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}

	public String getKbaseId() {
		return kbaseId;
	}	
}
