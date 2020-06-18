package com.lbl.regprecise.ent;

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
@Table(name = "Article")
public class Article extends IdObject{
	@Id
	@GeneratedValue
	@Column(name="articleId")
	Integer id;
		
	String title;
	String authors;
	String year;
	String jornal;
	String pages;
	Integer pmid;


	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getJornal() {
		return jornal;
	}
	public void setJornal(String jornal) {
		this.jornal = jornal;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}
	public Integer getPmid() {
		return pmid;
	}
	

}
