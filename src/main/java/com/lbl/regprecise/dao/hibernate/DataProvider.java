package com.lbl.regprecise.dao.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.lbl.regprecise.dto.CollectionStatDTO;
import com.lbl.regprecise.dto.GeneRegulonDTO;
import com.lbl.regprecise.dto.GenomeStatDTO;
import com.lbl.regprecise.dto.PropagatedRegulonStatDTO;
import com.lbl.regprecise.dto.RegulogStatDTO;
import com.lbl.regprecise.dto.RegulonStatDTO;
import com.lbl.regprecise.dto.RiboswitchRawStatDTO;
import com.lbl.regprecise.ent.Collection;
import com.lbl.regprecise.ent.CollectionUserRole;
import com.lbl.regprecise.ent.ComputationalValidation;
import com.lbl.regprecise.ent.Cron;
import com.lbl.regprecise.ent.DatabaseRelease;
import com.lbl.regprecise.ent.Effector;
import com.lbl.regprecise.ent.EffectorClass;
import com.lbl.regprecise.ent.ExperimentalValidation;
import com.lbl.regprecise.ent.Gene;
import com.lbl.regprecise.ent.Genome;
import com.lbl.regprecise.ent.Log;
import com.lbl.regprecise.ent.Operon;
import com.lbl.regprecise.ent.Ortholog;
import com.lbl.regprecise.ent.Pathway;
import com.lbl.regprecise.ent.PathwayClass;
import com.lbl.regprecise.ent.ProcessState;
import com.lbl.regprecise.ent.Profile;
import com.lbl.regprecise.ent.Project;
import com.lbl.regprecise.ent.ProjectUserRole;
import com.lbl.regprecise.ent.PropagatedCollection;
import com.lbl.regprecise.ent.PropagatedGene;
import com.lbl.regprecise.ent.PropagatedRegulon;
import com.lbl.regprecise.ent.RegElement;
import com.lbl.regprecise.ent.Regulator;
import com.lbl.regprecise.ent.Regulog;
import com.lbl.regprecise.ent.Regulome;
import com.lbl.regprecise.ent.Regulon;
import com.lbl.regprecise.ent.Riboswitch;
import com.lbl.regprecise.ent.RiboswitchRawSite;
import com.lbl.regprecise.ent.SearchIndex;
import com.lbl.regprecise.ent.Site;
import com.lbl.regprecise.ent.TFFamily;
import com.lbl.regprecise.ent.Taxon;
import com.lbl.regprecise.ent.TaxonGenome;
import com.lbl.regprecise.ent.User;
import com.lbl.regprecise.ent.UserRole;

/**
 * @author Pavel Novichkov
 *
 */
public class DataProvider {

	Session session;	
	 
