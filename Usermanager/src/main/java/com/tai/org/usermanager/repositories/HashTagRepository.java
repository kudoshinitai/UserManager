package com.tai.org.usermanager.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tai.org.usermanager.enties.HashTagEntity;

public interface HashTagRepository extends CrudRepository<HashTagEntity, Integer> {

	List<HashTagEntity> findAll();

	Set<HashTagEntity> findByIdIn(Set<Integer> ids);

	@Query(value = "SELECT COUNT(USER_ID) FROM USER_HASHTAG WHERE HASHTAG_ID = ?1", nativeQuery = true)
	Optional<Integer> myCustomQuery(int hashTagId);

}
