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
@Table(name = "PropagatedGene")
public class PropagatedGene extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "propagatedGeneId")
    private Integer id;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "propagatedRegulonId")			
    private PropagatedRegulon propagatedRegulon;
	
	private Integer moLocusId;
    private String moLocusTag;
    private Integer moLocusGI;
    private String moFunction;
    private String kbaseId;
    
    private String orthologRef;
    private Boolean trueRegulonMember;
    
    private Boolean isPublic;
    private Float minSiteScore;
    
    
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "propagatedGeneId")
	})	    
    private List<PropagatedSite> propagatedSites = new ArrayList<PropagatedSite>();
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "propagatedGeneId")
	})
    private List<PropagatedGene2GeneOrtholog> orthologousGenes = new ArrayList<PropagatedGene2GeneOrtholog>();
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "propagatedGeneId")
	})
    private List<PropagatedGeneScore> scores = new ArrayList<PropagatedGeneScore>();
	
	
	public List<PropagatedSite> getPublicSites()
	{
		List<PropagatedSite> publicSites = new ArrayList<PropagatedSite>();
		for(PropagatedSite site: propagatedSites)
		{
			if(site.getIsPublic() != null && site.getIsPublic().booleanValue())
			{
				publicSites.add(site);
			}
		}
		return publicSites;
	}
	
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

	public Integer getMoLocusId() {
		return moLocusId;
	}

	public void setMoLocusId(Integer moLocusId) {
		this.moLocusId = moLocusId;
	}

	public String getMoLocusTag() {
		return moLocusTag;
	}

	public void setMoLocusTag(String moLocusTag) {
		this.moLocusTag = moLocusTag;
	}

	public Integer getMoLocusGI() {
		return moLocusGI;
	}

	public void setMoLocusGI(Integer moLocusGI) {
		this.moLocusGI = moLocusGI;
	}

	public List<PropagatedSite> getPropagatedSites() {
		return propagatedSites;
	}

	public void setPropagatedSites(List<PropagatedSite> propagatedSites) {
		this.propagatedSites = propagatedSites;
	}


	public void setOrthologRef(String orthologRef) {
		this.orthologRef = orthologRef;
	}

	public String getOrthologRef() {
		return orthologRef;
	}

	public void setMoFunction(String moFunction) {
		this.moFunction = moFunction;
	}

	public String getMoFunction() {
		return moFunction;
	}

	public void setTrueRegulonMember(Boolean trueRegulonMember) {
		this.trueRegulonMember = trueRegulonMember;
	}

	public Boolean getTrueRegulonMember() {
		return trueRegulonMember;
	}

	public void setOrthologousGenes(List<PropagatedGene2GeneOrtholog> orthologousGenes) {
		this.orthologousGenes = orthologousGenes;
	}

	public List<PropagatedGene2GeneOrtholog> getOrthologousGenes() {
		return orthologousGenes;
	}

	public void setScores(List<PropagatedGeneScore> scores) {
		this.scores = scores;
	}

	public List<PropagatedGeneScore> getScores() {
		return scores;
	}

	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}

	public String getKbaseId() {
		return kbaseId;
	}

	public boolean hasSite(Integer relativePosition) {
		for(PropagatedSite pgpSite: propagatedSites)
		{
			if(pgpSite.getPosition().intValue() == relativePosition.intValue())
			{
				return true;
			}
		}
		return false;
	}

	public List<PropagatedSite> getNewSites(List<Site> sites) {
		List<PropagatedSite> newSites = new ArrayList<PropagatedSite>();
		for(PropagatedSite pgpSite: propagatedSites)
		{
			boolean isNewSite = true;
			for(Site site: sites)
			{
				if(pgpSite.getPosition().intValue() == site.getRelativePosition().intValue())
				{
					isNewSite = false;
					break;
				}
			}
			if(isNewSite) newSites.add(pgpSite);
		}
		return newSites;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setMinSiteScore(Float minSiteScore) {
		this.minSiteScore = minSiteScore;
	}

	public Float getMinSiteScore() {
		return minSiteScore;
	}


    
	
    
}