	public DataProvider()
	{
		session = HibernateUtil.getSession();
	}
	
	
	public void close()
	{
		session.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Integer> getRegulonIds()
	{
		return session.createSQLQuery(" select regulonId from Regulon").list();			
	}
	
	@SuppressWarnings("unchecked")
	public List<Regulon> getRegulons() {
		return session.createCriteria(Regulon.class).list();		
	}
		
	
	
	public void populatePropagatedLists(int prpCollectionId, 
			List<Regulog> regulogs, List<Genome> genomes)
	{
		HashSet<Regulog> regulogSet = new HashSet<Regulog>();
		HashSet<Genome> genomeSet = new HashSet<Genome>();
		for(PropagatedRegulon regulon: getPropagatedRegulons(prpCollectionId))
		{
			regulogSet.add(regulon.getRegulog());
			genomeSet.add(regulon.getGenome());
		}
		
		regulogs.addAll(regulogSet);
		Collections.sort(regulogs, new Comparator<Regulog>() {

			public int compare(Regulog o1, Regulog o2) {
				return o1.getRegulatorName().compareTo(o2.getRegulatorName());
			}
		});
		
		genomes.addAll(genomeSet);
		Collections.sort(genomes, new Comparator<Genome>() {

			public int compare(Genome o1, Genome o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PropagatedRegulon> getPropagatedRegulons(int prpCollectionId)
	{
		return 
			session.createSQLQuery("select  p.* from PropagatedRegulon p where p.propagatedCollectionId = " +  prpCollectionId )
				.addEntity(PropagatedRegulon.class)
				.list(); 
	}
	
	public PropagatedRegulon getPropagatedRegulon(int pgpRegulonId)
	{
		return (PropagatedRegulon) session.load(PropagatedRegulon.class, pgpRegulonId);
	}

	@SuppressWarnings("unchecked")
	public List<Regulon> getRegulons(Genome genome, Collection collection)
	{
		return 
		session.createSQLQuery("select distinct n.* " 
				+ " from Regulon n join Collection2Regulog c using(regulogId)" 
				+ " where n.genomeId = " + genome.getId() 
				+ " and c.collectionId = " + collection.getId())
				.addEntity(Regulon.class)
				.list();		
	}

	@SuppressWarnings("unchecked")
	public List<Regulon> getRegulons(Genome genome)
	{
		return 
		session.createSQLQuery("select distinct n.*" 
				+ " from Regulon n" 
				+ " where n.genomeId = " + genome.getId() )
				.addEntity(Regulon.class)
				.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<SearchIndex> getSearchIndeces(String val)
	{
		return session.createCriteria(SearchIndex.class).
			add( Restrictions.like("objName", val) ).
			addOrder(Order.asc("objType")).			
			addOrder(Order.asc("objName")).			
			addOrder(Order.asc("regulatorName")).			
			addOrder(Order.asc("genomeName")).			
			list(); 		
	}
	
	@SuppressWarnings("unchecked")
	public List<ExperimentalValidation> getExperimentalValidations()
	{
		return session.createCriteria(ExperimentalValidation.class).list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Regulog> getExperimentalyValidatedRegulogs()
	{		
		return 
			session.createSQLQuery("select distinct r.* from Regulog r join Regulog2ExperimentalValidation e using(regulogId)")
				.addEntity(Regulog.class)
				.list();
	}
	
	public void save(Object obj) {
		session.save(obj);
		
	}

	public void addLog(Log log) {
		log = new Log(log);
		log.setId(null);
		session.beginTransaction();
		session.save(log);
		session.getTransaction().commit();		
	}
	
	public void saveCommit(Object obj) {
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();		
	}
	
	public User getUser(Integer userId)
	{
		return (User) session.load(User.class, userId);
	}
	
	public User getUser(String login)
	{
		return (User) session.createCriteria(User.class).
			add( Restrictions.like("login", login) ).uniqueResult(); 		
	}
	
	public UserRole getUserRole(String name)
	{
		return (UserRole) session.createCriteria(UserRole.class).
			add( Restrictions.like("name", name) ).uniqueResult(); 		
	}
	
	@SuppressWarnings("unchecked")
	public List<Regulator> getRegulators() {
		return session.createCriteria(Regulator.class).list();		
	}

	@SuppressWarnings("unchecked")
	public List<Regulog> getRegulogs() {
		return session.createCriteria(Regulog.class).list();		
	}


	
	@SuppressWarnings("unchecked")
	public List<Gene> getGenes() {
		return session.createCriteria(Gene.class).list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<ComputationalValidation> getComputationalValidations() {
		return session.createCriteria(ComputationalValidation.class)
			.addOrder(Order.asc("orderIndex"))
			.list();		
	}	

	@SuppressWarnings("unchecked")
	public List<PropagatedCollection> getPropagatedCollections()
	{
		return session.createCriteria(PropagatedCollection.class).list();			
	}
	
	public PropagatedCollection getPropagatedCollection(int prpCollectionId)
	{
		return (PropagatedCollection) session.load(PropagatedCollection.class, prpCollectionId);		
	}
	
	public Ortholog getOrtholog(int orthologId)
	{
		return (Ortholog) session.load(Ortholog.class, orthologId);		
	}
	
	public Genome getGenome(int genomeId)
	{
		return (Genome) session.load(Genome.class, genomeId);		
	}

	@SuppressWarnings("unchecked")
	public List<Genome> getGenomes()
	{
		return session.createCriteria(Genome.class).list();				
	}
	
	@SuppressWarnings("unchecked")
	public Genome getGenome(String name)
	{
		List<Genome> genomes = session.createCriteria(Genome.class).
			add( Restrictions.like("name", name) ).list();
				
		return genomes.size() > 0 ? genomes.get(0) : null; 			
	}
	
	@SuppressWarnings("unchecked")
	public Genome getGenomeByMOId(Integer moGenomeId)
	{
		List<Genome> genomes =  session.createCriteria(Genome.class).
			add( Restrictions.like("moId", moGenomeId) ).list();
		return genomes.size() > 0 ? genomes.get(0) : null;
	}

	
	public Regulon getRegulon(int regulonId)
	{
		return (Regulon) session.load(Regulon.class, regulonId);		
	}
		
	public Regulog getRegulog(int regulogId)
	{
		return (Regulog) session.load(Regulog.class, regulogId);		
	}
	
	public Collection getCollection(int collectionId)
	{
		return (Collection) session.load(Collection.class, collectionId);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Collection> getCollections(int typeTermId)
	{
		return session.createCriteria(Collection.class)
			.add(Restrictions.eq("typeTermId", typeTermId))
			.list();			
	}

	@SuppressWarnings("unchecked")
	public List<Collection> getCollections()
	{
		return session.createCriteria(Collection.class)
			.list();			
	}
	
	
	public Riboswitch getRiboswitch(String name)
	{
		return (Riboswitch) session.createCriteria(Riboswitch.class)
			.add(Restrictions.eq("name", name))
			.uniqueResult();			
	}	
	
	@SuppressWarnings("unchecked")
	public List<CollectionUserRole> getCollectionUserRoles(User user)
	{		
		return session.createCriteria(CollectionUserRole.class)
			.add(Restrictions.eq("user.id", user.getId()))
			.list();			
	}

	@SuppressWarnings("unchecked")
	public List<ProjectUserRole> getProjectUserRoles(User user)
	{
		return session.createCriteria(ProjectUserRole.class)
		.add(Restrictions.eq("user.id", user.getId()))
		.list();			
	}
	
	
	public List<Collection> getCollections(User user)
	{
		List<Collection> collections = new ArrayList<Collection>();
		for(int collectionTypeId: Collection.getCollectionTypeIds())
		{
			collections.addAll(getCollections(user, collectionTypeId));
		}
		return collections;
	}
	
	public List<Collection> getCollections(User user, int typeTermId)
	{
		if(user.getRole().isAdministrator() || user.getRole().isRegPreciseCurator())
		{
			return getCollections(typeTermId);
		}
		
		
		List<Collection> collections = new ArrayList<Collection>();  		
		for(CollectionUserRole collectionUserRole: user.getCollections())
		{
			Collection collection = collectionUserRole.getCollection();
			if(collection.getTypeTermId() == typeTermId)
			{
				collections.add(collection);
			}	
		}
		
		return collections;
	}
	
	
	
	
	public Hashtable<Integer,Hashtable<Integer,PropagatedRegulonStatDTO>> getGenomeId2RegulogId2PropagatedRegulonStatDTOHash(int pgpCollectionId)
	{
		Hashtable<Integer,Hashtable<Integer,PropagatedRegulonStatDTO>> hash 
			= new Hashtable<Integer, Hashtable<Integer,PropagatedRegulonStatDTO>>();
		
		for(PropagatedRegulonStatDTO regulonStat: getPropagatedRegulonStatDTO(pgpCollectionId))
		{
			Integer genomeId = regulonStat.getGenomeId();
			Integer regulogId = regulonStat.getRegulogId();
			
			Hashtable<Integer,PropagatedRegulonStatDTO> subHash = hash.get(genomeId);
			if(subHash == null)
			{
				subHash = new Hashtable<Integer,PropagatedRegulonStatDTO>();
				hash.put(genomeId, subHash);
			}
			subHash.put(regulogId, regulonStat);
		}
		return hash;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PropagatedRegulonStatDTO> getPropagatedRegulonStatDTO(int pgpCollectionId)
	{
		return 
		session.createSQLQuery(
				"select  r.propagatedRegulonId, r.regulogId, r.genomeId,"
				+ " (	select count(distinct propagatedGeneId)" 
				+ " from PropagatedSite s where s.propagatedRegulonId = r.propagatedRegulonId "
				+ " ) as geneCount, " 
				+ " (	select count(*)" 
				+ " from PropagatedRegulator t where t.propagatedRegulonId = r.propagatedRegulonId "
				+ " ) as regulatorCount "
				+ " from PropagatedCollection c "
				+ " join PropagatedCollection2Regulon using(propagatedCollectionId) "
				+ " join PropagatedRegulon r using(propagatedRegulonId) "
				+ " where  c.propagatedCollectionId = " + pgpCollectionId
				)				
		.setResultTransformer(Transformers.aliasToBean(PropagatedRegulonStatDTO.class))
		.list();				
	}
	

	
	@SuppressWarnings("unchecked")
	public List<CollectionStatDTO> getCollectionsStatDTO(int typeTermId)
	{
		return 
			session.createSQLQuery(
					
					"select " 
					+ " c.collectionId as collectionId, c.name as collectionName, c.typeTermId as collectionTypeTermId "

					+ " ,(select count(distinct genomeId) from Collection2Regulog join Regulon using (regulogId) where collectionId = c.collectionId ) as genomeCount "
					+ " ,(select count(*) from Collection2Regulog  join Regulog  using (regulogId) where collectionId = c.collectionId) as regulogCount "
					+ " ,(select count(distinct regulatorName) from Collection2Regulog  join Regulog  using (regulogId) where collectionId = c.collectionId) as regulatorCount "
					+ " ,(select count(distinct regulatorFamily) from Collection2Regulog  join Regulog  using (regulogId) where collectionId = c.collectionId) as familyCount "
					+ " ,(select count(*)  from Collection2Regulog join Regulon using (regulogId) join Site s using (regulonId) where collectionId = c.collectionId ) as siteCount "
					+ " from Collection c  "
					+ " where c.typeTermId =  " + typeTermId
					+ " order by c.orderIndex")				
			.setResultTransformer(Transformers.aliasToBean(CollectionStatDTO.class))
			.list();		
	}	
	

	public CollectionStatDTO getSummaryStatDTO()
	{
		CollectionStatDTO stat = (CollectionStatDTO)
			session.createSQLQuery(
					"select " 
					+ " (select count(distinct genomeId) from Regulog rg join Regulon using (regulogId) ) as genomeCount "
					+ " ,(select count(*) from Regulog rg  ) as regulogCount "
					+ " ,(select count(distinct regulatorName) from Regulog rg  ) as regulatorCount "
					+ " ,(select count(distinct regulatorFamily) from Regulog rg  ) as familyCount "
					+ " ,(select count(*)  from Regulog rg join Regulon using (regulogId) join Site s using (regulonId) ) as siteCount "
					
					)
			.setResultTransformer(Transformers.aliasToBean(CollectionStatDTO.class))
			.uniqueResult();
		
		stat.setCollectionId(-1);
		stat.setCollectionName("General stat");
		stat.setCollectionTypeTermId(-1);
		
		return stat;
	}	
	
	
	public CollectionStatDTO getCollectionStatDTO(int collectionId)
	{
		return (CollectionStatDTO)
			session.createSQLQuery(
					"select " 
					+ " c.collectionId as collectionId, c.name as collectionName, c.typeTermId as collectionTypeTermId "

					+ " ,(select count(distinct genomeId) from Collection2Regulog join Regulon using (regulogId) where collectionId = c.collectionId ) as genomeCount "
					+ " ,(select count(*) from Collection2Regulog  join Regulog  using (regulogId) where collectionId = c.collectionId) as regulogCount "
					+ " ,(select count(distinct regulatorName) from Collection2Regulog  join Regulog  using (regulogId) where collectionId = c.collectionId) as regulatorCount "
					+ " ,(select count(distinct regulatorFamily) from Collection2Regulog  join Regulog  using (regulogId) where collectionId = c.collectionId) as familyCount "
					+ " ,(select count(*)  from Collection2Regulog join Regulon using (regulogId) join Site s using (regulonId) where collectionId = c.collectionId ) as siteCount "
					+ " from Collection c  "

					+ " where c.collectionId = " + collectionId)
			.setResultTransformer(Transformers.aliasToBean(CollectionStatDTO.class))
			.uniqueResult();		
	}	
	
	@SuppressWarnings("unchecked")
	public List<GenomeStatDTO> getGenomeStatsDTO()
	{ 
		return 
			session.createSQLQuery(
					" select g.genomeId as genomeId, g.name as genomeName, "  
					+ " (select count(*) from Regulon n join Regulog rg using(regulogId) where n.genomeId = g.genomeId and rg.isPublic = 1 )  as regulonCount,  "
					+ " (select count(*) from Site s join Regulon n using(regulonId) join Regulog rg using(regulogId) where n.genomeId = g.genomeId and rg.isPublic = 1)  as siteCount  "

					+ " from  " 
					+ " Genome g  "
					+ " order by g.name")
			.setResultTransformer(Transformers.aliasToBean(GenomeStatDTO.class))
			.list();		
	}
	
	
	public Hashtable<Integer, Hashtable<Integer, RegulonStatDTO>> getRegulonStatsDTOHash(int collectionId)
	{
		Hashtable<Integer, Hashtable<Integer, RegulonStatDTO>> regulog2genome2regulonStatHash = new Hashtable<Integer, Hashtable<Integer, RegulonStatDTO>>();
		for(RegulonStatDTO rs : getRegulonStatsDTO(collectionId))
		{
			Hashtable<Integer, RegulonStatDTO> genome2regulonStatHash = regulog2genome2regulonStatHash.get(rs.getRegulogId());
			if(genome2regulonStatHash == null)
			{
				genome2regulonStatHash = new Hashtable<Integer, RegulonStatDTO>();
				regulog2genome2regulonStatHash.put(rs.getRegulogId(), genome2regulonStatHash);
			}
			genome2regulonStatHash.put(rs.getGenomeId(), rs);
			
		}
		return regulog2genome2regulonStatHash;
	}
	
	@SuppressWarnings("unchecked")
	public List<RegulonStatDTO> getRegulonStatsDTO(int collectionId)
	{
		return 
		session.createSQLQuery(								
				" select "
				+ " r.regulonId as regulonId,"
				+ " r.regulogId as regulogId," 
				+ " r.genomeId as genomeId," 
				+ " (select count(distinct g.operonId) from Gene g join Site s using(geneId) where g.regulonId = r.regulonId) as operonsWithSiteCount" 
				+ " from Regulon r join  Collection2Regulog c using(regulogId) "
				+ " where c.collectionId = " + collectionId)
			.setResultTransformer(Transformers.aliasToBean(RegulonStatDTO.class))
			.list();		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RegulogStatDTO> getRegulogStatsDTO()
	{
		return 
			session.createSQLQuery(
					" select "
					+ " r.regulogId as regulogId," 
					+ " r.regulatorName as regulatorName," 
					+ " r.taxonName as taxonName, "
					+ " r.phylum as phylum, "
					+ " r.function as function," 
					+ " r.functionClass as pathway," 
					+ " r.effector as effector, "
					+ " r.regulatorFamily as regulatorFamily," 
					+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount," 
					+ " (select count(*) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as siteCount" 
					+ " from Regulog r ") 
				.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
				.list();		
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<GeneRegulonDTO> getGeneRegulonDTO(int taxonomyId, List<String> locusTags)
	{
		return 
		session.createSQLQuery(
				"select" 
				+ " gn.geneId as geneId ," 
				+ " gn.locusTag as locusTag," 
				+ " gn.regulonId as regulonId,"
				+ " rg.regulatorName as regulatorName,"
				+ " rg.taxonName as taxonName, "
				+ " rg.phylum as phylum, "
				+ " rg.function as function,"
				+ " rg.functionClass as pathway," 
				+ " rg.effector as effector, "
				+ " rg.regulatorFamily as regulatorFamily" 


				+ " from SearchIndex s " 
				+ " join Gene gn on s.objId = gn.operonId " 
				+ " join Regulon rn on rn.regulonId = s.regulonId " 
				+ " join Genome gm using(genomeId) " 
				+ " join Regulog rg using(regulogId)"
				
				+ " where s.objType = 3 " 
				+ " and gm.moId = " + taxonomyId
				+ " and gn.locusTag in(" + HibernateUtil.toCommaSeparatedString(locusTags) + ")"		
			) 
			.setResultTransformer(Transformers.aliasToBean(GeneRegulonDTO.class))
			.list();				
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<RegulogStatDTO> getRegulogStatsDTO(int collectionId)
	{
		return 
			session.createSQLQuery(
					" select "
					+ " r.regulogId as regulogId," 
					+ " r.regulatorName as regulatorName," 
					+ " r.taxonName as taxonName, "
					+ " r.phylum as phylum, "
					+ " r.function as function," 
					+ " r.functionClass as pathway," 
					+ " r.effector as effector, "
					+ " r.regulatorFamily as regulatorFamily," 
					+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount," 
					+ " (select count(*) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as siteCount" 
					+ " from Regulog r  join Collection2Regulog c using(regulogId) "
					+ " where c.collectionId = " + collectionId)
				.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
				.list();		
	}
	

	public Session getSession() {
		return session;
	}
	
	public void beginTransaction()
	{
		session.beginTransaction();		
	}
	
	public void saveOrUpdate(Object obj)
	{
		session.saveOrUpdate(obj);
	}
	
	public void commit()
	{
		session.getTransaction().commit();
	}




	public void delete(Object obj) {
		session.delete(obj);		
	}
	
	public void clearLog(int jobTermId, int errorTermId)
	{
		session.createSQLQuery("delete from Log where jobTermId = " + jobTermId 
				+ " and errorTermId = " + errorTermId).executeUpdate();		
		
	}
	public void clearLog(int jobTermId)
	{
		session.createSQLQuery("delete from Log where jobTermId = " + jobTermId ).executeUpdate();		
		
	}

	@SuppressWarnings("unchecked")
	public List<PropagatedGene> getSupportingOrthologs(PropagatedGene pgGene) {
		
		return 
		session.createSQLQuery(
				"select distinct g1.*" 
				+" from PropagatedGene g1"  
				+" join PropagatedGene g2"  
				+" join PropagatedRegulon r1 on r1.propagatedRegulonId = g1. propagatedRegulonId"
				+" join PropagatedRegulon r2 on r2.propagatedRegulonId = g2. propagatedRegulonId"
				+" where"
				+" r1.regulogId = r2.regulogId and r1.typeTermId = r2.typeTermId"
				+" and g1.orthologRef = g2.orthologRef"
				+" and g1.propagatedGeneId != g2.propagatedGeneId"
				+" and g2.propagatedGeneId = " + pgGene.getId())
				.addEntity(PropagatedGene.class)
				.list();	
		}


	public Cron getCron(int cronId) {
		return (Cron) session.load(Cron.class, cronId);		
	}


	public void flush() {
		session.flush();
		
	}


	public Gene getGene(int geneId) {
		return (Gene) session.load(Gene.class, geneId);		
	}


	public Operon getOperon(int operonId) {
		return (Operon) session.load(Operon.class, operonId);		
	}


	public Site getSite(int siteId) {
		return (Site) session.load(Site.class, siteId);		
	}


	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return session.createCriteria(User.class).list();
	}


	@SuppressWarnings("unchecked")
	public List<RiboswitchRawStatDTO> getRiboswitchStatUIByMOGneomeIds(int[] moGenomeIds) {
		String genomeIdsString = HibernateUtil.toCommaSeparatedString(moGenomeIds);
		
		String sql = 
			"select r.riboswitchId as riboswitchId, " 
			+ " r.name as name, " 
			+ " r.rfamId as rfamId, " 
			+ " count(*) as siteCount, " 
			+ " min(s.score) as minScore "
			+ " from RiboswitchRawSite s join Riboswitch r using(riboswitchId) join Genome g using(genomeId)"
			+ " where g.moId in (" + genomeIdsString + ")"
			+ " group by r.name, r.rfamId "
			+ " order by r.name ";		
		
		return 
		session.createSQLQuery( sql	)
			.setResultTransformer(Transformers.aliasToBean(RiboswitchRawStatDTO.class))
			.list();		
	}




	@SuppressWarnings("unchecked")
	public List<RiboswitchRawSite> getRiboswitchRawSites(int riboswitchId,
			int[] moGenomeIds) {

		String genomeIdsString = HibernateUtil.toCommaSeparatedString(moGenomeIds);
		
		String sql = 
			"select distinct s.* "
			+ " from RiboswitchRawSite s join Riboswitch r using(riboswitchId) join Genome g using(genomeId)"
			+ " where g.moId in (" + genomeIdsString + ")"
			+ " and s.riboswitchId = " + riboswitchId;
			
		return 
			session.createSQLQuery(sql)
				.addEntity(RiboswitchRawSite.class)
				.list();		
	}


	public Project getProject(int projectId) {
		return (Project) session.load(Project.class, projectId);		
	}


	public RegElement getRegElement(int regElementId) {
		return (RegElement) session.load(RegElement.class, regElementId);		
	}


	@SuppressWarnings("unchecked")
	public List<Project> getProjects() {
		return session.createCriteria(Project.class).list();
	}


	public Profile getProfile(Integer profileId) {
		return (Profile) session.load(Profile.class, profileId);		
	}


	@SuppressWarnings("unchecked")
	public List<Taxon> getTaxons() {
		return session.createCriteria(Taxon.class).list();
	}

	public Taxon getTaxon(int taxonId) {
		return (Taxon) session.load(Taxon.class, taxonId);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Taxon> getReferenceTaxonCandidates(int genomeId)
	{
		String sql =  " select t.* "
			+ "from Taxon t  join TaxonGenome g using(taxonId) "
			+ "where t.isReference = 1 and g.genomeId = " + genomeId;
			
		return (List<Taxon>) session.createSQLQuery(sql).addEntity(Taxon.class).list();			
	}
	
	public Taxon getReferenceTaxon(int genomeId)
	{
		List<Taxon> taxons =  getReferenceTaxonCandidates(genomeId);			
		return taxons.size() > 0 ? taxons.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<TaxonGenome> getReferenceTaxonGenomes() {
		String sql =  " select tg.*" +
				" from TaxonGenome tg " +
				" join Taxon t using(taxonId)" +
				" where t.isReference = 1";
		
		return (List<TaxonGenome>) session.createSQLQuery(sql).addEntity(TaxonGenome.class).list();		
	}

	@SuppressWarnings("unchecked")
	public List<DatabaseRelease> getDatabaseReleases(){
		return session.createCriteria(DatabaseRelease.class)
			.addOrder(Order.asc("majorVersion"))
			.addOrder(Order.asc("mionrVersion"))
			.list();			
	};
	
	public DatabaseRelease getCurrentDatabaseRelease()
	{
		return (DatabaseRelease) session.createCriteria(DatabaseRelease.class).
		add( Restrictions.eq("isCurrent", true) ).uniqueResult(); 				
	}


	@SuppressWarnings("unchecked")
	public List<Effector> getEffectors(int classId) {
		return session.createSQLQuery("select * from Effector e" +
				" where e.effectorClassId = " +	classId)
				.addEntity(Effector.class)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Effector> getEffectors() {
		return session.createCriteria(Effector.class).list();			
	}
	
	@SuppressWarnings("unchecked")
	public List<Pathway> getPathways() {
		return session.createCriteria(Pathway.class).list();			
	}	
	
	@SuppressWarnings("unchecked")
	public List<EffectorClass> getEffectorClasses() {
		return session.createCriteria(EffectorClass.class).list();			
	}
	
	public EffectorClass getEffectorClass(int effectorClassId) {		
		return (EffectorClass) session.load(EffectorClass.class, effectorClassId);
	}
	
	@SuppressWarnings("unchecked")
	public List<PathwayClass> getPathwayClasses() {
		return session.createCriteria(PathwayClass.class).list();
	}
	
	public Effector getEffector(int effectorId) {		
		return (Effector) session.load(Effector.class, effectorId);
	}

	@SuppressWarnings("unchecked")
	public List<Regulome> getRegulomes() {
		return session.createCriteria(Regulome.class).list();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Regulog> getEffectorRegulogs(Integer effectorId)
	{
		String sql =  " select distinct r.* "
			+ "from Regulog r join Regulog2Effector r2e using(regulogId) "
			+ "where r2e.effectorId = " + effectorId;			
		return (List<Regulog>)session.createSQLQuery(sql).addEntity(Regulog.class).list();			
	}
	
	public PathwayClass getPathwayClass(int pathwayClassId) {		
		return (PathwayClass) session.load(PathwayClass.class, pathwayClassId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pathway> getPathways(int classId) {
		return session.createSQLQuery("select * from Pathway p" +
				" where p.pathwayClassId = " + classId)
				.addEntity(Pathway.class)
				.list();		
	}
	
	public Pathway getPathway(int pathwayId) {		
		return (Pathway) session.load(Pathway.class, pathwayId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Regulog> getPathwayRegulogs(Integer pathwayId)
	{
		String sql =  " select distinct r.* "
			+ "from Regulog r join Regulog2Pathway r2p using(regulogId) "
			+ "where r2p.pathwayId = " + pathwayId;			
		return (List<Regulog>)session.createSQLQuery(sql).addEntity(Regulog.class).list();			
	}	

	@SuppressWarnings("unchecked")
	public List<TFFamily> getTfFamilies() {		
		return session.createCriteria(TFFamily.class).list();
	}
	
	public TFFamily getTFFamily(int tfFamilyId) {		
		return (TFFamily) session.load(TFFamily.class, tfFamilyId);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Riboswitch> getRnaFamilies() {		
		return session.createCriteria(Riboswitch.class).list();
	}	
	
	public Riboswitch getRiboswitch(int riboswitchId) {		
		return (Riboswitch) session.load(Riboswitch.class, riboswitchId);
	}	
	
	public ProcessState getProcessState(Integer processId) {
		return (ProcessState) session.get(ProcessState.class, processId);
	}
	
	@SuppressWarnings("unchecked")
	public Genome getGenomeByKbaseId(String genomeKBaseId) {
		List<Genome> genomes = 
			session.createCriteria(Genome.class).
			add( Restrictions.like("kbaseId",  genomeKBaseId) ).list();
		
		return genomes.size() > 0 ? genomes.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	public Regulome getRegulomeByKBaseId(String regulomeKBaselId) {
		List<Regulome> regulomes = 
			session.createCriteria(Regulome.class).
			add( Restrictions.like("kbaseId",  regulomeKBaselId) ).list();
		
		return regulomes.size() > 0 ? regulomes.get(0) : null;
	}
	
}