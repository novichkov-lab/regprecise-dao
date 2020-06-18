package com.lbl.regprecise.ent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "userId")
	Integer id;
	
	String name;
	String login;
	String pwd;
	Boolean isActive;
	
	
	@Column(columnDefinition="Text")
	String description;
	
	@ManyToOne
	@JoinColumn(name="userRoleId")
	UserRole role;
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "userId")
	})		
	private List<CollectionUserRole> collections = new ArrayList<CollectionUserRole>();
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "userId")
	})		
	private List<ProjectUserRole> projects = new ArrayList<ProjectUserRole>();

	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="User2Tool",
			joinColumns={@JoinColumn(name="userId")},
			inverseJoinColumns={@JoinColumn(name="toolId")}
	)
	private List<Tool> tools = new ArrayList<Tool>(); 		
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="User2Regulog",
			joinColumns={@JoinColumn(name="userId")},
			inverseJoinColumns={@JoinColumn(name="regulogId")}
	)
	private List<Regulog> regulogs = new ArrayList<Regulog>(); 	
		
	
	public User(){}
	
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public void setRegulogs(List<Regulog> regulogs) {
		this.regulogs = regulogs;
	}

	public List<Regulog> getRegulogs() {
		return regulogs;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setCollections(List<CollectionUserRole> collections) {
		this.collections = collections;
	}

	public List<CollectionUserRole> getCollections() {
		return collections;
	}
	
	public boolean validatePassword(String password) throws NoSuchAlgorithmException {
		return pwd.equals(encodePassword(password));
	}
	
	public static String encodePassword(String password) throws NoSuchAlgorithmException { 
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
         //convert the byte to hex format
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
		return hexString.toString();
    }

	public void setProjects(List<ProjectUserRole> projects) {
		this.projects = projects;
	}

	public List<ProjectUserRole> getProjects() {
		return projects;
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}

	public List<Tool> getTools() {
		return tools;
	}
	
	public boolean isPrivelagedUser()
	{
		return getRole().isAdministrator() || getRole().isRegPreciseCurator();
	}
}
