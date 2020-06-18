package com.lbl.regprecise.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class GeneDTO {
	
	private Integer geneId;
	private String name;
	private Integer vimssId;
	private String  locusTag;
	
	
	public GeneDTO(){}
	
	public GeneDTO(Integer geneId, Integer vimssId, String name, String locusTag) {
		super();
		this.geneId = geneId;
		this.vimssId = vimssId;
		this.name = name;
		this.locusTag = locusTag;
	}

	public Integer getGeneId() {
		return geneId;
	}
	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getVimssId() {
		return vimssId;
	}
	public void setVimssId(Integer vimssId) {
		this.vimssId = vimssId;
	}
	public String getLocusTag() {
		return locusTag;
	}
	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}
		
}
