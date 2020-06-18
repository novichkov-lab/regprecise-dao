package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "User2Collection")
public class CollectionUserRole {
	@Id
	@GeneratedValue
	@Column(name = "collection2UserId")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="collectionId")
	private Collection collection;	
	
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

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public Collection getCollection() {
		return collection;
	}	
	
}
