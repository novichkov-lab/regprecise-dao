package com.lbl.regprecise.dao.hibernate;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lbl.regprecise.dto.CollectionStatDTO;
import com.lbl.regprecise.dto.EffectorStatDTO;
import com.lbl.regprecise.dto.GLAMMElementDTO;
import com.lbl.regprecise.dto.GeneDTO;
import com.lbl.regprecise.dto.GeneRegulonDTO;
import com.lbl.regprecise.dto.GenomeStatDTO;
import com.lbl.regprecise.dto.KBaseGene2GeneDTO;
import com.lbl.regprecise.dto.KBaseGeneDTO;
import com.lbl.regprecise.dto.KBaseRegulatorDTO;
import com.lbl.regprecise.dto.KBaseRegulonDTO;
import com.lbl.regprecise.dto.PathwayStatDTO;
import com.lbl.regprecise.dto.PropagatedCollectionStatDTO;
import com.lbl.regprecise.dto.PropagatedGenomeStatDTO;
import com.lbl.regprecise.dto.PropagatedRegulonStatDTO;
import com.lbl.regprecise.dto.RegulatorDTO;
import com.lbl.regprecise.dto.RegulogRegulatorDTO;
import com.lbl.regprecise.dto.RegulogStatDTO;
import com.lbl.regprecise.dto.RegulomeStatDTO;
import com.lbl.regprecise.dto.RegulonDTO;
import com.lbl.regprecise.dto.RegulonStatDTO;
import com.lbl.regprecise.dto.RiboswitchStatDTO;
import com.lbl.regprecise.dto.TFFamiliyStatDTO;
import com.lbl.regprecise.ent.Collection;
import com.lbl.regprecise.ent.ComputationalValidation;
import com.lbl.regprecise.ent.DatabaseRelease;
import com.lbl.regprecise.ent.Effector;
import com.lbl.regprecise.ent.EffectorClass;
import com.lbl.regprecise.ent.Genome;
import com.lbl.regprecise.ent.Operon;
import com.lbl.regprecise.ent.Ortholog;
import com.lbl.regprecise.ent.Pathway;
import com.lbl.regprecise.ent.PathwayClass;
import com.lbl.regprecise.ent.ProcessState;
import com.lbl.regprecise.ent.PropagatedCollection;
import com.lbl.regprecise.ent.PropagatedRegulon;
import com.lbl.regprecise.ent.Regulator;
import com.lbl.regprecise.ent.Regulog;
import com.lbl.regprecise.ent.Regulome;
import com.lbl.regprecise.ent.Regulon;
import com.lbl.regprecise.ent.Riboswitch;
import com.lbl.regprecise.ent.SearchIndex;
import com.lbl.regprecise.ent.TFFamily;

/**
 * @author Pavel Novichkov
 *
 */
public abstract class ConstrainedDataProvider {
	Session session;
	
	public ConstrainedDataProvider()
	{
		session = HibernateUtil.getSession();
	}
	
	public Session getSession()
	{
		return session;
	}
	
	public void close()
	{
		session.close();
	}
	
	public void save(Object obj) {
		session.save(obj);		
	}
	
	public void saveOrUpdate(Object obj) {
		session.saveOrUpdate(obj);		
	}
	
	public void beginTransaction()
	{
		session.beginTransaction();		
	}
	
	public void commit()
	{
		session.getTransaction().commit();
	}
	
	
	public void clearLog(int jobTermId)
	{
		session.createSQLQuery("delete from Log where jobTermId = " + jobTermId ).executeUpdate();			
	}
	
	@SuppressWarnings("unchecked")
	public Genome getGenomeByMOId(Integer moGenomeId)
	{
		List<Genome> genomes =  session.createCriteria(Genome.class).
			add( Restrictions.like("moId", moGenomeId) ).list();
		return genomes.size() > 0 ? genomes.get(0) : null;
	}	
	
	public Genome getGenome(int genomeId)
	{
		return (Genome) session.load(Genome.class, genomeId);		
	}	
	
