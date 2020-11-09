package com.tai.org.usermanager.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class UserDTO {

	@JsonProperty("ID")
	private Integer id;

	@JsonProperty("FIRST_NAME")
	private String firstName;

	@JsonProperty("LAST_NAME")
	private String lastName;

	@JsonProperty("EMAIL")
	private String email;

	@JsonProperty("PHONE")
	private String phone;
	
	@JsonProperty("NAME")
	private String name;
	
	@JsonProperty("HASHTAG_ID")
	//Request
	private Set<Integer> hashTagIds;
	
	
	@JsonProperty("HASHTAG")
	//Response
	private Set<HashTagDTO> hashTags;
	
	
	@JsonProperty("PROJECT_ID")
	//Request
	private Set<Integer> projectIds;
	
	
	@JsonProperty("PROJECT")
	//Respond
	private Set<ProjectDTO> projects;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Integer> getHashTagIds() {
		return hashTagIds;
	}

	public void setHashTagIds(Set<Integer> hashTagIds) {
		this.hashTagIds = hashTagIds;
	}

	public Set<HashTagDTO> getHashTags() {
		return hashTags;
	}

	public void setHashTags(Set<HashTagDTO> hashTags) {
		this.hashTags = hashTags;
	}

	public Set<ProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(Set<ProjectDTO> projects) {
		this.projects = projects;
	}

	public Set<Integer> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(Set<Integer> projectIds) {
		this.projectIds = projectIds;
	}
	
}
