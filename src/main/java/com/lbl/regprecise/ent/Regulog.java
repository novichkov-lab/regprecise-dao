package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Regulog")
public class Regulog extends IdObject{
	@Id
	@GeneratedValue
	@Column(name = "regulogId")
	private Integer id;
	
	private String kbaseId;	
	private String taxonName;
	private String phylum;
	
	private String regulatorName;
	private String regulatorModeOfAction;
	private Integer statusTermId;
	private Integer regulationTypeTermId;
		
	private Date createDate;
	
	@Column(columnDefinition="Text")
	private String genomeTree;	
	
	@Column(columnDefinition="Text")
	String notes;	
	
		
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="User2Regulog",
			joinColumns={@JoinColumn(name="regulogId")},
			inverseJoinColumns={@JoinColumn(name="userId")}
	)
	private List<User> users = new ArrayList<User>();
	
	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "profileId")
	private Profile profile;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "riboswitchId")
	private Riboswitch riboswitch;
		
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "regulogId")
	})	
	@OrderBy("orderIndex")
	private List<Regulon> regulons = new ArrayList<Regulon>();
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "regulogId")
	})		
	private List<Cron> crons = new ArrayList<Cron>();

	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Collection2Regulog",
			joinColumns={@JoinColumn(name="regulogId")},
			inverseJoinColumns={@JoinColumn(name="collectionId")}
	)
	private List<Collection> collections = new ArrayList<Collection>(); 		
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulog2ExperimentalValidation",
			joinColumns={@JoinColumn(name="regulogId")},
			inverseJoinColumns={@JoinColumn(name="expValidationId")}
	)
	private List<ExperimentalValidation> experimentalValidations = new ArrayList<ExperimentalValidation>(); 		

	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulog2Effector",
			joinColumns={@JoinColumn(name="regulogId")},
			inverseJoinColumns={@JoinColumn(name="effectorId")}
	)
	private List<Effector> effectors = new ArrayList<Effector>(); 	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulog2Pathway",
			joinColumns={@JoinColumn(name="regulogId")},
			inverseJoinColumns={@JoinColumn(name="pathwayId")}
	)
	private List<Pathway> pathways = new ArrayList<Pathway>(); 	
			
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "tfFamilyId")
	private TFFamily tfFamily;		
	
	
	public static void sortByRegnameTaxonomy(List<Regulog> regulogs)
	{
		Collections.sort(regulogs, new Comparator<Regulog>(){

			public int compare(Regulog o1, Regulog o2) {
				String s1 = o1.getRegulatorName() + " - " + o1.getTaxonName();
				String s2 = o2.getRegulatorName() + " - " + o2.getTaxonName();
				return s1.compareTo(s2);
			}
		});
	}
	
	
	public static List<Regulog> sortByRegtypeFamilyRegnameTaxonomy(List<Regulog> regulogs)
	{
		Collections.sort(regulogs, new Comparator<Regulog>(){

			public int compare(Regulog o1, Regulog o2) {
				String s1 = "" + o1.getRegulationTypeTermId() + " - " + o1.getRegulatorFamily() + " - " + o1.getRegulatorName()+ " - " + o1.getTaxonName();
				String s2 = "" + o2.getRegulationTypeTermId() + " - " + o2.getRegulatorFamily() + " - " + o2.getRegulatorName()+ " - " + o2.getTaxonName();
				return s1.compareTo(s2);
			}
		});
		return regulogs;
	}

	
	