	@SuppressWarnings("unchecked")
	public List<ComputationalValidation> getComputationalValidations() {
		return session.createCriteria(ComputationalValidation.class)
			.addOrder(Order.asc("orderIndex"))
			.list();		
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
	
	public Hashtable<Integer, Hashtable<Integer, RegulonStatDTO>> getRegulonStatsDTOHash(int collectionId)
	{
		return getRegulonStatsDTOHash(collectionId, null);
	}

	
	public Hashtable<Integer, Hashtable<Integer, RegulonStatDTO>> getRegulonStatsDTOHash(int collectionId, Integer regulationTypeTermId)
	{
		Hashtable<Integer, Hashtable<Integer, RegulonStatDTO>> regulog2genome2regulonStatHash = new Hashtable<Integer, Hashtable<Integer, RegulonStatDTO>>();
		for(RegulonStatDTO rs : getRegulonStatsDTO(collectionId, regulationTypeTermId))
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
	
	public abstract List<RegulonStatDTO> getRegulonStatsDTO(int collectionId, Integer regulationTypeTermId);
	
	public abstract CollectionStatDTO getCollectionStatDTO(int collectionId);
		
	public abstract List<RegulogStatDTO> getRegulogStatsDTO(int collectionId);
	
	public abstract List<GenomeStatDTO> getGenomeStatsDTO(boolean allowEmptyRows);
	
	public abstract List<GenomeStatDTO> getGenomeStatsDTO(Integer collectionId, boolean allowEmptyRows);
	
	public abstract CollectionStatDTO getSummaryStatDTO();
	
	public abstract List<RegulogStatDTO> getRegulogStatsDTO();
	
	public abstract Collection getCollection(int collectionId);	
	
	public abstract List<CollectionStatDTO> getCollectionsStatDTO(int typeTermId);
	
	public abstract List<Regulog> getExperimentalyValidatedRegulogs();
	
	public abstract List<Regulon> getRegulons(Genome genome);
	
	public abstract Regulon getRegulon(int regulonId);
	
	public abstract Regulog getRegulog(int regulogId);
	
	public abstract Ortholog getOrtholog(int orthologId);
	
	public abstract List<SearchIndex> getSearchIndeces(String val);	
	
	public abstract List<Integer> getRegulonIds();	
	
	public abstract List<Regulog> getRegulogs(int collectionId, Integer regulationTypeTermId);
	
	public abstract List<Regulog> getRegulogs(int collectionId);
	
	public abstract List<Regulog> getRegulogs();
	
	public abstract List<Regulator> getRegulators();
	 	
	public abstract  List<TFFamiliyStatDTO> getTFFamiliyStatDTO();
	
	public abstract List<EffectorClass> getEffectorClasses();
	
	public abstract Hashtable<EffectorClass, List<EffectorStatDTO>> getEffectorsStatHashDTO();		
	
	public abstract List<PathwayClass> getPathwayClasses();
	
	public abstract Hashtable<PathwayClass, List<PathwayStatDTO>> getPathwaysStatHashDTO();			
	
	public abstract List<RiboswitchStatDTO> getRiboswitchStatDTO();
	
	public abstract Hashtable<EffectorClass, List<Regulog>> getEffectorClass2RegulogsHash(int collectionId);	
	
	public abstract Hashtable<PathwayClass, List<Regulog>> getPathwayClass2RegulogsHash(int collectionId);
	
	public abstract List<RegulogStatDTO> getRegulogStatsDTO(TFFamily tfFamily); 
	
	public abstract TFFamily getTFFamily(int tfFamilyId);
	
	public abstract Riboswitch getRiboswitch(int riboswitchId);
	
	public abstract Operon getOperon(int operonId);
	
	public abstract List<RegulogStatDTO> getRegulogStatsDTO(Riboswitch riboswitch);
	
	public abstract Effector getEffector(int effectorId);
	
	public abstract List<RegulogStatDTO> getRegulogStatsDTO(Effector effector);	

	public abstract Pathway getPathway(int pathwayId);
	
	public abstract List<RegulogStatDTO> getRegulogStatsDTO(Pathway pathway);	
	
	public abstract List<EffectorStatDTO> getEffectorsStatDTO();
	
	public abstract List<PathwayStatDTO> getPathwaysStatDTO();

	public abstract List<RegulonDTO> getRegulonsDTO(int genomeId);
	
	public abstract List<GeneDTO> getGenesDTO(int regulonId);
	
	public abstract List<RegulatorDTO> getRegulatorsDTO(int regulonId);

	public abstract void clear();
	
	public abstract List<GeneRegulonDTO> getGeneRegulonDTO(int taxonomyId, List<String> locusTags);

	public abstract List<SearchIndex> getSearchIndecesByGeneVimssId(Integer geneVimssId);

	public abstract List<SearchIndex> getSearchIndecesByRegulatorVimssId(Integer vimssId);
	
	public abstract PropagatedCollection getPropagatedCollection(int prpCollectionId);
	
	public abstract List<PropagatedRegulonStatDTO> getPropagatedRegulonStatDTO(int pgpCollectionId);
	
	public abstract Hashtable<Integer,Hashtable<Integer,PropagatedRegulonStatDTO>> getGenomeId2RegulogId2PropagatedRegulonStatDTOHash(int pgpCollectionId);
	
	public abstract Hashtable<Integer,Hashtable<Integer,PropagatedRegulonStatDTO>> getRegulogId2GenomeId2PropagatedRegulonStatDTOHash(int pgpCollectionId);
	
	public abstract PropagatedRegulon getPropagatedRegulon(int pgpRegulonId);
	
	public abstract List<PropagatedRegulon> getPropagatedRegulons(int prpCollectionId);
	
	public abstract void populatePropagatedLists(int prpCollectionId, 
			List<Regulog> regulogs, List<Genome> genomes);
	
	public abstract ProcessState getProcessState(Integer processId);
	
	public abstract List<GLAMMElementDTO> getGLAMMElementsDTO(Integer resourceId, String resourceType);

	public abstract Regulome getRegulomeByKbaseId(String regulomeKBaseId);
	public abstract Regulome getRegulomeByGenomeKbaseId(String genomeKBaseId);

	public abstract Regulon getRegulonByKbaseId(String regulonKBaseId);
	public abstract PropagatedRegulon getPropagatedRegulonByKBaseId(String regulonKBaseId);

	public abstract List<RegulomeStatDTO> getRegulomeStatDTOs();
	
	public abstract  List<RegulomeStatDTO> getRegulomeStatDTOsByGenomeKBaseId(String genomeKBaseId);
	
	
	public abstract List<KBaseGeneDTO> getRegulatedGenes(List<String> kbaseGeneIds);
	public abstract Map<String,List<KBaseGeneDTO>> getCoregualtedGenes(List<String> kbaseGeneIds);
	public abstract Map<String,List<KBaseRegulatorDTO>> getRegulators(List<String> kbaseGeneIds);
	public abstract Map<String,List<KBaseRegulonDTO>> getRegulons(List<String> kbaseGeneIds);
	public abstract List<KBaseGene2GeneDTO> getCoregulatedGenePairs(List<String> kbaseGeneIds);
	public abstract List<KBaseRegulonDTO> getKBaseRegulonDTOs(List<String> kbaseRegulonIds);
	public abstract List<KBaseGeneDTO> getRegulonKBaseGeneDTOs(String kbaseRegulonId);
	public abstract List<PropagatedCollectionStatDTO> getPropagatedCollectionStatsDTO();
	public abstract PropagatedCollectionStatDTO getPropagatedCollectionStatDTO(int propagatedCollectionId);
	public abstract List<PropagatedGenomeStatDTO> getPropagatedGenomeStatsDTO(int propagatedCollectionId);
	public abstract List<PropagatedRegulon> getPropagatedRegulons(int pgpCollectionId, int genomeId);
	
	public abstract List<RegulogRegulatorDTO> getRegulogRegulatorsDTO(TFFamily tfFamily);

	
		
}
