package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class Gene {

	private Integer regulonId;	
	private String name;
	private String locusTag;
	private Integer vimssId;	
	private String  function;
	
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public void setVimssId(Integer vimssId) {
		this.vimssId = vimssId;
	}
	public Integer getVimssId() {
		return vimssId;
	}	
}
