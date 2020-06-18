package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class RegulogCollection {

	public final static String COLLECTION_TYPE_TAXGROUP = "taxGroup";
	public final static String COLLECTION_TYPE_TF = "tf";
	public final static String COLLECTION_TYPE_TFFAM = "tfFam";
	public final static String COLLECTION_TYPE_RNAFAM = "rnaFam";
	public final static String COLLECTION_TYPE_PATHWAY = "pathway";
	public final static String COLLECTION_TYPE_EFFECTOR = "effector";
	
	public static boolean isValidCollectionType(String collectionType)
	{
		if(collectionType.equals(COLLECTION_TYPE_TAXGROUP)) return true;
		if(collectionType.equals(COLLECTION_TYPE_TF)) return true;
		if(collectionType.equals(COLLECTION_TYPE_TFFAM)) return true;
		if(collectionType.equals(COLLECTION_TYPE_RNAFAM)) return true;
		if(collectionType.equals(COLLECTION_TYPE_PATHWAY)) return true;
		if(collectionType.equals(COLLECTION_TYPE_EFFECTOR)) return true;
		return false;
	}
	
	
	private String collectionType;
	private Integer collectionId;
	
	private String className;
	private String name;

	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
