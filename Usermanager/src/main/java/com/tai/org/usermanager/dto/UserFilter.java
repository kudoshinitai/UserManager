package com.tai.org.usermanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFilter {

	@JsonProperty("ORDER")
	private String order;
	
	@JsonProperty("ORDERBY")
	private String orderBy;
	
	@JsonProperty("NAME")
	private String name;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
