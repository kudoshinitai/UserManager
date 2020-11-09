package com.tai.org.usermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tai.org.usermanager.dto.ProjectDTO;
import com.tai.org.usermanager.services.IProjectService;

@Controller
@RequestMapping(path = "/demo")
public class ProjectController {

	@Autowired
	private IProjectService projectService;
	
	@GetMapping(path = "/projects")
	public @ResponseBody Iterable<ProjectDTO> getAllDeAn(){
		return projectService.findAll();
	}
	
	@PostMapping(path = "/project/add")
	public @ResponseBody ProjectDTO addProject(@RequestBody ProjectDTO projectDTO) {
		
		return projectService.addProject(projectDTO);
	}
	
	@PostMapping(path = "/project/update")
	public @ResponseBody ProjectDTO updateProject (@RequestBody ProjectDTO projectDTO) {
		return projectService.updateProject(projectDTO);
	}
	
	@DeleteMapping(path = "/project/delete")
	public @ResponseBody String deleteProject(@RequestParam int id) {
		if(projectService.deleteProject(id)) {
			return "Project with id " + id + "is deleted successfully!";
		}
		return "Project with id " + id + "is not exist!";
	}
}
