package com.tai.org.usermanager.enties;

import java.io.Serializable;

public class UserHashTagId implements Serializable{

	/**
	 * 
	 */
	
	//Làm private key cho UserHashTagEntity (userId, hashTagId)
	//Phục vụ trong UserHashTagRepository.java
	private static final long serialVersionUID = -6248427446770195242L;

	private int userId;
	
	private int hashTagId;
}
