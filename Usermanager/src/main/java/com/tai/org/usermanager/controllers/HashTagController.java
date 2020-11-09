package com.tai.org.usermanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tai.org.usermanager.dto.HashTagDTO;
import com.tai.org.usermanager.services.IHashTagService;

@Controller
@RequestMapping (path = "/demo")
public class HashTagController {
	@Autowired
	private IHashTagService hashTagService;
	
	@GetMapping(path = "/hashtags")
	public @ResponseBody Iterable<HashTagDTO> getAllHashTags(){
		return hashTagService.findAll();
	}
	
	@PostMapping(path = "/hashtag/add")
	public @ResponseBody HashTagDTO addNewHashTag(@RequestBody HashTagDTO hashTagDTO) {
		HashTagDTO addedHashTag = hashTagService.addHashTag(hashTagDTO); 
		return addedHashTag;
	}
	
	@DeleteMapping(path = "/hashtag/delete")
	public @ResponseBody String deleteHashTag(@RequestParam int id) {
		boolean isDeleteHashTag = hashTagService.deleteHashTag(id);
		if(isDeleteHashTag) {
			return "HashTag with id " + id + "is deleted successfull";
		}
		return "Hashtag with id" + id + "is not exist";
	}
	
	@PutMapping (path = "/hashtag/countuser")
	public @ResponseBody HashTagDTO countUser(@RequestParam int hashTagId) {
		HashTagDTO responseHashTagDTO = hashTagService.countUser(hashTagId);
		return responseHashTagDTO;
		
	}
	
	@GetMapping(path = "/hashtag/countalluser")
	public @ResponseBody List<HashTagDTO> countAllUser(){
		return hashTagService.countAllUser();
	}
	
	@GetMapping(path = "/hashtag/findmaxuser")
	public @ResponseBody List<HashTagDTO> findMaxUser(){
		return hashTagService.findMaxUserInHashTag();
	}
}
