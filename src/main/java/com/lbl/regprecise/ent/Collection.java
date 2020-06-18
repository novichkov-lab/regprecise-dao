package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "Collection")
public class Collection extends IdObject implements Comparable<Collection>{
	public static final int COLLECTION_ID_PUBLIC_ALL = 0;

	public static final int DEFAULT_COLLECTION_ID = 100;	
	
	@Id
	@GeneratedValue
	@Column(name = "collectionId")	
	private Integer id;
	
	private String name;
	private String longName;
	private String phylum;
	private String taxonName;
	
	@Column(columnDefinition="Text")
	private String description;
	
	private Integer typeTermId;
	private Integer orderIndex;
	private Integer statusTermId;
		
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Collection2Regulog",
			joinColumns={@JoinColumn(name="collectionId")},
			inverseJoinColumns={@JoinColumn(name="regulogId")}
	)	
	private List<Regulog> regulogs = new ArrayList<Regulog>();
	
		
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "collectionId")
	})		
	private List<CollectionUserRole> collectionUserRoles = new ArrayList<CollectionUserRole>();
		
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "collectionId")
	})
	@OrderBy("orderIndex")	
	List<CollectionGenome> collectionGenomes = new ArrayList<CollectionGenome>();	
	
	
	public static String getCollectionTypeName(int termId)
	{
		switch(termId)
		{
			case Term.COLLECTION_TYPE_BY_REGULATOR: return "By trascription factor";
			case Term.COLLECTION_TYPE_BY_TAXONOMY : return "By taxonomy";
			case Term.COLLECTION_TYPE_BY_PATHWAY: return "By metabolic pathway";
			case Term.COLLECTION_TYPE_BY_REGFAM: return "By transcription factor family";		
			case Term.COLLECTION_TYPE_BY_EFFECTOR: return "By effector";		
			case Term.COLLECTION_TYPE_PRIVATE: return "Private";		
		}
		return "";
	}
	
	
	public static int[] getCollectionTypeIds()
	{
		return new int[]{
			Term.COLLECTION_TYPE_BY_TAXONOMY, 	
			Term.COLLECTION_TYPE_BY_REGULATOR, 
			Term.COLLECTION_TYPE_BY_REGFAM,
			Term.COLLECTION_TYPE_BY_PATHWAY,
			Term.COLLECTION_TYPE_BY_EFFECTOR,
			Term.COLLECTION_TYPE_PRIVATE
		};
	}
	
	public static boolean isCanonicalCollection(int collectionTypeTermId)
	{
		return collectionTypeTermId == Term.COLLECTION_TYPE_BY_TAXONOMY 
			|| collectionTypeTermId == Term.COLLECTION_TYPE_BY_REGULATOR 
			|| collectionTypeTermId == Term.COLLECTION_TYPE_PRIVATE;
	}
	
	
	public void addRegulog(Regulog regulog)
	{
		regulogs.add(regulog);
	}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getLongName() {
		return longName;
	}
	public void setTypeTermId(Integer typeTermId) {
		this.typeTermId = typeTermId;
	}
	public Integer getTypeTermId() {
		return typeTermId;
	}
	public void setRegulogs(List<Regulog> regulogs) {
		this.regulogs = regulogs;
	}
	public List<Regulog> getRegulogs() {
		return regulogs;
	}
	
	public int getRegulatorCount(Genome genome, int regulogstatusTermId, int regulationTypeTermId)
	{
		HashSet<String> regulatorNames = new HashSet<String>();
		for(Regulog regulog: regulogs)
		{
			if(regulog.getStatusTermId() != regulogstatusTermId) continue;
			if(regulog.getRegulationTypeTermId() != regulationTypeTermId) continue;
			
			for(Regulon regulon: regulog.getRegulons())
			{
				if(regulon.hasSites() && regulon.getGenome().equals(genome))
				{
					regulatorNames.add(regulog.getRegulatorName());
				}
			}
		}
		return regulatorNames.size();
	}
	
	public int getFamilyCount(Genome genome, int regulogstatusTermId, int regulationTypeTermId)
	{
		HashSet<String> regulatorFamilies = new HashSet<String>();
		for(Regulog regulog: regulogs)
		{
			if(regulog.getStatusTermId() != regulogstatusTermId) continue;
			if(regulog.getRegulationTypeTermId() != regulationTypeTermId) continue;
	
			for(Regulon regulon: regulog.getRegulons())
			{
				if(regulon.hasSites() && regulon.getGenome().equals(genome) )
				{
					regulatorFamilies.add(regulog.getRegulatorFamily());
				}
			}
		}
		return regulatorFamilies.size();
	}
	
	public int getSitesCount(Genome genome, int regulogstatusTermId, int regulationTypeTermId)
	{
		int count = 0;
		for(Regulog regulog: regulogs)
		{
			if(regulog.getStatusTermId() != regulogstatusTermId) continue;
			if(regulog.getRegulationTypeTermId() != regulationTypeTermId) continue;
			
			for(Regulon regulon: regulog.getRegulons())
			{
				if(regulon.getGenome().equals(genome))
				{
					count += regulon.getSites().size();
				}
			}
		}
		return count;		
	}
	public List<Integer> getTaxonomyIds()
	{
		List<Integer> taxonomyIds = new Vector<Integer>();
		for(CollectionGenome cg: collectionGenomes)
		{
			taxonomyIds.add(cg.getGenome().getMoId());
		}

		return taxonomyIds;
	}
	
	public List<Genome> getGenomes()
	{
		List<Genome> genomes = new ArrayList<Genome>();
		for(CollectionGenome cg: collectionGenomes)
		{
			genomes.add(cg.getGenome());
		}
		return  genomes;
	}

	public void setCollectionGenomes(List<CollectionGenome> collectionGenomes) {
		this.collectionGenomes = collectionGenomes;
	}


	public List<CollectionGenome> getCollectionGenomes() {
		return collectionGenomes;
	}
	
	public String getType()
	{
		return Collection.getCollectionTypeName(typeTermId);
	}


	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}


	public Integer getOrderIndex() {
		return orderIndex;
	}


	public void setCollectionUserRoles(List<CollectionUserRole> collectionUserRoles) {
		this.collectionUserRoles = collectionUserRoles;
	}


	public List<CollectionUserRole> getCollectionUserRoles() {
		return collectionUserRoles;
	}
	
	public UserRole getUserRole(int userId)
	{
		for(CollectionUserRole cuRole: collectionUserRoles)
		{
			if(cuRole.getUser().getId() == userId)
			{
				return cuRole.getRole();
			}
		}
		return null;
	}
	
	public List<Regulon> getRegulons(Genome genome)
	{
		List<Regulon> regulons = new Vector<Regulon>();
		for(Regulog regulog: getRegulogs())
		{
			Regulon regulon = regulog.getRegulon(genome);
			if(regulon!= null)
			{
				regulons.add(regulon);
			}
		}		
		return regulons;
	}
		
	public boolean equals(Object obj)
	{
		return obj instanceof Collection 
			   && ((Collection)obj).getId().equals(id); 
	}


	public int compareTo(Collection o) {
		return name.compareTo(o.getName());
	}


	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}


	public String getPhylum() {
		return phylum;
	}


	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}


	public String getTaxonName() {
		return taxonName;
	}


	public void setStatusTermId(Integer statusTermId) {
		this.statusTermId = statusTermId;
	}


	public Integer getStatusTermId() {
		return statusTermId;
	}


	
}
