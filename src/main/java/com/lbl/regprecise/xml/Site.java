package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class Site {

	private Integer regulonId;
	private String sequence;
	private Integer position;
	private Float score;
	private String geneLocusTag;
	private Integer geneVIMSSId;	

		
	public Integer getRegulonId() {
		return regulonId;
	}
	public void setRegulonId(Integer regulonId) {
		this.regulonId = regulonId;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}

	public void setGeneLocusTag(String geneLocusTag) {
		this.geneLocusTag = geneLocusTag;
	}
	public String getGeneLocusTag() {
		return geneLocusTag;
	}
	public void setGeneVIMSSId(Integer geneVIMSSId) {
		this.geneVIMSSId = geneVIMSSId;
	}
	public Integer getGeneVIMSSId() {
		return geneVIMSSId;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	
}
