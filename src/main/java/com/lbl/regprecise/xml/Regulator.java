package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class Regulator {
	
	private Integer regulonId;
	private String name;
	private String locusTag;
	private Integer vimssId;
	private String regulatorFamily;

	public Integer getRegulonId() {
		return regulonId;
	}
	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}


	public String getLocusTag() {
		return locusTag;
	}
	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}
	public String getRegulatorFamily() {
		return regulatorFamily;
	}
	public void setRegulatorFamily(String regulatorFamily) {
		this.regulatorFamily = regulatorFamily;
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

	
}
