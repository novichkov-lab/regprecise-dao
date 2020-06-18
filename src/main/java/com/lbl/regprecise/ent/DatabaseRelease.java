package com.lbl.regprecise.ent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "DatabaseRelease")
public class DatabaseRelease {

	@Id
	@GeneratedValue
	@Column(name = "releaseId")
	private Integer id;

	private Integer majorVersion;
	private Integer mionrVersion;
	private Date releaseDate;	
	
	private Boolean isCurrent;	
	
	private String recentUpdateMonth;
	
	@Column(columnDefinition="Text")
	private String recentUpdateText;		
	
	private String upcommingUpdateMonth;
	
	@Column(columnDefinition="Text")
	private String upcommingUpdateText;		
	
	@Column(columnDefinition="Text")
	private String notes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getRecentUpdateMonth() {
		return recentUpdateMonth;
	}

	public void setRecentUpdateMonth(String recentUpdateMonth) {
		this.recentUpdateMonth = recentUpdateMonth;
	}

	public String getRecentUpdateText() {
		return recentUpdateText;
	}

	public void setRecentUpdateText(String recentUpdateText) {
		this.recentUpdateText = recentUpdateText;
	}

	public String getUpcommingUpdateMonth() {
		return upcommingUpdateMonth;
	}

	public void setUpcommingUpdateMonth(String upcommingUpdateMonth) {
		this.upcommingUpdateMonth = upcommingUpdateMonth;
	}

	public String getUpcommingUpdateText() {
		return upcommingUpdateText;
	}

	public void setUpcommingUpdateText(String upcommingUpdateText) {
		this.upcommingUpdateText = upcommingUpdateText;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}		
	
}
