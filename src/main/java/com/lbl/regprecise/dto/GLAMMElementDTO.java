package com.lbl.regprecise.dto;

/**
 * @author Pavel Novichkov
 *
 */
public class GLAMMElementDTO {
	
	public final static String TYPE_TAXONOMY = "taxonomy";
	public final static String TYPE_GENOME = "genome";
	public final static String TYPE_REGULOG = "regulog";
	public final static String TYPE_REGULON = "regulon";
	public final static String TYPE_COLLECTION_TAXGROUP = "taxGroup";
	public final static String TYPE_COLLECTION_TF = "tf";
	public final static String TYPE_COLLECTION_TFFAM = "tfFam";
	public final static String TYPE_COLLECTION_RNAFAM = "rnaFam";
	public final static String TYPE_COLLECTION_PATHWAY = "pathway";
	public final static String TYPE_COLLECTION_EFFECTOR = "effector";
	
	public static boolean isValidCollectionType(String collectionType)
	{
		if(collectionType.equals(TYPE_COLLECTION_TAXGROUP)) return true;
		if(collectionType.equals(TYPE_COLLECTION_TF)) return true;
		if(collectionType.equals(TYPE_COLLECTION_TFFAM)) return true;
		if(collectionType.equals(TYPE_COLLECTION_RNAFAM)) return true;
		if(collectionType.equals(TYPE_COLLECTION_PATHWAY)) return true;
		if(collectionType.equals(TYPE_COLLECTION_EFFECTOR)) return true;
		return false;
	}	
	
	
	private Integer regulonId;
	private String regulatorName;
	private Integer genomeMoId;
	private String genomeName;
	private Integer geneMoId;
	private Integer orthologId;	
	
	public String getRegulatorName() {
		return regulatorName;
	}
	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}
	public Integer getGenomeMoId() {
		return genomeMoId;
	}
	public void setGenomeMoId(Integer genomeMoId) {
		this.genomeMoId = genomeMoId;
	}
	public String getGenomeName() {
		return genomeName;
	}
	public void setGenomeName(String genomeName) {
		this.genomeName = genomeName;
	}
	public Integer getGeneMoId() {
		return geneMoId;
	}
	public void setGeneMoId(Integer geneMoId) {
		this.geneMoId = geneMoId;
	}
	public Integer getOrthologId() {
		return orthologId;
	}
	public void setOrthologId(Integer orthologId) {
		this.orthologId = orthologId;
	}
	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}
	public Integer getRegulonId() {
		return regulonId;
	}
	
	
	
}
