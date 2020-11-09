package com.tai.org.usermanager.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.tai.org.usermanager.enties.ProjectEntity;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Integer> {
	
	List<ProjectEntity> findAll();
	Set<ProjectEntity> findByIdIn(Set<Integer> projectIds);
}
