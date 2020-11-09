package com.tai.org.usermanager.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tai.org.usermanager.enties.UserHashTagEntity;
import com.tai.org.usermanager.enties.UserHashTagId;
import com.tai.org.usermanager.projections.TagUserTotal;

public interface UserHashTagRepository extends CrudRepository<UserHashTagEntity, UserHashTagId> {

	@Query("SELECT COUNT(UH.userId) FROM UserHashTagEntity UH WHERE UH.hashTagId = :hashTagId")
	long countUser(@Param("hashTagId")int hashTagId);
	
	@Query("SELECT UH.hashTagId as hashTagId, COUNT(UH.userId) as totalUser FROM UserHashTagEntity UH where UH.hashTagId in :hashTagIds group by UH.hashTagId")
	List<TagUserTotal> countAllUser (@Param("hashTagIds") List<Integer> hashTagIds);
	
	
	@Query("SELECT UH.hashTagId as hashTagId, count(UH.userId) as maxUser "
			+ "FROM UserHashTagEntity UH group by UH.hashTagId "
			+ "having count(UH.userId) >= all (select count(UH2.userId) from UserHashTagEntity UH2 group by UH2.hashTagId)")
	List<TagUserTotal> findMaxUserInHashTag ();
}
