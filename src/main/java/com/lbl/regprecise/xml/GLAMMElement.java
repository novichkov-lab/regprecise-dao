package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class GLAMMElement {
	
	private Integer vimssId;
	private String ecNumber;
	private String groupId;
	private String groupName;
	private String genomeName;
	private Integer taxonomyId;
	private Float strength;
	private String callbackURL;
	
	public Integer getVimssId() {
		return vimssId;
	}
	public void setVimssId(Integer vimssId) {
		this.vimssId = vimssId;
	}
	public String getEcNumber() {
		return ecNumber;
	}
	public void setEcNumber(String ecNumber) {
		this.ecNumber = ecNumber;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCallbackURL() {
		return callbackURL;
	}
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}
	public void setGenomeName(String genomeName) {
		this.genomeName = genomeName;
	}
	public String getGenomeName() {
		return genomeName;
	}
	public void setTaxonomyId(Integer taxonomyId) {
		this.taxonomyId = taxonomyId;
	}
	public Integer getTaxonomyId() {
		return taxonomyId;
	}
	public void setStrength(Float strength) {
		this.strength = strength;
	}
	public Float getStrength() {
		return strength;
	}
}
