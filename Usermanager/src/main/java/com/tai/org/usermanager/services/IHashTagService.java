package com.tai.org.usermanager.services;
import java.util.List;
import java.util.Set;

import com.tai.org.usermanager.dto.HashTagDTO;
import com.tai.org.usermanager.enties.HashTagEntity;
public interface IHashTagService {

	public List<HashTagDTO> findAll();

	public HashTagDTO addHashTag(HashTagDTO hashTagDTO);
	
	boolean deleteHashTag(int id);
	
	public Set<HashTagEntity> findByHashTagIdIn (Set<Integer> ids); 
	
	public String findById(int id);
	
	public HashTagDTO countUser (int hashTagId);
	
	public List<HashTagDTO> countAllUser();
	
	public List<HashTagDTO> findMaxUserInHashTag();
}
