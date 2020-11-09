package com.tai.org.usermanager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class HashTagDTO {

	
	@JsonProperty("ID")
	private int id;
	
	@JsonProperty("NAME")
	private String name;
	
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	@JsonProperty("NAME2")
	private String name2;
	
    @JsonProperty("NUMBEROFUSER")
    private long numberOfUser;
    

	public long getNumberOfUser() {
		return numberOfUser;
	}

	public void setNumberOfUser(long numberOfUser) {
		this.numberOfUser = numberOfUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
