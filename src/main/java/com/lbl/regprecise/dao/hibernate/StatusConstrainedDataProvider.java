package com.lbl.regprecise.dao.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;

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
import com.lbl.regprecise.ent.Effector;
import com.lbl.regprecise.ent.EffectorClass;
import com.lbl.regprecise.ent.Gene;
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
import com.lbl.regprecise.ent.Term;

/**
 * @author Pavel Novichkov
 * @author Elena Novichkova
 *
 */

public class StatusConstrainedDataProvider extends ConstrainedDataProvider{

	private int minRegulogStatusTermId;
	private int minCollectionStatusTermId;
	
	public StatusConstrainedDataProvider(int minRegulogStatusTermId)
	{
		super();
		this.minRegulogStatusTermId = minRegulogStatusTermId;
		this.minCollectionStatusTermId = Term.TERM_COLLECTION_STATE_PUBLIC;
	}	

	public StatusConstrainedDataProvider(int minRegulogStatusTermId, int minCollectionStatusTermId)
	{
		super();
		this.minRegulogStatusTermId = minRegulogStatusTermId;
		this.minCollectionStatusTermId = minCollectionStatusTermId;
	}
	
	@Override
	public Collection getCollection(int collectionId) {
		Collection collection = (Collection) session.load(Collection.class, collectionId);
		
		if(collection.getStatusTermId() < minCollectionStatusTermId)
		{
			throw new RuntimeException("Reuqested collection is not public");
		}
		return 	collection;
	}

	private String getBasicCollectionStatDtoSQL()
	{
		String regulogCondition = getRegulogCondition(); 		
		
		return 
				"select " 
				+ " c.collectionId as collectionId" 
				+ " ,c.name as collectionName" 
				+ " ,c.typeTermId as collectionTypeTermId "

				+ " ,(select count(distinct genomeId) " 
						+ " from Collection2Regulog join Regulog r using(regulogId) join Regulon using (regulogId)" 
						+ " where collectionId = c.collectionId and " + regulogCondition + " ) as totlaGenomeCount "
						
				+ " ,(select count(distinct r.regulogId) " 
						+ " from Collection2Regulog  join Regulog  r using (regulogId) " 
						+ " where collectionId = c.collectionId and " + regulogCondition + ") as totalRegulogCount "
						
						
				+ " ,(select count(distinct genomeId) " 
						+ " from Collection2Regulog join Regulog r using(regulogId) join Regulon using (regulogId)" 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF 
						+ " and " + regulogCondition + " ) as tfGenomeCount "
						
				+ " ,(select count(distinct r.regulatorName) " 
						+ " from Collection2Regulog  join Regulog r using (regulogId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF 
						+ " and " + regulogCondition + ") as tfCount "
						
				+ " ,(select count(distinct r.tfFamilyId) " 
						+ " from Collection2Regulog  join Regulog r using (regulogId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF 
						+ " and r.tfFamilyId != null " 
						+  " and " + regulogCondition + ") as tfFamilyCount "
						
				+ " ,(select count(distinct r.regulogId) " 
						+ " from Collection2Regulog  join Regulog  r using (regulogId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
						+  " and " + regulogCondition + ") as tfRegulogCount "
																		
				+ " ,(select count(distinct s.siteId)  " 
						+ " from Collection2Regulog join Regulog r using (regulogId) join Regulon using (regulogId) join Site s using (regulonId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
						+ " and " + regulogCondition + ") as tfSiteCount "
						
				+ " ,(select count(distinct s.regulonId)  " 
						+ " from Collection2Regulog join Regulog r using (regulogId) join Regulon using (regulogId) join Site s using (regulonId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
						+ " and " + regulogCondition + ") as tfRegulonCount "
						
				+ " ,(select count(distinct r.taxonName) " 
						+ " from Collection2Regulog  join Regulog  r using (regulogId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
						+  " and " + regulogCondition + ") as tfTaxonCount "																		
						
																		
				+ " ,(select count(distinct genomeId) " 
						+ " from Collection2Regulog join Regulog r using(regulogId) join Regulon using (regulogId)" 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA 
						+ " and " + regulogCondition + " ) as rnaGenomeCount "
						
				+ " ,(select count(distinct r.riboswitchId) " 
						+ " from Collection2Regulog  join Regulog r using (regulogId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA 
						+ " and " + regulogCondition + ") as rnaCount "
						
				+ " ,(select count(distinct r.regulogId) " 
						+ " from Collection2Regulog  join Regulog  r using (regulogId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA 
						+  " and " + regulogCondition + ") as rnaRegulogCount "
																		
				+ " ,(select count(distinct s.siteId)  " 
						+ " from Collection2Regulog join Regulog r using (regulogId) join Regulon using (regulogId) join Site s using (regulonId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA 
						+ " and " + regulogCondition + ") as rnaSiteCount "
						
				+ " ,(select count(distinct s.regulonId)  " 
						+ " from Collection2Regulog join Regulog r using (regulogId) join Regulon using (regulogId) join Site s using (regulonId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
						+ " and " + regulogCondition + ") as rnaRegulonCount "
						
				+ " ,(select count(distinct r.taxonName) " 
						+ " from Collection2Regulog  join Regulog  r using (regulogId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
						+  " and " + regulogCondition + ") as rnaTaxonCount "							
						
				+ " from Collection c  ";		
	}
	
	@Override
	public CollectionStatDTO getCollectionStatDTO(int collectionId) {
		
		String collectionCondition = getCollectionCondition();
		String basicSQL = getBasicCollectionStatDtoSQL();
		
		return (CollectionStatDTO)
		session.createSQLQuery(
				basicSQL
				+ " where c.collectionId = " + collectionId 
				+ " and " + collectionCondition
				)
		.setResultTransformer(Transformers.aliasToBean(CollectionStatDTO.class))
		.uniqueResult();	
	}

	@SuppressWarnings("unchecked")
	public List<CollectionStatDTO> getCollectionsStatDTO(int typeTermId) {
		
		String collectionCondition = getCollectionCondition();
		String basicSQL = getBasicCollectionStatDtoSQL();
		
		return session.createSQLQuery(
				basicSQL
				+ " where c.typeTermId =  " + typeTermId 
				+ " and " + collectionCondition
				+ " order by c.name")				
		.setResultTransformer(Transformers.aliasToBean(CollectionStatDTO.class))
		.list();		
	}
	
	@Override
	public CollectionStatDTO getSummaryStatDTO() {
	
//TODO		
/*		
		String regulogCondition = getRegulogCondition(); 
		
		CollectionStatDTO stat = (CollectionStatDTO)
		session.createSQLQuery(
				"select " 
				+ " (select count(distinct genomeId) from Regulog r join Regulon using (regulogId) where " + regulogCondition + ") as genomeCount "
				+ " ,(select count(*) from Regulog r   where " + regulogCondition + ") as regulogCount "
				+ " ,(select count(distinct regulatorName) from Regulog r  where " + regulogCondition + ") as regulatorCount "
				+ " ,(select count(distinct regulatorFamily) from Regulog r  where " + regulogCondition + ") as familyCount "
				+ " ,(select count(*)  from Regulog r join Regulon using (regulogId) join Site s using (regulonId) where " + regulogCondition + ") as siteCount "
								
				)
		.setResultTransformer(Transformers.aliasToBean(CollectionStatDTO.class))
		.uniqueResult();
	
		stat.setCollectionId(-1);
		stat.setCollectionName("General stat");
		stat.setCollectionTypeTermId(-1);
	
		return stat;
*/
			return null;
	}
	
	
	

	@SuppressWarnings("unchecked")
	public List<Regulog> getExperimentalyValidatedRegulogs() {
		
		String regulogCondition = getRegulogCondition(); 

		return 
		session.createSQLQuery(
				"select distinct r.* " 
					+ " from Regulog r join Regulog2ExperimentalValidation e using(regulogId) "
					+ " where " + regulogCondition )
			.addEntity(Regulog.class)
			.list();
	}

