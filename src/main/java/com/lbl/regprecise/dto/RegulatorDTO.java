package com.lbl.regprecise.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class RegulatorDTO {

	private Integer regulatorId;
	private Integer vimssId;
	private String locusTag;
			
	public RegulatorDTO(){}
	
	public RegulatorDTO(Integer regulatorId, Integer vimssId, String locusTag) {
		super();
		this.regulatorId = regulatorId;
		this.vimssId = vimssId;
		this.locusTag = locusTag;
	}
	public Integer getRegulatorId() {
		return regulatorId;
	}
	public void setRegulatorId(Integer regulatorId) {
		this.regulatorId = regulatorId;
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
