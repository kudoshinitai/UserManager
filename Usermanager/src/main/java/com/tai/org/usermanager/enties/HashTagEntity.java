package com.tai.org.usermanager.enties;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "HASHTAG")

public class HashTagEntity {

	//@ManyToMany(mappedBy = "hashTags")
	
	//private Set<UserEntity> users = new HashSet<>();
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "HASHTAG_ID")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int hashtagId) {
		this.id = hashtagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column (name = "NAME")
	private String name;
}
