package com.tai.org.usermanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tai.org.usermanager.dto.ProjectDTO;
import com.tai.org.usermanager.enties.ProjectEntity;
import com.tai.org.usermanager.repositories.ProjectRepository;

@Service("deAnService")
public class ProjectService implements IProjectService{

	@Autowired
	private ProjectRepository projectRepository;
	@Override
	public List<ProjectDTO> findAll() {
		List<ProjectDTO> deAnResponses = new ArrayList<>();
		List<ProjectEntity> deAns = projectRepository.findAll();
		for(ProjectEntity deAn : deAns) {
			ProjectDTO savedDeAn = new ProjectDTO();
			BeanUtils.copyProperties(deAn, savedDeAn);
			deAnResponses.add(savedDeAn);
		}
		return deAnResponses;
	}
	@Override
	public ProjectDTO addProject(ProjectDTO projectDTO) {
		ProjectEntity project = new ProjectEntity();
		BeanUtils.copyProperties(projectDTO, project);
		ProjectEntity savedProject = projectRepository.save(project);
		ProjectDTO projectResponse = new ProjectDTO();
		BeanUtils.copyProperties(savedProject, projectResponse);
		return projectResponse;
	}
	@Override
	public ProjectDTO updateProject(ProjectDTO projectDTO) {
		Optional<ProjectEntity> optionalProject = projectRepository.findById(projectDTO.getId());
		if(optionalProject.isPresent()) {
			BeanUtils.copyProperties(projectDTO, optionalProject.get());
			ProjectEntity updatedProject = projectRepository.save(optionalProject.get());
			ProjectDTO responseProject = new ProjectDTO();
			BeanUtils.copyProperties(updatedProject, responseProject);
			return responseProject;
		}
		return null;
	}
	@Override
	public boolean deleteProject(int id) {
		Optional<ProjectEntity> optionalProject = projectRepository.findById(id);
		if(optionalProject.isPresent()) {
			projectRepository.delete(optionalProject.get());
			return true;
		}
		
		return false;
	}
	@Override
	public Set<ProjectEntity> findByProjectIdIn(Set<Integer> projectIds) {
		// TODO Auto-generated method stub
		return projectRepository.findByIdIn(projectIds);
	}

	


	
}
