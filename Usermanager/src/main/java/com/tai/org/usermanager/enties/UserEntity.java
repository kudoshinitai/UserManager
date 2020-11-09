package com.tai.org.usermanager.enties;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table (name = "USER")
public class UserEntity {

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_HASHTAG",
			   joinColumns = {@JoinColumn(name = "USER_ID")},
			   inverseJoinColumns = {@JoinColumn(name = "HASHTAG_ID")})
	
	private Set<HashTagEntity> hashTags = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_PROJECT",
	joinColumns = {@JoinColumn(name="USER_ID")},
	inverseJoinColumns = {@JoinColumn(name = "PROJECT_ID")})
	
	private Set<ProjectEntity> projects = new HashSet<>();	
	
	public Set<ProjectEntity> getProjects() {
		return projects;
	}

	public void setProjects(Set<ProjectEntity> projects) {
		this.projects = projects;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "USER_ID")
	private Integer id;
	
	@Column (name = "EMAIL")
	private String email;

	@Column (name = "FIRST_NAME")
	private String firstName;
	
	@Column (name = "LAST_NAME")
	private String lastName;
	
	@Column (name = "PHONE")
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<HashTagEntity> getHashTags() {
		return hashTags;
	}

	public void setHashTags(Set<HashTagEntity> hashTags) {
		this.hashTags = hashTags;
	}	
}
