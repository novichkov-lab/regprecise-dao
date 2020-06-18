package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Elena Novichkova
 *
 */
@Entity
@Table(name = "User2Project")
public class ProjectUserRole {
	@Id
	@GeneratedValue
	@Column(name = "user2projectId")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="projectId")
	private Project project;	
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;	
	
	@ManyToOne
	@JoinColumn(name="userRoleId")
	private UserRole role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
}