/*	
	public static void sortByTaxonomyRegnameName(List<Project> prjs)
	{
		Collections.sort(prjs, new Comparator<Project>(){

			public int compare(Project o1, Project o2) {
				String s1 = o1.getTaxonomy() + " - " + o1.getProjectRegulator().getName() + " - " + o1.getName();
				String s2 = o2.getTaxonomy() + " - " + o2.getProjectRegulator().getName() + " - " + o2.getName();
				return s1.compareTo(s2);
			}
		});
	}	
*/	
	
	

	public static void sortByRegnameNameTaxonomyRegulogName(List<Regulog> regulogs)
	{
		Collections.sort(regulogs, new Comparator<Regulog>(){

			public int compare(Regulog o1, Regulog o2) {
				String regName1 = o1.getRegulatorName();
				String prefix1 = isGoodRegulatorName(regName1) ? "x" : "y";					
				String s1 = prefix1 + " - " + o1.getRegulatorName() + " - " + o1.getPhylum() + " - " + o1.getName();
				
				
				String regName2 = o2.getRegulatorName();
				String prefix2 = isGoodRegulatorName(regName2) ? "x" : "y";					
				String s2 = prefix2 + " - " + o2.getRegulatorName() + " - " + o2.getPhylum() + " - " + o2.getName();
				return s1.compareTo(s2);
			}
		});
	}		
	
	public static boolean isGoodRegulatorName(String regName)
	{
		for(int i = 0, n = regName.length(); i < n; i++)
		{
			char ch = regName.charAt(i);
			if(ch == '_' || Character.isDigit(ch)) return false;
		}
		return true;
	}
	
	
	
	public String getName()
	{
		return regulatorName + " - " + taxonName;
	}	
	
	public void addRegulon(Regulon regulon)
	{
		regulons.add(regulon);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaxonName() {
		return taxonName != null ? taxonName : "";
	}

	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}

	public String getPhylum() {
		return phylum != null ? phylum : "";
	}

	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}

	public String getRegulatorName() {
		return regulatorName != null? regulatorName : "";
	}

	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}

