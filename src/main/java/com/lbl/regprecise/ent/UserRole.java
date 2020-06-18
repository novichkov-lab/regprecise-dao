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
@Table(name="UserRole")
public class UserRole {
	@Id
	@GeneratedValue
	@Column(name = "userRoleId")
	private Integer id;

	private String name;
	private Boolean isActive;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isAdministrator()
	{
		return name.equals("Administrator");
	}
	
	public boolean isAnnotator()
	{
		return name.equals("Annotator");
	}
	
	public boolean isRegPreciseCurator()
	{
		return name.equals("RegPrecise curator");
	}
	
	public boolean isCollectionCurator()
	{
		return name.equals("Collection curator");
	}
	
}
