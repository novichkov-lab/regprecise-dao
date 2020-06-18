package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
@Table(name = "Regulon")
public class Regulon extends IdObject implements Comparable<Regulon>{
	@Id
	@GeneratedValue
	@Column(name = "regulonId")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulogId")		
	Regulog regulog;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "genomeId")		
	Genome genome;	
	private String kbaseId;
	
	Integer orderIndex;
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE, CascadeType.DELETE_ORPHAN})	
	@JoinColumns({
		@JoinColumn(name = "regulonId")
	})		
	List<Regulator> regulators = new ArrayList<Regulator>();	
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "regulonId")
	})		
	List<Operon> operons = new ArrayList<Operon>();
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "regulonId")
	})	
	List<Locus> loci = new ArrayList<Locus>();

	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "regulonId")
	})
	List<Gene> genes = new ArrayList<Gene>();
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "regulonId")
	})
	List<Site> sites = new ArrayList<Site>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulome2Regulon",
			joinColumns={@JoinColumn(name="regulonId")},
			inverseJoinColumns={@JoinColumn(name="regulomeId")}
	)
	private List<Regulome> regulomes = new ArrayList<Regulome>(); 		
	
	
	public static void sortByRegulatorName(List<Regulon> regulons)
	{
		Collections.sort(regulons, new Comparator<Regulon>() {
			public int compare(Regulon o1, Regulon o2) {
				
				String s1 = o1.getRegulog().getRegulatorName();
				String s2 = o2.getRegulog().getRegulatorName();
				return s1.compareTo(s2);
			}
		});
	}
	
	public static void sortByRegulationTypeRegulatorName(List<Regulon> regulons)
	{
		Collections.sort(regulons, new Comparator<Regulon>() {
			public int compare(Regulon o1, Regulon o2) {
				
				String s1 = "" + o1.getRegulog().getRegulationTypeTermId() + " - "  + o1.getRegulog().getRegulatorName();
				String s2 = "" + o2.getRegulog().getRegulationTypeTermId() + " - "  + o2.getRegulog().getRegulatorName();
				return s1.compareTo(s2);
			}
		});
	}

	
	public static void sortByRegulatedOperonsCount(List<Regulon> regulons)
	{
		Collections.sort(regulons, new Comparator<Regulon>() {
			public int compare(Regulon o1, Regulon o2) {
				
				Integer n1 = o1.getRegulatedOperons().size();
				Integer n2 = o2.getRegulatedOperons().size();
				return n2.compareTo(n1);
			}
		});
	}
	
	
	
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

	public Genome getGenome() {
		return genome;
	}

	public void setGenome(Genome genome) {
		this.genome = genome;
	}

	public List<Operon> getOperons() {
		return operons;
	}

	public void setOperons(List<Operon> operons) {
		this.operons = operons;
	}

	public List<Locus> getLoci() {
		return loci;
	}

	public void setLoci(List<Locus> loci) {
		this.loci = loci;
	}

	public List<Regulator> getRegulators() {
		return regulators;
	}

	public void setRegulators(List<Regulator> regulators) {
		this.regulators = regulators;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public List<Site> getSites() {
		return sites;
	}
	
	
	public Map<String,Gene> getKbaseId2RegualtedGenesMap()
	{
		Map<String,Gene> map = new Hashtable<String, Gene>();
		for(Operon operon: operons)
		{
			if(operon.hasSites())
			{
				for(Gene gene: operon.getGenes())
				{
					map.put(gene.getKbaseId(), gene);
				}
			}
		}		
		return map;
	}
	
	public List<Gene> getRegulatedGenes()
	{
		List<Gene> siteGenes = new ArrayList<Gene>();
		for(Operon operon: operons)
		{
			if(operon.hasSites())
			{
				siteGenes.addAll(operon.getGenes());
			}
		}
		return siteGenes;
	}
	
	public List<Operon> getRegulatedOperons()
	{
		List<Operon> siteOperons = new ArrayList<Operon>();
		for(Operon operon: operons)
		{
			if(operon.hasSites())
			{
				siteOperons.add(operon);
			}
		}
		return siteOperons;
	}

	public int compareTo(Regulon o) {		
		return genome.getName().compareTo(o.getGenome().getName());
	}
	
	public String getGenomeName(){
		return genome.getName();
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public List<Gene> getGenes() {
		return genes;
	}
	
	public Gene getOrthologousGene(Ortholog ortholog)
	{
		for(Gene gene: genes)
		{
			if(gene.getOrtholog().equals(ortholog))
			{
				return gene;
			}
		}
		return null;
	}
	
	public Gene getOrtholohousGene(int orthologId)
	{
		for(Gene gene: genes)
		{
			if(gene.getOrtholog().getId() == orthologId)
			{
				return gene;
			}
		}
		return null;
	}

	public boolean hasSites() {
		return getSites().size() > 0;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}
	
	public String getRegulatedOperonsFirstGeneMOIds()
	{
		StringBuffer sb = new StringBuffer();
		for(Operon operon: getRegulatedOperons())
		{
			if(sb.length() > 0 ){
				sb.append(",");
			}
			sb.append(operon.getFirstGeneMOId());
		}
		return sb.toString();
	}
	
	public String getRegulatedGeneMOIds()
	{
		StringBuffer sb = new StringBuffer();
		for(Gene gene: getRegulatedGenes())
		{
			if(sb.length() > 0 ){
				sb.append(",");
			}
			sb.append(gene.getMoId() );
		}
		return sb.toString();
	}
	
	public String getSitesPlainSequenceFasta()
	{
		StringBuffer sb = new StringBuffer();
		
		Hashtable<Integer,String> siteId2SequenceHash = new Hashtable<Integer,String>();
		for (Site site : getSites()) {
			siteId2SequenceHash.put(site.getId(), site.getSequence().trim());
		}
		Site.convertToPlainSequences(siteId2SequenceHash);

		for (Site site : getSites()) {
			String sequence = siteId2SequenceHash.get(site.getId());
			sb.append(">");
			sb.append(site.getFastaHeader());			
			sb.append("\n");
			sb.append(sequence);
			sb.append("\n");
		}
		return sb.toString();
	}

	public String exportGenes() {
		StringBuffer sb = new StringBuffer();
				
		for(Operon operon: operons)
		{
			if(operon.hasSites())
			{
				sb.append(operon.exportGenes());
				sb.append("\n");
			}
		}
		sb.append("\n");

		return sb.toString();
	}

	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}

	public String getKbaseId() {
		return kbaseId;
	}

	public void setRegulomes(List<Regulome> regulomes) {
		this.regulomes = regulomes;
	}

	public List<Regulome> getRegulomes() {
		return regulomes;
	}
	
	public void populateRegulatedGeneMoIds(HashSet<Integer> moIds, boolean onlyFirstGeneInOperon)
	{
		for(Operon operon: operons)
		{
			if(operon.hasSites())
			{
				
				if(onlyFirstGeneInOperon)
				{
					moIds.add(operon.getFirstGeneMOId());
				}
				else{
					operon.populateGeneMoIds(moIds);
				}
			}
		}
	}
	
}
