package com.lbl.regprecise.dto;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Novichkov
 *
 */
@XmlRootElement
public class RiboswitchRawStatDTO {
		private Integer riboswitchId;
		private String name;
		private String rfamId;
		private BigInteger siteCount;
		private Float minScore;
		
		public Integer getRiboswitchId() {
			return riboswitchId;
		}
		public void setRiboswitchId(Integer riboswitchId) {
			this.riboswitchId = riboswitchId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRfamId() {
			return rfamId;
		}
		public void setRfamId(String rfamId) {
			this.rfamId = rfamId;
		}
		public BigInteger getSiteCount() {
			return siteCount;
		}
		public void setSiteCount(BigInteger siteCount) {
			this.siteCount = siteCount;
		}
		public void setMinScore(Float minScore) {
			this.minScore = minScore;
		}
		public Float getMinScore() {
			return minScore;
		}
}
