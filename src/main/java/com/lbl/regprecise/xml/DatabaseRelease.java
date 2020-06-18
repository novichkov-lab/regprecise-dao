package com.lbl.regprecise.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Elena Novichkova
 *
 */
@XmlRootElement
public class DatabaseRelease {

	private Integer majorVersion;
	private Integer mionrVersion;
	private String releaseDate;
	
	public Integer getMajorVersion() {
		return majorVersion;
	}
	public void setMajorVersion(Integer majorVersion) {
		this.majorVersion = majorVersion;
	}
	public Integer getMionrVersion() {
		return mionrVersion;
	}
	public void setMionrVersion(Integer mionrVersion) {
		this.mionrVersion = mionrVersion;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}	
}
