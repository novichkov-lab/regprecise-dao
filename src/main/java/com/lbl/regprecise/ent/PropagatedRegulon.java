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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "PropagatedRegulon")
public class PropagatedRegulon extends IdObject{
	
	@Id
	@GeneratedValue
	@Column(name = "propagatedRegulonId")
    private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulogId")			    
    private Regulog regulog;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "genomeId")		
	private Genome genome;		    
    
	private Integer typeTermId;
	private String kbaseId;
	private String regulatorName;
	
	private Float minSiteScore; 
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="PropagatedCollection2Regulon",
			joinColumns={@JoinColumn(name="propagatedRegulonId")},
			inverseJoinColumns={@JoinColumn(name="propagatedCollectionId")}
	)
	private List<PropagatedCollection> propagatedCollections = new ArrayList<PropagatedCollection>();	
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "propagatedRegulonId")
	})	     
    private List<PropagatedRegulator> propagatedRegulators = new ArrayList<PropagatedRegulator>();
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "propagatedRegulonId")
	})	     
    private List<PropagatedGene> propagatedGenes = new ArrayList<PropagatedGene>();

	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "propagatedRegulonId")
	})
	private List<PropagatedSite> propagatedSites = new ArrayList<PropagatedSite>();	
	
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


	public List<PropagatedRegulator> getPropagatedRegulators() {
		return propagatedRegulators;
	}

	public void setPropagatedRegulators(
			List<PropagatedRegulator> propagatedRegulators) {
		this.propagatedRegulators = propagatedRegulators;
	}

	public List<PropagatedGene> getPropagatedGenes() {
		return propagatedGenes;
	}

	public void setPropagatedGenes(List<PropagatedGene> propagatedGenes) {
		this.propagatedGenes = propagatedGenes;
	}

	public Genome getGenome() {
		return genome;
	}

	public void setGenome(Genome genome) {
		this.genome = genome;
	}

	public void setTypeTermId(Integer typeTermId) {
		this.typeTermId = typeTermId;
	}

	public Integer getTypeTermId() {
		return typeTermId;
	}

	public void setMinSiteScore(Float minSiteScore) {
		this.minSiteScore = minSiteScore;
	}

	public Float getMinSiteScore() {
		return minSiteScore;
	}

	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}

	public String getKbaseId() {
		return kbaseId;
	}

	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}

	public String getRegulatorName() {
		return regulatorName;
	}

	public boolean hasRegulator(String locusTag) {
		for(PropagatedRegulator regulator: propagatedRegulators)
		{
			if(regulator.getMoLocusTag() == null) continue;
			if(regulator.getMoLocusTag().equals(locusTag)) return true;
		}
		return false;
	}

	public boolean hasRegulator(int  moId) {
		for(PropagatedRegulator regulator: propagatedRegulators)
		{
			if(regulator.getMoLocusId() == moId) return true;
		}
		return false;
	}

	public List<PropagatedRegulator> getNewRegulators(List<Regulator> regulators) {
		List<PropagatedRegulator> newRegulators = new ArrayList<PropagatedRegulator>();
		
		for(PropagatedRegulator pgpRegulator: propagatedRegulators)
		{
			boolean hasRegualtor = false;
			for(Regulator regualtor: regulators)
			{
				if(regualtor.getMoId() == null) continue;
				if(pgpRegulator.getMoLocusId().intValue() == regualtor.getMoId().intValue())
				{
					hasRegualtor = true;
					break;
				}
			}
			if(!hasRegualtor) {
				newRegulators.add(pgpRegulator);
			}
		}
		return newRegulators;
	}

	public boolean hasOperon(Integer firstGeneMOId) {
		for(PropagatedGene pgpGene: propagatedGenes)
		{
			if(pgpGene.getMoLocusId().intValue() == firstGeneMOId.intValue())
			{
				return true;
			}
		}
		return false;
	}

	public PropagatedGene getPropagatedGeneByMOLocusId(Integer firstGeneMOId) {
		for(PropagatedGene pgpGene: propagatedGenes)
		{
			if(pgpGene.getMoLocusId().intValue() == firstGeneMOId.intValue())
			{
				return pgpGene;
			}
		}
		return null;
	}

	public void setPropagatedSites(List<PropagatedSite> propagatedSites) {
		this.propagatedSites = propagatedSites;
	}

	public List<PropagatedSite> getPropagatedSites() {
		return propagatedSites;
	}

	public void setPropagatedCollections(List<PropagatedCollection> propagatedCollections) {
		this.propagatedCollections = propagatedCollections;
	}

	public List<PropagatedCollection> getPropagatedCollections() {
		return propagatedCollections;
	}
	
	public PropagatedCollection getPropagatedCollection() {
		return propagatedCollections.get(0);
	}
	
	public List<PropagatedGene> getPublicGenes()
	{
		List<PropagatedGene> publicGenes = new ArrayList<PropagatedGene>();
		for(PropagatedGene gene: propagatedGenes)
		{
			if(gene.getIsPublic() != null && gene.getIsPublic().booleanValue())
			{
				publicGenes.add(gene);
			}
		}
		return publicGenes;
	}	

}
