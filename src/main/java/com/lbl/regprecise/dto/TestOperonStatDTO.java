package com.lbl.regprecise.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class TestOperonStatDTO {
	private int operonsWithSiteCount;
	private Integer regulatedOperonId;
	private String regulatedOperonName;	
@XmlElement
	private List<TestGeneStatDTO> genes;

	public void setOperonsWithSiteCount(int operonsWithSiteCount) {
		this.operonsWithSiteCount = operonsWithSiteCount;
	}

	public int getOperonsWithSiteCount() {
		return operonsWithSiteCount;
	}

	public void setRegulatedOperonId(Integer regulatedOperonId) {
		this.regulatedOperonId = regulatedOperonId;
	}

	public Integer getRegulatedOperonId() {
		return regulatedOperonId;
	}

	public void setRegulatedOperonName(String regulatedOperonName) {
		this.regulatedOperonName = regulatedOperonName;
	}

	public String getRegulatedOperonName() {
		return regulatedOperonName;
	}

	public List<TestGeneStatDTO> getGenes() {
		if (genes == null) {
			genes = new ArrayList<TestGeneStatDTO>();
	    }
	    return this.genes;
	}
}
