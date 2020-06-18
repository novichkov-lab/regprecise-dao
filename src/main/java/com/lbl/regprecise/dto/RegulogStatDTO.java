package com.lbl.regprecise.dto;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.lbl.regprecise.ent.Term;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class RegulogStatDTO {
	private Integer regulogId;
	private String regulatorName;
	private String taxonName;
	
	private String phylum;
	private String effector;
	private String pathway;
	private String tfFamily;
	private String rnaFamily;
	
	private Integer regulationTypeTermId;
	private BigInteger genomeCount;
	private BigInteger regulonCount;
	private BigInteger siteCount;
	
	
	public static void sortByPhylumTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.phylum + " - " + o1.getTaxonName();
				String s2 = o2.phylum + " - " + o2.getTaxonName();
				return s1.compareTo(s2);
			}
		});
	}	
	
	public static void sortByRegnamePhylum(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.regulatorName + " - " + o1.phylum;
				String s2 = o2.regulatorName + " - " + o2.phylum;
				return s1.compareTo(s2);
			}
		});
	}	
	
	public static void sortByPhylumRegnameTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.phylum + "-" + o1.regulatorName + " - " + o1.taxonName;
				String s2 = o2.phylum + "-" + o2.regulatorName + " - " + o2.taxonName;
				return s1.compareTo(s2);
			}
		});
	}		
	
	public static void sortByRegnamePhylumTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.regulatorName + " - " + o1.phylum + " - " + o1.getTaxonName();
				String s2 = o2.regulatorName + " - " + o2.phylum + " - " + o2.getTaxonName();
				return s1.compareTo(s2);
			}
		});
	}		
	
	public static void sortByRegnameTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.regulatorName + " - " + o1.getTaxonName();
				String s2 = o2.regulatorName + " - " + o2.getTaxonName();
				return s1.compareTo(s2);
			}
		});
	}
	
	public static void sortByPathwayRegnameTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.pathway + " - " + o1.regulatorName + " - " + o1.getTaxonName();
				String s2 = o2.pathway + " - " + o2.regulatorName + " - " + o2.getTaxonName();
				if(o1.pathway == null || o1.pathway.length() == 0) s1 = "XXXXX";
				if(o2.pathway == null || o2.pathway.length() == 0) s2 = "XXXXX";
				
				return s1.compareTo(s2);
			}
		});
	}

	public static void sortByEffectorRegnameTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.effector + " - " + o1.regulatorName + " - " + o1.getTaxonName();
				String s2 = o2.effector + " - " + o2.regulatorName + " - " + o2.getTaxonName();
				if(o1.effector == null || o1.effector.length() == 0) s1 = "XXXXX";
				if(o2.effector == null || o2.effector.length() == 0) s2 = "XXXXX";
				
				return s1.compareTo(s2);
			}
		});
	}
	 
	public static void sortByFamilyRegnameTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.getRegulatorFamily() + " - " + o1.regulatorName+ " - " + o1.getTaxonName();
				String s2 = o2.getRegulatorFamily() + " - " + o2.regulatorName+ " - " + o2.getTaxonName();
				return s1.compareTo(s2);
			}
		});
	}

	public static void sortByGenomesCount(List<RegulogStatDTO> prjs, final boolean asc)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				Integer i1 = o1.genomeCount.intValue();
				Integer i2 = o2.genomeCount.intValue();
				return (asc ? i1.compareTo(i2) : i2.compareTo(i1));
			}
		});
	}
	
	public static void sortBySitesCount(List<RegulogStatDTO> prjs, final boolean asc)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				Integer i1 = o1.siteCount.intValue();
				Integer i2 = o2.siteCount.intValue();
				return (asc ? i1.compareTo(i2) : i2.compareTo(i1));
			}
		});
	}
	
	
	public static void sortByPhylumTaxonomyRegnameName(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = o1.getPhylum() + " - " + o1.getTaxonName() + " - " + o1.regulatorName ;
				String s2 = o2.getPhylum() + " - " + o2.getTaxonName() + " - " + o2.regulatorName;
				return s1.compareTo(s2);
			}
		});
	}	
		
	public static void sortByRegtypePhylumRegnameTaxonomy(List<RegulogStatDTO> prjs)
	{
		Collections.sort(prjs, new Comparator<RegulogStatDTO>(){

			public int compare(RegulogStatDTO o1, RegulogStatDTO o2) {
				String s1 = "" + o1.getRegulationTypeTermId() + " - " + o1.getPhylum() + " - " + o1.getRegulatorName() + " - " + o1.getTaxonName();
				String s2 = "" + o2.getRegulationTypeTermId() + " - " + o2.getPhylum() + " - " + o2.getRegulatorName()+ " - " + o2.getTaxonName();
				return s1.compareTo(s2);
			}
		});
	}	
	
	public String getName()
	{
		return regulatorName + " - " + getTaxonName();
	}
	
	public Integer getRegulogId() {
		return regulogId.intValue();
	}
	public void setRegulogId(Integer regulogId) {
		this.regulogId = regulogId;
	}
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}

	public String getEffector() {
		return effector;
	}	
	
	public void setEffector(String effector) {
		this.effector = effector;
	}
	public String getRegulatorFamily() {
		return regulationTypeTermId == Term.TERM_REGULATION_TYPE_TF ? tfFamily : rnaFamily;
	}
	public BigInteger getGenomeCount() {
		return genomeCount;
	}
	public void setGenomeCount(BigInteger genomeCount) {
		this.genomeCount = genomeCount;
	}
	public BigInteger getSiteCount() {
		return siteCount;
	}
	public void setSiteCount(BigInteger siteCount) {
		this.siteCount = siteCount;
	}


	public void setPathway(String pathway) {
		this.pathway = pathway;
	}

	public String getPathway() {
		return pathway;
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

	public void setRegulationTypeTermId(Integer regulationTypeTermId) {
		this.regulationTypeTermId = regulationTypeTermId;
	}


	public Integer getRegulationTypeTermId() {
		return regulationTypeTermId;
	}

	public void setRegulonCount(BigInteger regulonCount) {
		this.regulonCount = regulonCount;
	}

	public BigInteger getRegulonCount() {
		return regulonCount;
	}	
}
