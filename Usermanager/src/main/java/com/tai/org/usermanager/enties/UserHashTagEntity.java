package com.tai.org.usermanager.enties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(UserHashTagId.class)

@Table(name = "user_hashtag")
public class UserHashTagEntity {

	@Id
	@Column (name = "USER_ID")
	private int userId;
	
	@Id
	@Column(name = "HASHTAG_ID")
	private int hashTagId;
	
	@Column(name = "TIME")
	private Date time;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHashTagId() {
		return hashTagId;
	}

	public void setHashTagId(int hashTagId) {
		this.hashTagId = hashTagId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
