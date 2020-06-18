package com.lbl.regprecise.dto;

/**
 * @author Pavel Novichkov
 *
 */
public class KBaseGeneDTO {
	private String queryKBaseId;
	private String kbaseId;
	private String name;
	private String locusTag;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}
	public String getLocusTag() {
		return locusTag;
	}
	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}
	public String getKbaseId() {
		return kbaseId;
	}
	public void setQueryKBaseId(String queryKBaseId) {
		this.queryKBaseId = queryKBaseId;
	}
	public String getQueryKBaseId() {
		return queryKBaseId;
	}
}