/*	
	public String getRegulatorFamily() {
		return regulatorFamily != null ? regulatorFamily : "";
	}

	public void setRegulatorFamily(String regulatorFamily) {
		this.regulatorFamily = regulatorFamily;
	}

	public String getEffector() {
		return effector != null? effector : "";
	}

	public void setEffector(String effector) {
		this.effector = effector;
	}
*/	

	public List<Regulon> getRegulons() {
		return regulons;
	}

	public void setRegulons(List<Regulon> regulons) {
		this.regulons = regulons;
	}

	public List<Cron> getCrons() {
		return crons;
	}

	public void setCrons(List<Cron> crons) {
		this.crons = crons;
	}

	public Regulon getRegulon(String genomeName) {
		for(Regulon regulon: regulons)
		{
			if(regulon.getGenome().getName().equals(genomeName))
			{
				return regulon;
			}
		}
		return null;
	}

	public Regulon getRegulon(Genome genome) {
		for(Regulon regulon: regulons)
		{
			if(regulon.getGenome().equals(genome))
			{
				return regulon;
			}
		}
		return null;
	}
	
	public boolean hasSites(Genome genome)
	{
		for(Regulon regulon: regulons)
		{
			if(regulon.getGenome().equals(genome)){
				if(regulon.getSites().size() > 0) return true;
			}
		}
		return false;
	}
	
	public List<Site> getSites()
	{
		List<Site> sites = new ArrayList<Site>();
		for(Regulon regulon: regulons)
		{
			sites.addAll(regulon.getSites());
		}
		return sites;
	}
	

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public List<Collection> getCollections() {
		return collections;
	}

	public void addCron(Cron cron) {
		cron.setRegulog(this);
		crons.add(cron);
		
	}

	public void setRegulatorModeOfAction(String regulatorModeOfAction) {
		this.regulatorModeOfAction = regulatorModeOfAction;
	}

	public String getRegulatorModeOfAction() {
		return regulatorModeOfAction;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setExperimentalValidations(List<ExperimentalValidation> experimentalValidations) {
		this.experimentalValidations = experimentalValidations;
	}

	public List<ExperimentalValidation> getExperimentalValidations() {
		return experimentalValidations;
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
		

	public String getSitesElementsFasta() {
		StringBuffer sb = new StringBuffer();
		int elemIndex = 0;
		for (Site site : getSites()) {
				sb.append(">");
				sb.append(site.getFastaHeader());
				sb.append("|elem|");
				sb.append(elemIndex++);
				sb.append("\n");
				sb.append(site.getSequence());
				sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public int[] getGenomeMoIds() {
		int[] genomeMoIds = new int[regulons.size()];
		
		int index = 0;
		for(Regulon regulon: regulons)
		{
			genomeMoIds[index++] = regulon.getGenome().getMoId();
		}
		
		return genomeMoIds;
	}	

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}		
	
	public String getUserNames()
	{
		StringBuffer sb = new StringBuffer();
		for(User user: users)
		{
			if(sb.length() > 0) sb.append(";");
			sb.append(user.getName());
		}
		return sb.toString();
	}

	public Hashtable<Integer, Regulon> getGenomeMoId2RegulonHash() {
		Hashtable<Integer, Regulon> moId2RegulonHash = new Hashtable<Integer, Regulon>();
		for(Regulon regulon: regulons)
		{
			moId2RegulonHash.put(regulon.getGenome().getMoId(), regulon);
		}
		
		return moId2RegulonHash;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public Object getUser(int userId) {
		for(User user: users)
		{
			if(user.getId() == userId)
			{
				return user;
			}
		}
		return null;
	}

	public boolean canAccess(User user) {
		for(User hUser: users)
		{
			if(user.getId() == hUser.getId())
			{
				return true;
			}
		}
		return false;
	}
	
	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}

	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public void setStatusTermId(Integer statusTermId) {
		this.statusTermId = statusTermId;
	}

	public Integer getStatusTermId() {
		return statusTermId;
	}

	public void setGenomeTree(String genomeTree) {
		this.genomeTree = genomeTree;
	}

	public String getGenomeTree() {
		return genomeTree;
	}

	public void setRiboswitch(Riboswitch riboswitch) {
		this.riboswitch = riboswitch;
	}

	public Riboswitch getRiboswitch() {
		return riboswitch;
	}

	public void setEffectors(List<Effector> effectors) {
		this.effectors = effectors;
	}

	public List<Effector> getEffectors() {
		return effectors;
	}
	

	public void setPathways(List<Pathway> pathways) {
		this.pathways = pathways;
	}

	public List<Pathway> getPathways() {
		return pathways;
	}	

	public void setTfFamily(TFFamily tfFamily) {
		this.tfFamily = tfFamily;
	}

	public TFFamily getTfFamily() {
		return tfFamily;
	}

	public String getEffectorNames() {
		String effectorNames = "";
		for(Effector effector: effectors)
		{
			if(effectorNames.length() > 0) effectorNames += "; ";
			effectorNames += effector.getName();
		}
		
		return effectorNames;
	}

	public String getPathwayNames() {
		String pathwayNames = "";
		for(Pathway pathway: pathways)
		{
			if(pathwayNames.length() > 0) pathwayNames += "; ";
			pathwayNames += pathway.getName();
		}
		
		return pathwayNames;
	}
	
	public String getRegulatorFamily()
	{
		if(regulationTypeTermId == Term.TERM_REGULATION_TYPE_TF)
		{
			return tfFamily != null ? tfFamily.getName() : null;
		}
		else if(regulationTypeTermId == Term.TERM_REGULATION_TYPE_RNA)
		{
			return riboswitch != null ? riboswitch.getRfamId() : null;
		}
		return null;
	}


	public String exportGenes() {
		StringBuffer sb = new StringBuffer();
		for(Regulon regulon: regulons)
		{
			if(regulon.hasSites())
			{
				sb.append("#" + regulon.getGenome().getName());
				sb.append("\n");
				
				sb.append( regulon.exportGenes());
			}
		}
		return sb.toString();
	}


	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}


	public String getKbaseId() {
		return kbaseId;
	}	
	
}