	public List<GenomeStatDTO> getGenomeStatsDTO(boolean allowEmptyRows) {
		return getGenomeStatsDTO(null, allowEmptyRows);
	}

	
	@SuppressWarnings("unchecked")
	public List<RegulogRegulatorDTO> getRegulogRegulatorsDTO(TFFamily tfFamily)
	{
		String collectionCondition = getCollectionCondition();
		String regulogCondition = getRegulogCondition(); 
		String constrains = regulogCondition + "and " + collectionCondition;
	
		String sql =		
				"select t.*, a.sequence as aaSequence from" 
					+ "( select distinct "
					+ " 	rr.regulatorId as  regulatorId"
					+ "		,rr.moId as regulatorVimssId"
					+ "		,rr.locusTag as regulatorLocusTag "
					+ "		,rn.regulonId as regulonId "
					+ "		,r.regulogId as regulogId "
					+ "		,r.regulatorName as regulatorName "
					+ "		,g.name as genomeName "
					+ "		,g.moId as taxonId "
					+ "		,r.taxonName as taxonName"
					+ "		,r.phylum as phylum"

					+ ", (select CAST(group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as pathway" 
					+ ", (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector"
					
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Site s " 
					+ " where s.regulonId = rn.regulonId"
					+ " ) as regulonSiteCount "
					+ " from Regulog r "
					+ "		join Collection2Regulog using(regulogId)"
					+ "		join Collection c using(collectionId)"
					+ "		join Regulon rn using(regulogId) "
					+ "		join Regulator rr using(regulonId) "
					+ "		join Genome g using(genomeId)"
					+ " where r.tfFamilyId = " + tfFamily.getId() 
					+ "		and " + constrains
					+ ") t"
					+ " left join AASeq a on a.locusId = t.regulatorVimssId"
				+ " where regulonSiteCount > 0";
		
		return session.createSQLQuery(sql)		
			.addScalar("aaSequence", new StringType())
			.addScalar("regulatorId", new IntegerType())
			.addScalar("regulatorVimssId", new IntegerType())
			.addScalar("regulonId", new IntegerType())
			.addScalar("regulogId", new IntegerType())
			.addScalar("regulatorLocusTag", new StringType())
			.addScalar("regulatorName", new StringType())
			.addScalar("regulonSiteCount", new LongType())
			.addScalar("genomeName", new StringType())
			.addScalar("taxonId", new IntegerType())

			.addScalar("taxonName", new StringType())
			.addScalar("phylum", new StringType())
			.addScalar("pathway", new StringType())
			.addScalar("effector", new StringType())
			
			.setResultTransformer(Transformers.aliasToBean(RegulogRegulatorDTO.class))
			.list();					
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GenomeStatDTO> getGenomeStatsDTO(Integer collectionId, boolean allowEmptyRows) {
		
		String collectionCondition = getCollectionCondition();
		String regulogCondition = getRegulogCondition(); 
		String constrains = regulogCondition + "and " + collectionCondition;

		if(collectionId != null)
		{
			constrains += " and c.collectionId = " + collectionId;
		}	
			
		
		
		return session.createSQLQuery(
				
				"select * from" 
					+ "( select "
					+ " 	g.genomeId as genomeId "
					+ "		,g.name as genomeName "
					+ "		,g.moId as taxonomyId "


					+ " ,(select count(distinct n.regulonId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulon n using (regulogId)"
					+ "		join Site s using (regulonId)"
					
					+ " where n.genomeId = g.genomeId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfRegulonCount "
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulon n using (regulogId)"
					+ "		join Site s using (regulonId)"
					+ " where n.genomeId = g.genomeId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfSiteCount "
					
					+ " ,(select count(distinct n.regulonId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulon n using (regulogId)"
					+ "		join Site s using (regulonId)"
					
					+ " where n.genomeId = g.genomeId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaRegulonCount "
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulon n using (regulogId)"
					+ "		join Site s using (regulonId)"
					+ " where n.genomeId = g.genomeId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaSiteCount "					
					
					
					
					+  ( collectionId != null 
							? " from CollectionGenome join Genome g using(genomeId) where collectionId = " + collectionId 
							: " from  Genome g " )
					
					+ ") tt"
					+ ( allowEmptyRows? "" : " where (tfRegulonCount > 0 or rnaRegulonCount > 0)" )
					+  " order by genomeName" 					
		)				
		.setResultTransformer(Transformers.aliasToBean(GenomeStatDTO.class))
		.list();					
	}

	@Override
	public Ortholog getOrtholog(int orthologId) {
		Ortholog ortholog = (Ortholog) session.load(Ortholog.class, orthologId);	
		
		if(!isGoodStatus(ortholog.getCron().getRegulog().getStatusTermId()) )
		{
			throw new RuntimeException("Permissions denied to access this regulog");
		}		
		return ortholog;
	}

	@Override
	public Regulog getRegulog(int regulogId) {
		Regulog regulog =  (Regulog) session.load(Regulog.class, regulogId);
		if(!isGoodStatus(regulog.getStatusTermId()) )
		{
			throw new RuntimeException("Permissions denied to access this regulog");
		}
		return regulog;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegulogStatDTO> getRegulogStatsDTO(TFFamily tfFamily) {
		
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();

		return 
			session.createSQLQuery( 	
					"select * from ("
					+ " select distinct"
					+ " r.regulogId as regulogId," 
					+ " r.regulatorName as regulatorName," 
					+ " r.taxonName as taxonName, "
					+ " r.phylum as phylum, "
					+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as pathway," 
					+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "
					+ " (select rfamId from Riboswitch t where t.riboswitchId = r.riboswitchId) as rnaFamily," 
					+ " (select name from TFFamily t where t.tfFamilyId = r.tfFamilyId) as tfFamily," 
					+ " r.regulationTypeTermId as regulationTypeTermId,"					
					+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount,"
					+ " (select count(distinct s.regulonId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as regulonCount," 					
					+ " (select count(distinct s.siteId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as siteCount" 
					+ " from Regulog r  join Collection2Regulog using(regulogId) join Collection c using(collectionId)"
					+ " where r.tfFamilyId = " + tfFamily.getId()
					+ " and " + regulogCondition
					+ " and " + collectionCondition
					+ ") t "
					+ " where siteCount > 0"
			)	
			.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
			.list();		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegulogStatDTO> getRegulogStatsDTO(int collectionId) {
		
		String regulogCondition = getRegulogCondition();		
		String collectionCondition = getCollectionCondition();

		String sql =
			"select * from ("
			+ " select distinct "
			+ " r.regulogId as regulogId," 
			+ " r.regulatorName as regulatorName," 
			+ " r.taxonName as taxonName, "
			+ " r.phylum as phylum, "
			+ " (select cast( group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId)  as pathway," 
			+ " (select cast( group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "
			+ " (select rfamId from Riboswitch t where t.riboswitchId = r.riboswitchId) as rnaFamily," 
			+ " (select name from TFFamily t where t.tfFamilyId = r.tfFamilyId) as tfFamily,"
			+ " r.regulationTypeTermId as regulationTypeTermId,"			
			+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount," 
			+ " (select count(distinct s.regulonId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as regulonCount," 
			+ " (select count(distinct s.siteId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as siteCount" 
			+ " from Regulog r  join Collection2Regulog using(regulogId) join Collection c using(collectionId)"
			+ " where c.collectionId = " + collectionId
			+ " and " + regulogCondition
			+ " and " + collectionCondition
			+ ") t "
			+ " where siteCount > 0";
		
		return 
		session.createSQLQuery(sql)		
			.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
			.list();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegulogStatDTO> getRegulogStatsDTO() {
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();
		
		return 
		session.createSQLQuery(
				"select * from ("
				+ " select distinct"
				+ " r.regulogId as regulogId," 
				+ " r.regulatorName as regulatorName," 
				+ " r.taxonName as taxonName, "
				+ " r.phylum as phylum, "
				+ " (select CAST(group_concat(name order by name separator ', ') as char(255))  from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as pathway," 
				+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "
				+ " (select rfamId from Riboswitch t where t.riboswitchId = r.riboswitchId) as rnaFamily," 
				+ " (select name from TFFamily t where t.tfFamilyId = r.tfFamilyId) as tfFamily," 
				+ " r.regulationTypeTermId as regulationTypeTermId,"				
				+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount,"
				+ " (select count(distinct s.regulonId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as regulonCount," 				
				+ " (select count(distinct s.siteId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as siteCount" 
				+ " from Regulog r join Collection2Regulog using(regulogId) join Collection c using(collectionId) " 
				+" where " + regulogCondition
				+ " and " + collectionCondition
				+ ") t "
				+ " where siteCount > 0") 
			.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
			.list();		
	}

	@SuppressWarnings("unchecked")
	public List<GeneRegulonDTO> getGeneRegulonDTO(int taxonomyId, List<String> locusTags)
	{
		String regulogCondition = getRegulogCondition(); 
		
		return 
		session.createSQLQuery(
				"select" 
				+ " gn.geneId as geneId ," 
				+ " gn.locusTag as locusTag," 
				+ " gn.regulonId as regulonId,"
				+ " r.regulatorName as regulatorName,"
				+ " r.regulatorName as regulatorName,"
				+ " r.taxonName as taxonName, "
				+ " r.phylum as phylum, "
				+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as function,"
				+ " r.functionClass as pathway," 
				+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "



				+ " from SearchIndex s " 
				+ " join Gene gn on s.objId = gn.operonId " 
				+ " join Regulon rn on rn.regulonId = s.regulonId " 
				+ " join Genome gm using(genomeId) " 
				+ " join Regulog r using(regulogId)"
				
				+ " where s.objType = 3 " 
				+ " and gm.moId = " + taxonomyId
				+ " and gn.locusTag in(" + HibernateUtil.toCommaSeparatedString(locusTags) + ")"	
				+ " and " + regulogCondition
			) 
			.setResultTransformer(Transformers.aliasToBean(GeneRegulonDTO.class))
			.list();				
	}	
	
	@Override
	public Regulon getRegulon(int regulonId) {
		
		Regulon regulon = (Regulon) session.load(Regulon.class, regulonId);
		if(!isGoodStatus(regulon.getRegulog().getStatusTermId()) )
		{
			throw new RuntimeException("Permissions denied to access this regulog");
		}			
		return regulon;
	}

	@SuppressWarnings("unchecked")
	public List<RegulonStatDTO> getRegulonStatsDTO(int collectionId, Integer regulationTypeTermId)
	{
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();
		
		String sql = " select "
			+ " n.regulonId as regulonId,"
			+ " n.regulogId as regulogId," 
			+ " n.genomeId as genomeId," 
			+ " (select count(distinct g.operonId) from Gene g join Site s using(geneId) where g.regulonId = n.regulonId) as operonsWithSiteCount" 
			+ " from Regulon n join  Collection2Regulog using(regulogId) join Regulog r using(regulogId) join Collection c using(collectionId)"
			+ " where c.collectionId = " + collectionId
			
			+ (regulationTypeTermId != null  
				? " and r.regulationTypeTermId = " + regulationTypeTermId
				: "")
				
			
			+ " and " + regulogCondition
			+ " and " + collectionCondition; 
		
		return 
		session.createSQLQuery(sql)
			.setResultTransformer(Transformers.aliasToBean(RegulonStatDTO.class))
			.list();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Regulon> getRegulons(Genome genome) {
		
		String regulogCondition = getRegulogCondition(); 
		
		return 
		session.createSQLQuery("select distinct n.*" 
				+ " from Regulon n join Regulog r using(regulogId)" 
				+ " where n.genomeId = " + genome.getId() 
				+ " and " + regulogCondition)
				.addEntity(Regulon.class)
				.list();	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SearchIndex> getSearchIndeces(String val) {
		return session.createCriteria(SearchIndex.class).
			add( Restrictions.like("objName", val) ).
			addOrder(Order.asc("objType")).			
			addOrder(Order.asc("objName")).			
			addOrder(Order.asc("regulatorName")).			
			addOrder(Order.asc("genomeName")).			
			list(); 		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SearchIndex> getSearchIndecesByGeneVimssId(Integer vimssId) {
		
		return 
		session.createSQLQuery(
				" select s.* " 
				+ " from SearchIndex s join Gene g on s.objId = g.geneId and s.objType = " + Term.TERM_GENE
				+ " where g.moId = " + vimssId)
				.addEntity(SearchIndex.class)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SearchIndex> getSearchIndecesByRegulatorVimssId(Integer vimssId) {
		
		return 
		session.createSQLQuery(
				" select s.* " 
				+ " from SearchIndex s join Regulator r on s.objId = r.regulatorId and s.objType = " + Term.TERM_REGULATOR
				+ " where r.moId = " + vimssId)
				.addEntity(SearchIndex.class)
				.list();
	}
					
	
	@SuppressWarnings("unchecked")
	public List<Integer> getRegulonIds()
	{
		String regulogCondition = getRegulogCondition();
		
		return session.createSQLQuery(" select regulonId from Regulon join Regulog r using(regulogId) "
				+ " where " + regulogCondition
				).list();			
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Regulog> getRegulogs(int collectionId, Integer regulationTypeTermId) {
		String regulogCondition = getRegulogCondition(); 
		String collectionCondition = getCollectionCondition();
		
		return 
		session.createSQLQuery("select distinct r.*" 
				+ " from Regulog r join Collection2Regulog using(regulogId) join Collection c using(collectionId)" 
				+ " where c.collectionId = " + collectionId
				+ ( regulationTypeTermId != null  
						? " and r.regulationTypeTermId = " + regulationTypeTermId
						: ""
				)
				+ " and " + regulogCondition
				+ " and " + collectionCondition)
				.addEntity(Regulog.class)
				.list();			
	}
		
	@SuppressWarnings("unchecked")
	@Override	
	public  List<Regulog> getRegulogs(int collectionId)
	{
		String regulogCondition = getRegulogCondition(); 
		String collectionCondition = getCollectionCondition();
		
		return 
		session.createSQLQuery("select distinct r.*" 
				+ " from Regulog r join Collection2Regulog using(regulogId) join Collection c using(collectionId)" 
				+ " where c.collectionId = " + collectionId
				+ " and " + regulogCondition
				+ " and " + collectionCondition)
				.addEntity(Regulog.class)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Regulog> getRegulogs() {
		return session.createCriteria(Regulog.class)
			.add(Restrictions.gt("statusTermId", minRegulogStatusTermId - 1))
			.list();		
	}	
	

	@SuppressWarnings("unchecked")
	public List<PathwayStatDTO> getPathwaysStatDTO() {
		
		String collectionCondition = getCollectionCondition();
		String regulogCondition = getRegulogCondition(); 
		String constrains = regulogCondition + "and " + collectionCondition;
		
		return session.createSQLQuery(
				
				"select * from" 
					+ "( select "
					+ " 	p.pathwayId as pathwayId "
					+ "		,p.name as pathwayName "
					+ "		,pc.pathwayClassId as pathwayClassId "
					+ "		,pc.name as pathwayClassName "

					+ " ,(select count(distinct genomeId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ " 	join Regulog r using(regulogId) " 
					+ "		join Regulon using (regulogId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId"
					+ " 	and " + constrains 
					+ " ) as totalGenomeCount "

					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId"
					+ " 	and " + constrains 
					+ " ) as totalRegulogCount "
					
					+ " ,(select count(distinct r.taxonName) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId"
					+ " 	and " + constrains 
					+ " ) as totalTaxonCount "
					
					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId"
					+ " 	and " + constrains 
					+ " ) as totalRegulonCount "
					
					
					
					
					
					+ " ,(select count(distinct r.regulatorName) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfCount "
					
					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfRegulogCount "
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfSiteCount "
					
					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfRegulonCount "					

					+ " ,(select count(distinct r.riboswitchId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaCount "
					
					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaRegulogCount "
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaSiteCount "
					
					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Pathway rp using (regulogId)"
					+ " where rp.pathwayId = p.pathwayId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaRegulonCount "
					
					+ " from  Pathway p join PathwayClass pc using(pathwayClassId)) tt"
					+ " where (tfSiteCount > 0 or rnaSiteCount)"
					+ " order by pathwayClassName, pathwayName")				
		.setResultTransformer(Transformers.aliasToBean(PathwayStatDTO.class))
		.list();			
	}
	
	@SuppressWarnings("unchecked")
	public List<PathwayClass> getPathwayClasses()
	{
		return session.createCriteria(PathwayClass.class)
			.addOrder(Order.asc("name"))
			.list();			
	}
	
	public Hashtable<PathwayClass, List<PathwayStatDTO>> getPathwaysStatHashDTO() {		
		
		Hashtable<PathwayClass, List<PathwayStatDTO>> pathwaysStatHash = 
				new Hashtable<PathwayClass, List<PathwayStatDTO>> ();
		
		Hashtable<Integer, PathwayClass> id2PathwayClassHash = new Hashtable<Integer, PathwayClass>();
		
		for(PathwayStatDTO pathwayStat: getPathwaysStatDTO())
		{
			PathwayClass pathwayClass = id2PathwayClassHash.get(pathwayStat.getPathwayClassId());
			if(pathwayClass == null)
			{
				pathwayClass = (PathwayClass) session.get(PathwayClass.class, pathwayStat.getPathwayClassId());
				id2PathwayClassHash.put(pathwayStat.getPathwayClassId(), pathwayClass);
			}
			
			List<PathwayStatDTO> pathwayStats = pathwaysStatHash.get(pathwayClass);
			if(pathwayStats == null)
			{
				pathwayStats = new ArrayList<PathwayStatDTO>();
				pathwaysStatHash.put(pathwayClass, pathwayStats);
			}
			
			pathwayStats.add(pathwayStat);
		}				
		
		return pathwaysStatHash;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<EffectorStatDTO> getEffectorsStatDTO() {
		
		String collectionCondition = getCollectionCondition();
		String regulogCondition = getRegulogCondition(); 
		String constrains = regulogCondition + " and " + collectionCondition;		
		
		return session.createSQLQuery(
				
				"select * from" 
					+ "( select "
					+ " 	e.effectorId as effectorId "
					+ "		,e.name as effectorName "
					+ "		,ec.effectorClassId as effectorClassId "
					+ "		,ec.name as effectorClassName "

					+ " ,(select count(distinct genomeId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ " 	join Regulog r using(regulogId) " 
					+ "		join Regulon using (regulogId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId"
					+ " 	and " + constrains 
					+ " ) as totalGenomeCount "

					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId"
					+ " 	and " + constrains 
					+ " ) as totalRegulogCount "

					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId "
					+ " 	and " + constrains 
					+ " ) as totalRegulonCount "					
					
					+ " ,(select count(distinct r.taxonName) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId"
					+ " 	and " + constrains 
					+ " ) as totalTaxonCount "				

					+ " ,(select count(distinct r.regulatorName) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfCount "
					
					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfRegulogCount "
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfSiteCount "
					
					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
					+ " 	and " + constrains 
					+ " ) as tfRegulonCount "
					
					
					+ " ,(select count(distinct r.riboswitchId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaCount "
					
					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c " 
					+ " 	join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaRegulogCount "

					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaSiteCount "					
					
					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c " 
					+ "		join Collection2Regulog using(collectionId) " 
					+ "		join Regulog r using(regulogId) " 
					+ "		join Regulon using(regulogId) " 
					+ "		join Site s using(regulonId)"
					+ "		join Regulog2Effector re using (regulogId)"
					+ " where re.effectorId = e.effectorId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " 	and " + constrains 
					+ " ) as rnaRegulonCount "									

					+ " from  Effector e join EffectorClass ec using(effectorClassId)) tt"
					+ " where (tfSiteCount > 0 or rnaSiteCount > 0)"
					+ " order by effectorClassName, effectorName")				
		.setResultTransformer(Transformers.aliasToBean(EffectorStatDTO.class))
		.list();			
	}
	
	@SuppressWarnings("unchecked")
	public List<EffectorClass> getEffectorClasses()
	{
		return session.createCriteria(EffectorClass.class)
			.addOrder(Order.asc("name"))
			.list();			
	}
	
	public Hashtable<EffectorClass, List<EffectorStatDTO>> getEffectorsStatHashDTO() {		
		
		Hashtable<EffectorClass, List<EffectorStatDTO>> effectorsStatHash = 
				new Hashtable<EffectorClass, List<EffectorStatDTO>> ();
		
		Hashtable<Integer, EffectorClass> id2EffectorClassHash = new Hashtable<Integer, EffectorClass>();
		
		for(EffectorStatDTO effectorStat: getEffectorsStatDTO())
		{
			EffectorClass effectorClass = id2EffectorClassHash.get(effectorStat.getEffectorClassId());
			if(effectorClass == null)
			{
				effectorClass = (EffectorClass) session.get(EffectorClass.class, effectorStat.getEffectorClassId());
				id2EffectorClassHash.put(effectorStat.getEffectorClassId(), effectorClass);
			}
			
			List<EffectorStatDTO> effectorStats = effectorsStatHash.get(effectorClass);
			if(effectorStats == null)
			{
				effectorStats = new ArrayList<EffectorStatDTO>();
				effectorsStatHash.put(effectorClass, effectorStats);
			}
			
			effectorStats.add(effectorStat);
		}		
		
		return effectorsStatHash;
	}
				
	@SuppressWarnings("unchecked")
	public List<RiboswitchStatDTO> getRiboswitchStatDTO() {
			
		
		String collectionCondition = getCollectionCondition();
		String regulogCondition = getRegulogCondition(); 
		String constrains = regulogCondition + "and " +  collectionCondition;		
		
		return 
		session.createSQLQuery(
				
				"select * from" 
					+ "( select "
					
					+ " riboswitchId as riboswitchId "
					+ ", name as riboswitchName "
					+ ", rfamId as rfamId"

					+ " ,(select count(distinct genomeId) "
					+ " from Collection c " 
							+ " join Collection2Regulog using(collectionId) "
							+ " join Regulog r using(regulogId) " 
							+ " join Regulon using (regulogId)"
							+ " join Site using (regulonId)"
					+ " where r.riboswitchId = t.riboswitchId "
					+ " and " + constrains 
					+ " ) as genomeCount "

					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c " 
						+ " join Collection2Regulog using(collectionId) "
						+ " join Regulog r using(regulogId) " 
						+ " join Regulon using (regulogId)"
						+ " join Site using (regulonId)"
					+ " where r.riboswitchId = t.riboswitchId "
					+ " and " + constrains 
					+ " ) as regulogCount "					
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c " 
						+ " join Collection2Regulog using(collectionId) "
						+ " join Regulog r using(regulogId) " 
						+ " join Regulon using (regulogId)"
						+ " join Site s using (regulonId)"
					+ " where r.riboswitchId = t.riboswitchId "
					+ " and " + constrains 
					+ " ) as siteCount "
					

					+ " ,(select count(distinct r.taxonName) "
					+ " from Collection c " 
						+ " join Collection2Regulog using(collectionId) "
						+ " join Regulog r using(regulogId) " 
						+ " join Regulon using (regulogId)"
						+ " join Site using (regulonId)"
					+ " where r.riboswitchId = t.riboswitchId "
					+ " and " + constrains 
					+ " ) as taxonCount "					
					
					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c " 
						+ " join Collection2Regulog using(collectionId) "
						+ " join Regulog r using(regulogId) " 
						+ " join Regulon using (regulogId)"
						+ " join Site s using (regulonId)"
					+ " where r.riboswitchId = t.riboswitchId "
					+ " and " + constrains 
					+ " ) as regulonCount "
															

					+ " from  Riboswitch t) tt"
					+ " where siteCount > 0"
					+ " order by riboswitchName")				
		.setResultTransformer(Transformers.aliasToBean(RiboswitchStatDTO.class))
		.list();			
	}	
		
	
	
	@SuppressWarnings("unchecked")
	public List<TFFamiliyStatDTO> getTFFamiliyStatDTO() {
			
		
		String collectionCondition = getCollectionCondition();
		String regulogCondition = getRegulogCondition(); 
		String constrains = regulogCondition + " and " + collectionCondition;
		
		String sql =
				"select * from" 
					+ "( select "
					+ " tfFamilyId as tfFamilyId"
					+ ", name as tfFamilyName "
					+ ", domains as proteinDomains"

					+ " ,(select count(distinct genomeId) "
					+ " from Collection c join Collection2Regulog using(collectionId) join Regulog r using(regulogId) join Regulon using (regulogId)"
					+ " where r.tfFamilyId = t.tfFamilyId"
					+ " and " + constrains 
					+ " ) as genomeCount "

					+ " ,(select count(distinct r.regulogId) "
					+ " from Collection c join Collection2Regulog using(collectionId) join Regulog r using(regulogId)"
					+ " where r.tfFamilyId = t.tfFamilyId"
					+ " and " + constrains 
					+ " ) as regulogCount "
					
					+ " ,(select count(distinct r.regulatorName) "
					+ " from Collection c join Collection2Regulog using(collectionId) join Regulog r using(regulogId)"
					+ " where r.tfFamilyId = t.tfFamilyId"
					+ " and " + constrains 
					+ " ) as tfCount "
					
					+ " ,(select count(distinct s.siteId) "
					+ " from Collection c join Collection2Regulog using(collectionId) join Regulog r using(regulogId) join Regulon using(regulogId) join Site s using(regulonId)"
					+ " where r.tfFamilyId = t.tfFamilyId"
					+ " and " + constrains 
					+ " ) as siteCount "
					
					+ " ,(select count(distinct s.regulonId) "
					+ " from Collection c join Collection2Regulog using(collectionId) join Regulog r using(regulogId) join Regulon using(regulogId) join Site s using(regulonId)"
					+ " where r.tfFamilyId = t.tfFamilyId"
					+ " and " + constrains 
					+ " ) as regulonCount "

					+ " ,(select count(distinct r.taxonName) "
					+ " from Collection c join Collection2Regulog using(collectionId) join Regulog r using(regulogId)"
					+ " where r.tfFamilyId = t.tfFamilyId"
					+ " and " + constrains 
					+ " ) as taxonCount "					
					
					+ " from  TFFamily t) tt"
					+ " where siteCount > 0"
					+ " order by tfFamilyName";
		
		return 
			session.createSQLQuery(sql)		
			.setResultTransformer(Transformers.aliasToBean(TFFamiliyStatDTO.class))
			.list();			
	}	
	
	
	public Hashtable<EffectorClass, List<Regulog>> getEffectorClass2RegulogsHash(int collectionId)
	{
		Hashtable<EffectorClass, List<Regulog>> hash = new Hashtable<EffectorClass, List<Regulog>>();
		
		for(Regulog regulog: getRegulogs(collectionId, null))
		{
			for(Effector effector: regulog.getEffectors())
			{
				List<Regulog> effectorClassRegulogs = hash.get(effector.getEffectorClass());
				if(effectorClassRegulogs == null) 
				{
					effectorClassRegulogs = new ArrayList<Regulog>();
					hash.put(effector.getEffectorClass(), effectorClassRegulogs);
				}	
				effectorClassRegulogs.add(regulog);
			}			
		}
		
		// Sort regulogs
		for(List<Regulog> regulogs: hash.values())
		{
			Collections.sort(regulogs, new Comparator<Regulog>(){
				public int compare(Regulog r1, Regulog r2) {
					return r1.getEffectorNames().compareTo(r2.getEffectorNames());
				}
			});
		}
		
		return hash;
	}
	
	public Hashtable<PathwayClass, List<Regulog>> getPathwayClass2RegulogsHash(int collectionId)
	{
		Hashtable<PathwayClass, List<Regulog>> hash = new Hashtable<PathwayClass, List<Regulog>>();
		
		for(Regulog regulog: getRegulogs(collectionId, null))
		{
			for(Pathway pathway: regulog.getPathways())
			{
				List<Regulog> pathwayClassRegulogs = hash.get(pathway.getPathwayClass());
				if(pathwayClassRegulogs == null) 
				{
					pathwayClassRegulogs = new ArrayList<Regulog>();
					hash.put(pathway.getPathwayClass(), pathwayClassRegulogs);
				}	
				pathwayClassRegulogs.add(regulog);
			}			
		}
		
		// Sort regulogs
		for(List<Regulog> regulogs: hash.values())
		{
			Collections.sort(regulogs, new Comparator<Regulog>(){
				public int compare(Regulog r1, Regulog r2) {
					return r1.getPathwayNames().compareTo(r2.getPathwayNames());
				}
			});
		}
		
		
		return hash;
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<Regulator> getRegulators() {
		String regulogCondition = getRegulogCondition();
		
		return 
		session.createSQLQuery("select distinct rl.*" 
				+ " from Regulator rl join Regulon using(regulonId) join Regulog r using(regulogId) " 
				+ " where " + regulogCondition)
				.addEntity(Regulator.class)
				.list();		
	}
	
	public TFFamily getTFFamily(int tfFamilyId)
	{
		return (TFFamily) session.get(TFFamily.class, tfFamilyId);
	}

	public Riboswitch getRiboswitch(int riboswitchId)
	{
		return (Riboswitch) session.get(Riboswitch.class, riboswitchId);
	}
		
	public Operon getOperon(int operonId)
	{
		return (Operon) session.get(Operon.class, operonId);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RegulogStatDTO> getRegulogStatsDTO(Riboswitch riboswitch)
	{		
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();

		return 
			session.createSQLQuery( 	
					"select * from ("
					+" select distinct "
						+ " r.regulogId as regulogId," 
						+ " r.regulatorName as regulatorName," 
						+ " r.taxonName as taxonName, "
						+ " r.phylum as phylum, "
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as pathway," 
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "
						+ " (select rfamId from Riboswitch t where t.riboswitchId = r.riboswitchId) as rnaFamily," 
						+ " (select name from TFFamily t where t.tfFamilyId = r.tfFamilyId) as tfFamily,"
						+ " r.regulationTypeTermId as regulationTypeTermId,"						
						+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount," 
						+ " (select count(distinct s.regulonId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as regulonCount," 						
						+ " (select count(distinct s.siteId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as siteCount" 
					+ " from Regulog r " 
						+ " join Collection2Regulog using(regulogId) " 
						+ " join Collection c using(collectionId) "
						+ " join Riboswitch rw using(riboswitchId) "
					+ " where r.regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
						+ " and rw.riboswitchId = " + riboswitch.getId()
						+ " and " + regulogCondition
						+ " and " + collectionCondition
						+ ") t "
						+ " where siteCount > 0"
			)	
			.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
			.list();	
	}
	
	public Effector getEffector(int effectorId)
	{
		return (Effector) session.get(Effector.class, effectorId);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RegulogStatDTO> getRegulogStatsDTO(Effector effector)
	{		
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();

		return 
			session.createSQLQuery( 
					"select * from ("
					+" select distinct "
						+ " r.regulogId as regulogId," 
						+ " r.regulatorName as regulatorName," 
						+ " r.taxonName as taxonName, "
						+ " r.phylum as phylum, "
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as pathway," 
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "
						+ " (select rfamId from Riboswitch t where t.riboswitchId = r.riboswitchId) as rnaFamily," 
						+ " (select name from TFFamily t where t.tfFamilyId = r.tfFamilyId) as tfFamily,"
						+ " r.regulationTypeTermId as regulationTypeTermId,"						
						+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount," 
						+ " (select count(distinct s.regulonId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as regulonCount," 						
						+ " (select count(distinct s.siteId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as siteCount" 
					+ " from Regulog r " 
						+ " join Collection2Regulog using(regulogId) " 
						+ " join Collection c using(collectionId) "
						+ " join Regulog2Effector e using(regulogId)"
					+ " where  e.effectorId = " + effector.getId()
						+ " and " + regulogCondition
						+ " and " + collectionCondition
					+ ") t "
					+ " where siteCount > 0"
			)	
			.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
			.list();	
	}

	public Pathway getPathway(int pathwayId)
	{
		return (Pathway) session.get(Pathway.class, pathwayId);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RegulogStatDTO> getRegulogStatsDTO(Pathway pathway)
	{		
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();

		return 
			session.createSQLQuery( 
					"select * from ("
					+" select distinct"
						+ " r.regulogId as regulogId," 
						+ " r.regulatorName as regulatorName," 
						+ " r.taxonName as taxonName, "
						+ " r.phylum as phylum, "
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as pathway," 
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "
						+ " (select rfamId from Riboswitch t where t.riboswitchId = r.riboswitchId) as rnaFamily," 
						+ " (select name from TFFamily t where t.tfFamilyId = r.tfFamilyId) as tfFamily,"
						+ " r.regulationTypeTermId as regulationTypeTermId,"
						+ " (select count(distinct n.genomeId) from Regulon n where n.regulogId = r.regulogId) as genomeCount,"
						+ " (select count(distinct s.regulonId) from Site s join Regulon n using (regulonId) where n.regulogId = r.regulogId) as regulonCount," 					
						+ " (select count(distinct s.siteId) " 
								+ " from Site s " 
								+ " join Regulon n using (regulonId) " 
							+" where n.regulogId = r.regulogId) as siteCount" 
					+ " from Regulog r " 
						+ " join Collection2Regulog using(regulogId) " 
						+ " join Collection c using(collectionId) "
						+ " join Regulog2Pathway p using(regulogId)"
					+ " where  p.pathwayId = " + pathway.getId()
						+ " and " + regulogCondition
						+ " and " + collectionCondition
					+ ") t "
					+ " where siteCount > 0"
			)	
			.setResultTransformer(Transformers.aliasToBean(RegulogStatDTO.class))
			.list();	
	}	
	
	@SuppressWarnings("unchecked")
	public List<RegulonDTO> getRegulonsDTO(int genomeId)
	{
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();

		return 
			session.createSQLQuery( 
					" select distinct"
						+ " n.regulonId as regulonId,"
						+ " r.regulogId as regulogId," 
						+ " n.genomeId as genomeId, "
						
						+ " r.taxonName as taxonName, "
						+ " r.phylum as phylum, "
						+ " r.regulatorName as regulatorName," 
						+ " (select rfamId from Riboswitch t where t.riboswitchId = r.riboswitchId) as rnaFamily," 
						+ " (select name from TFFamily t where t.tfFamilyId = r.tfFamilyId) as tfFamily,"
						+ " r.regulatorModeOfAction as regulatorModeOfAction," 
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Effector join Regulog2Effector ef using(effectorId) where ef.regulogId = r.regulogId) as effector, "
						+ " (select CAST(group_concat(name order by name separator ', ') as char(255)) from Pathway join Regulog2Pathway t using(pathwayId) where t.regulogId = r.regulogId) as function," 
						+ " r.regulationTypeTermId as regulationTypeTermId"
						+ ""
					+ " from Regulog r " 
						+ " join Collection2Regulog using(regulogId) " 
						+ " join Collection c using(collectionId) "
						+ " join Regulon n using(regulogId) "
					+ " where  n.genomeId = " + genomeId
						+ " and " + regulogCondition
						+ " and " + collectionCondition
			)	
			.setResultTransformer(Transformers.aliasToBean(RegulonDTO.class))
			.list();			
	}
	
	public List<GeneDTO> getGenesDTO(int regulonId)
	{
		List<GeneDTO> genesDTO = new ArrayList<GeneDTO>();
		
		Regulon regulon = getRegulon(regulonId);
		for(Operon operon: regulon.getOperons())
		{
			if(operon.hasSites())
			{
				for(Gene gene: operon.getGenes())
				{
					genesDTO.add(new GeneDTO(gene.getId(), gene.getMoId(), gene.getName(), gene.getLocusTag()));
				}
			}
		}	
		return genesDTO;		
	}
		
	public List<RegulatorDTO> getRegulatorsDTO(int regulonId)
	{
		List<RegulatorDTO> regulatorsDTO = new ArrayList<RegulatorDTO>();
		
		Regulon regulon = getRegulon(regulonId);
		for(Regulator regulator: regulon.getRegulators())
		{
			regulatorsDTO.add(new RegulatorDTO(regulator.getId(), regulator.getMoId(), regulator.getLocusTag()));
		}	
		return regulatorsDTO;		
	}	
	
	public PropagatedCollection getPropagatedCollection(int prpCollectionId)
	{
		return (PropagatedCollection) session.load(PropagatedCollection.class, prpCollectionId);		
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
			session.createSQLQuery(
						"select  distinct p.* " 
						+ " from PropagatedRegulon p " 
						+ "      join PropagatedCollection2Regulon using (propagatedRegulonId) " 
						+ " where propagatedCollectionId = " +  prpCollectionId )
				.addEntity(PropagatedRegulon.class)
				.list(); 
	}
	
	public PropagatedRegulon getPropagatedRegulon(int pgpRegulonId)
	{
		return (PropagatedRegulon) session.load(PropagatedRegulon.class, pgpRegulonId);
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
	
	public Hashtable<Integer,Hashtable<Integer,PropagatedRegulonStatDTO>> getRegulogId2GenomeId2PropagatedRegulonStatDTOHash(int pgpCollectionId)
	{
		Hashtable<Integer,Hashtable<Integer,PropagatedRegulonStatDTO>> hash 
			= new Hashtable<Integer, Hashtable<Integer,PropagatedRegulonStatDTO>>();
		
		for(PropagatedRegulonStatDTO regulonStat: getPropagatedRegulonStatDTO(pgpCollectionId))
		{
			Integer genomeId = regulonStat.getGenomeId();
			Integer regulogId = regulonStat.getRegulogId();
			
			Hashtable<Integer,PropagatedRegulonStatDTO> subHash = hash.get(regulogId);
			if(subHash == null)
			{
				subHash = new Hashtable<Integer,PropagatedRegulonStatDTO>();
				hash.put(regulogId, subHash);
			}
			subHash.put(genomeId, regulonStat);
		}
		return hash;
	}		
	
	@SuppressWarnings("unchecked")
	public List<PropagatedRegulonStatDTO> getPropagatedRegulonStatDTO(int pgpCollectionId)
	{
		return 
		session.createSQLQuery(
				"select  r.propagatedRegulonId, r.regulogId, r.genomeId,"
				+ " (	select count(distinct propagatedGeneId) from PropagatedSite s where s.isPublic = 1 and s.propagatedRegulonId = r.propagatedRegulonId  ) as geneCount, " 
				+ " (	select count(*) from PropagatedRegulator t where t.propagatedRegulonId = r.propagatedRegulonId ) as regulatorCount "
				+ " from PropagatedCollection c "
				+ "      join PropagatedCollection2Regulon using (propagatedCollectionId ) " 
				+ "      join PropagatedRegulon r using(propagatedRegulonId)"
				
				+ " where  c.propagatedCollectionId = " + pgpCollectionId
				)				
		.setResultTransformer(Transformers.aliasToBean(PropagatedRegulonStatDTO.class))
		.list();				
	}
	
	
	@SuppressWarnings("unchecked")
	public  List<PropagatedGenomeStatDTO> getPropagatedGenomeStatsDTO(int propagatedCollectionId)
	{
		return 
		session.createSQLQuery(
				"select genomeId, name as genomeName, count(*) as regulonCount, sum(regulonSiteCount) as siteCount"
				+" from ("
				+" select g.genomeId, g.name , r.propagatedRegulonId, count(*) as regulonSiteCount"
				+" from PropagatedCollection2Regulon join PropagatedRegulon r using(propagatedRegulonId) join PropagatedSite s using(propagatedRegulonId) join Genome g using(genomeId)" 
				+" where s.isPublic = 1 and propagatedCollectionId = " + propagatedCollectionId
				+" group by g.genomeId, g.name, r.propagatedRegulonId"
				+" ) t"
				+" group by genomeId, name"
				+" order by name"
				)				
		.setResultTransformer(Transformers.aliasToBean(PropagatedGenomeStatDTO.class))
		.list();				
	}
	
	@SuppressWarnings("unchecked")
	public List<PropagatedRegulon> getPropagatedRegulons(int pgpCollectionId, int genomeId)
	{
		
		return 
		session.createSQLQuery(
				" select r.* from PropagatedCollection2Regulon join PropagatedRegulon r using(propagatedRegulonId) join Regulog  using(regulogId)"
				+" where propagatedCollectionId = " + pgpCollectionId + " and genomeId = " + genomeId
				+" order by regulationTypeTermId, regulatorName"
				)				
		.addEntity(PropagatedRegulon.class)
		.list();		
	}
	

	
	@SuppressWarnings("unchecked")
	public List<PropagatedCollectionStatDTO> getPropagatedCollectionStatsDTO()
	{
		String sql = getPropagatedCollectionStatsDTO_SQL(null);
		return session.createSQLQuery( sql )				
				.setResultTransformer(Transformers.aliasToBean(PropagatedCollectionStatDTO.class))
				.list();				
	}
	
	
	public PropagatedCollectionStatDTO getPropagatedCollectionStatDTO(int propagatedCollectionId)
	{
		String sql = getPropagatedCollectionStatsDTO_SQL(propagatedCollectionId);
		return (PropagatedCollectionStatDTO) session.createSQLQuery( sql )				
				.setResultTransformer(Transformers.aliasToBean(PropagatedCollectionStatDTO.class))
				.uniqueResult();				
		
	}
	
	private String getPropagatedCollectionStatsDTO_SQL(Integer propagatedCollectionId)
	{
		return 
		"select propagatedCollectionId"
		+", t.name as targetTaxonName"
		+", (select count(*) from TaxonGenome where taxonId = t.taxonId) as targetGenomeCount"
		+", (select count(distinct propagatedRegulonId) from PropagatedCollection2Regulon join PropagatedRegulon using (propagatedRegulonId) join PropagatedSite s using(propagatedRegulonId) where s.isPublic = 1 and propagatedCollectionId = pc.propagatedCollectionId) as targetRegulonCount"
		+", collectionId as sourceRegulogCollectionId"
		+", c.name as sourceRegulogCollectionName"
		+", (select count(*) from CollectionGenome where collectionId = c.collectionId) as sourceGenomeCount"
		+", (select count(*) from Collection2Regulog join Regulog r using (regulogId) where  collectionId = c.collectionId and r.regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF + " and r.statusTermId >= " + minRegulogStatusTermId +") as sourceRegulogCount"
		+", (select count(distinct rn.regulonId) from Collection2Regulog join Regulog r using(regulogId) join Regulon rn using (regulogId) join Site using(regulonId) where  collectionId = c.collectionId and r.regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF + " and r.statusTermId >= " + minRegulogStatusTermId +") as sourceRegulonCount"
		+" from PropagatedCollection pc join Taxon t using(taxonId) join Collection c using(collectionId)"	
		+ (propagatedCollectionId != null ? " where pc.propagatedCollectionId = " + propagatedCollectionId : ""  );	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GLAMMElementDTO> getGLAMMElementsDTO(Integer resourceId, String resourceType)
	{
		String regulogCondition = getRegulogCondition();
		String collectionCondition = getCollectionCondition();		
		
		String sqlSpecificPart = " where 1=1";
		
		if(resourceType.equals(GLAMMElementDTO.TYPE_TAXONOMY)){
			sqlSpecificPart = " where g.moId = " + resourceId;
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_GENOME)){
			sqlSpecificPart = " where g.genomeId = " + resourceId;
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_REGULOG)){
			sqlSpecificPart = " where r.regulogId = " + resourceId;			
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_REGULON)){
			sqlSpecificPart = " where rn.regulonId = " + resourceId;			
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_COLLECTION_TAXGROUP)){
			sqlSpecificPart = " where c.collectionId = " + resourceId;			
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_COLLECTION_TF)){
			sqlSpecificPart = " where c.collectionId = " + resourceId;			
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_COLLECTION_TFFAM)){
			sqlSpecificPart = " where r.tfFamilyId = " + resourceId;			
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_COLLECTION_RNAFAM)){
			sqlSpecificPart = " where r.riboswitchId = " + resourceId;			
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_COLLECTION_PATHWAY)){
			sqlSpecificPart = " join Regulog2Pathway using(regulogId) where pathwayId = " + resourceId;			
		}
		else if (resourceType.equals(GLAMMElementDTO.TYPE_COLLECTION_EFFECTOR)){
			sqlSpecificPart = " join Regulog2Effector using(regulogId) where effectorId = " + resourceId;			
		}

		
		return 
		session.createSQLQuery(
				"select regulonId, t.regulatorName, t.genomeMoId, t.genomeName, moId as geneMoId, orthologId " 
				+ " from Gene" 
				+ " join" 
				+ " ("
				+ " select distinct operonId, r.regulatorName, g.moId as genomeMoId, g.name as genomeName"
				+ " from " 
				+ " Collection c " 
				+ " join Collection2Regulog using(collectionId) " 
				+ " join Regulog r using(regulogId)" 
				+ " join Regulon rn using (regulogId)" 
				+ " join Genome g using(genomeId)"
				+ " join Gene  using(regulonId)" 
				+ " join Site using(geneId)" 
				+ sqlSpecificPart
					+ " and " + regulogCondition
					+ " and " + collectionCondition
					
				+" ) t using(operonId)"	
			).setResultTransformer(Transformers.aliasToBean(GLAMMElementDTO.class))
			.list();		
	}
	

	@Override
	public ProcessState getProcessState(Integer processId) {
		return (ProcessState) session.get(ProcessState.class, processId);
	}	
	
	public void clear()
	{
		session.clear();
	}
		
	private String getRegulogCondition()
	{
		return 
			"( "
			+ "r.statusTermId >= " + minRegulogStatusTermId
			+ ")";
	}

	private String getCollectionCondition()
	{
		return 
			"( "
			+ "c.statusTermId >= " + minCollectionStatusTermId
			+ ")";
	}	
	
	private boolean isGoodStatus(int reglogStatusTermId)
	{
		return reglogStatusTermId >= minRegulogStatusTermId;
	}		
	

	@SuppressWarnings("unchecked")
	@Override
	public Regulome getRegulomeByKbaseId(String regulomeKBaseId) {
		List<Regulome> regulomes = 
			session.createCriteria(Regulome.class).
			add( Restrictions.like("kbaseId", regulomeKBaseId) ).list();
		
		return regulomes.size() > 0 ? regulomes.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Regulome getRegulomeByGenomeKbaseId(String genomeKBaseId) {
		List<Regulome> regulomes = 
			session.createCriteria(Regulome.class)
			.createAlias("genome", "g")
			.add( Restrictions.like("g.kbaseId", genomeKBaseId) ).list();
		
		return regulomes.size() > 0 ? regulomes.get(0) : null;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public Regulon getRegulonByKbaseId(String regulonKBaseId) {
		List<Regulon> regulons = 
			session.createCriteria(Regulon.class).
			add( Restrictions.like("kbaseId", regulonKBaseId) ).list();
		
		return regulons.size() > 0 ? regulons.get(0) : null;
	}

	private String getRegulomeStatDTOsBasicSQL()
	{
		String regulogCondition = getRegulogCondition(); 		
		String collectionCondition = getCollectionCondition();
				
		String sql =  
				" select "
				+ " rm.regulomeId as regulomeId" 
				+ " ,rm.kbaseId as regulomeKBaseId" 
				+ " ,gm.genomeId as genomeId" 
				+ " ,gm.kbaseId as genomeKBaseId" 
				+ " ,gm.name as genomeName" 
				+ ", gm.moId as genomeMOId"
				
				+ " ,(select count(distinct s.regulonId)  " 
						+ " from Collection c join Collection2Regulog using (collectionId) join Regulog r using (regulogId) join Regulon using (regulogId) join Site s using (regulonId) " 
						+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_TF
						+ " and " + regulogCondition + " and " + collectionCondition + ") as tfRegulonCount "
				+ " ,(select count(distinct s.regulonId)  " 
					+ " from Collection c join Collection2Regulog using (collectionId) join Regulog r using (regulogId) join Regulon using (regulogId) join Site s using (regulonId) " 
					+ " where collectionId = c.collectionId and regulationTypeTermId = " + Term.TERM_REGULATION_TYPE_RNA
					+ " and " + regulogCondition + " and " + collectionCondition + ") as rnaRegulonCount "
				
						
				+ " from Regulome rm "
					+ " join  Genome gm using(genomeId) ";
		return sql;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegulomeStatDTO> getRegulomeStatDTOs() {

		String sql =  
				"select * from ("
				+ getRegulomeStatDTOsBasicSQL() 
				+ ") t "
				+ " where tfRegulonCount > 0 or rnaRegulonCount > 0";		
	
		return session.createSQLQuery( sql )				
			.setResultTransformer(Transformers.aliasToBean(RegulomeStatDTO.class))
			.list();			
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegulomeStatDTO> getRegulomeStatDTOsByGenomeKBaseId(String genomeKBaseId) {
		String sql =  
			"select * from ("
				+ getRegulomeStatDTOsBasicSQL()
				+ " where gm.kbaseId = " + genomeKBaseId
			+ ") t "
			+ " where tfRegulonCount > 0 or rnaRegulonCount > 0";		

		return session.createSQLQuery( sql )				
		.setResultTransformer(Transformers.aliasToBean(RegulomeStatDTO.class))
		.list();			
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<KBaseGeneDTO>> getCoregualtedGenes(
			List<String> kbaseGeneIds) {
		String sql =
			"select distinct queryKBaseId, kbaseId, name, locusTag from Gene join ("
			+ " select distinct queryKBaseId, operonId"
			+ " from Gene join ("
			+ " select distinct g1.kbaseId as queryKBaseId,  g1.regulonId"
			+ " from " + getKBaseGeneFromSQL(kbaseGeneIds)
			+ ") t using(regulonId) join Site using(geneId)"
			+ " ) t using(operonId)";

		List<KBaseGeneDTO> geneDTOs = session.createSQLQuery( sql )				
			.setResultTransformer(Transformers.aliasToBean(KBaseGeneDTO.class))
			.list();	
		
		Map<String, List<KBaseGeneDTO>> kbaseId2GenesHash = new Hashtable<String, List<KBaseGeneDTO>>();
		for(KBaseGeneDTO geneDTO: geneDTOs)
		{
			String querKBaseId = geneDTO.getQueryKBaseId(); 
			if(querKBaseId == null) continue;
			if(querKBaseId == geneDTO.getKbaseId()) continue;
			
			List<KBaseGeneDTO> targetGenes = kbaseId2GenesHash.get(querKBaseId);
			if(targetGenes == null)
			{
				targetGenes = new Vector<KBaseGeneDTO>();
				kbaseId2GenesHash.put(querKBaseId, targetGenes);
			}
			targetGenes.add(geneDTO);
		}
		
		return kbaseId2GenesHash;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<KBaseRegulatorDTO>> getRegulators(List<String> kbaseGeneIds) {
		String sql =
			"select distinct queryKBaseId, rr.kbaseId, rg.regulatorName as name, rr.locusTag, rg.regulationTypeTermId"
			+ " from Regulator rr join ("
			+ " select distinct  g1.kbaseId as queryKBaseId, g1.regulonId, r.regulogId"
			
			+ " from " + getKBaseGeneFromSQL(kbaseGeneIds)
			+ " ) t using(regulonId) join Regulog rg using(regulogId)";

		List<KBaseRegulatorDTO> regulatorDTOs = session.createSQLQuery( sql )				
			.setResultTransformer(Transformers.aliasToBean(KBaseRegulatorDTO.class))
			.list();			

		Map<String, List<KBaseRegulatorDTO>> kbaseId2RegulatorsHash = new Hashtable<String, List<KBaseRegulatorDTO>>();
		for(KBaseRegulatorDTO regulatorDTO: regulatorDTOs)
		{
			String querKBaseId = regulatorDTO.getQueryKBaseId(); 
			if(querKBaseId == null) continue;
			if(querKBaseId == regulatorDTO.getKbaseId()) continue;
			
			List<KBaseRegulatorDTO> targetRegulators = kbaseId2RegulatorsHash.get(querKBaseId);
			if(targetRegulators == null)
			{
				targetRegulators = new Vector<KBaseRegulatorDTO>();
				kbaseId2RegulatorsHash.put(querKBaseId, targetRegulators);
			}
			targetRegulators.add(regulatorDTO);
		}
		
		return kbaseId2RegulatorsHash;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<KBaseRegulonDTO>> getRegulons(List<String> kbaseGeneIds) {
		String sql =
			"select distinct g1.kbaseId as queryKBaseId, rn.kbaseId, r.regulatorName, r.regulationTypeTermId"
			+ " from " + getKBaseGeneFromSQL(kbaseGeneIds);
		List<KBaseRegulonDTO> regulonDTOs = session.createSQLQuery( sql )				
			.setResultTransformer(Transformers.aliasToBean(KBaseRegulonDTO.class))
			.list();	
		
		Map<String, List<KBaseRegulonDTO>> kbaseId2RegulonsHash = new Hashtable<String, List<KBaseRegulonDTO>>();
		for(KBaseRegulonDTO regulonDTO: regulonDTOs)
		{
			String querKBaseId = regulonDTO.getQueryKBaseId(); 
			if(querKBaseId == null) continue;
			if(querKBaseId == regulonDTO.getKbaseId()) continue;
			
			List<KBaseRegulonDTO> targetRegulons = kbaseId2RegulonsHash.get(querKBaseId);
			if(targetRegulons == null)
			{
				targetRegulons = new Vector<KBaseRegulonDTO>();
				kbaseId2RegulonsHash.put(querKBaseId, targetRegulons);
			}
			targetRegulons.add(regulonDTO);
		}
		
		return kbaseId2RegulonsHash;
	}
	
	@SuppressWarnings("unchecked")
	public List<KBaseRegulonDTO> getKBaseRegulonDTOs(List<String> kbaseRegulonIds) {
		String sql =
			"select distinct rn.kbaseId as queryKBaseId, rn.kbaseId, r.regulatorName, r.regulationTypeTermId"
			+ " from Regulon rn join Regulog r using(regulogId) where rn.kbaseId in(" + getStringList(kbaseRegulonIds) + ")";
		return session.createSQLQuery( sql )				
			.setResultTransformer(Transformers.aliasToBean(KBaseRegulonDTO.class))
			.list();	
		
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<KBaseGeneDTO> getRegulonKBaseGeneDTOs(String kbaseRegulonId)
	{
		String sql = 
			"select regulonKBaseId as queryKBaseId, g.kbaseId, g.name, g.locusTag"
			+ " from Gene g join"
			+ " ("
			+ " select distinct rn.kbaseId as regulonKBaseId, operonId"
			+ " from Regulon rn join Site using(regulonId) join Gene using(geneId)"
			+ " where rn.kbaseId = '" + kbaseRegulonId+ "'"
			+ " ) t using(operonId)";
		
		return session.createSQLQuery( sql )				
		.setResultTransformer(Transformers.aliasToBean(KBaseGeneDTO.class))
		.list();			
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KBaseGeneDTO> getRegulatedGenes(List<String> kbaseGeneIds) {
		String sql =
			"select distinct g1.kbaseId as queryKBaseId, g1.kbaseId, g1.name, g1.locusTag "
			+ " from " + getKBaseGeneFromSQL(kbaseGeneIds);

		return session.createSQLQuery( sql )				
			.setResultTransformer(Transformers.aliasToBean(KBaseGeneDTO.class))
			.list();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KBaseGene2GeneDTO> getCoregulatedGenePairs(
			List<String> kbaseGeneIds) {
		
		String sql =
			"select t1.kbaseId as geneKBaseId1, t2.kbaseId as geneKBaseId2, t1.regulonKBaseId,  t1.regulatorName, t1.regulationTypeTermId" 
			+ " from"
			+ " ("
			+ " select distinct g1.kbaseId , rn.regulonId, rn.kbaseId as regulonKBaseId, r.regulatorName, r.regulationTypeTermId"
			+ " from" 
			+ getKBaseGeneFromSQL(kbaseGeneIds)
			+ " ) t1 join" 
			+ " ("
			+ " select distinct g1.kbaseId , rn.regulonId, rn.kbaseId as regulonKBaseId, r.regulatorName, r.regulationTypeTermId"
			+ " from" 
			+ getKBaseGeneFromSQL(kbaseGeneIds)
			+ " ) t2 using(regulonId)"
			+ " where t1.kbaseId < t2.kbaseId";

		return session.createSQLQuery( sql )				
			.setResultTransformer(Transformers.aliasToBean(KBaseGene2GeneDTO.class))
			.list();
	}	
	
	private String getKBaseGeneFromSQL(List<String> kbaseGeneIds)
	{
		String regulogCondition = getRegulogCondition(); 		
		String collectionCondition = getCollectionCondition();
		
		String sql = 

			" Collection c join Collection2Regulog using (collectionId) join Regulog r using (regulogId) join Regulon rn using (regulogId)" 
			+ " join Gene g1 using (regulonId) join Gene g2 using(operonId) join Site s on s.geneId = g2.geneId"
			+ " where g1.kbaseId in( " + getStringList(kbaseGeneIds) + ") "
			+ " and " +  regulogCondition + " and " + collectionCondition ;
		
		return sql;
	}
	
	private String getStringList(List<String> vals) {
		StringBuffer sb = new StringBuffer();
		for(String val: vals)
		{
			if(sb.length() > 0 ){
				sb.append(",");
			}
			sb.append("'" + val + "'");
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PropagatedRegulon getPropagatedRegulonByKBaseId(String regulonKBaseId) {
		List<PropagatedRegulon> regulons = 
			session.createCriteria(PropagatedRegulon.class).
			add( Restrictions.like("kbaseId", regulonKBaseId) ).list();
		
		return regulons.size() > 0 ? regulons.get(0) : null;
	}


}
