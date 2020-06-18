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
@Table(name = "Riboswitch")
public class Riboswitch extends DictionaryItem{
	@Id
	@GeneratedValue
	@Column(name = "riboswitchId")
	private Integer id;
	private String name;
	private String rfamId;
	
	@Column(columnDefinition="Text")	
	private String description;
	private String imageRef;
	
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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

	public String getImageRef() {
		return imageRef;
	}

}
