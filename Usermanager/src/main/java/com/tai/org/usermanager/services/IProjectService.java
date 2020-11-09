package com.tai.org.usermanager.services;

import java.util.List;
import java.util.Set;

import com.tai.org.usermanager.dto.ProjectDTO;
import com.tai.org.usermanager.enties.ProjectEntity;

public interface IProjectService {

	public List<ProjectDTO> findAll();
	ProjectDTO addProject(ProjectDTO projectDTO);
	ProjectDTO updateProject(ProjectDTO projectDTO);
	public boolean deleteProject(int id);
	public Set<ProjectEntity> findByProjectIdIn(Set<Integer> projectIds);
}
