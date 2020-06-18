package com.lbl.regprecise.dto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Pavel Novichkov
 *
 */

@XmlAccessorType
@XmlType(name = "testGeneStatDTO", propOrder = {    
    "geneId",
    "geneName",
    "geneLocusTag",
    "geneFunction"
})
public class TestGeneStatDTO {
	
	private Integer geneId;
	private String geneName;
	private String geneLocusTag;
	private String geneFunction;
		
	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}
	public Integer getGeneId() {
		return geneId;
	}
	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}
	public String getGeneName() {
		return geneName;
	}
	public void setGeneLocusTag(String geneLocusTag) {
		this.geneLocusTag = geneLocusTag;
	}
	public String getGeneLocusTag() {
		return geneLocusTag;
	}
	public void setGeneFunction(String geneFunction) {
		this.geneFunction = geneFunction;
	}
	public String getGeneFunction() {
		return geneFunction;
	}

}
