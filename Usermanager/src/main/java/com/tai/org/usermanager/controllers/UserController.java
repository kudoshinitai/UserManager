package com.tai.org.usermanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tai.org.usermanager.dto.UserDTO;
import com.tai.org.usermanager.dto.UserFilter;
import com.tai.org.usermanager.services.IUserService;

@Controller
@RequestMapping (path = "/demo")
public class UserController {

	@Autowired
	private IUserService userService;
	

	
	@PostMapping(path = "/user/add")
	public @ResponseBody UserDTO addNewUser (@RequestBody UserDTO userDTO) {
//		UserEntity n = new UserEntity();
//		n.setEmail(email);
//		n.setFirstName(firstName);
//		n.setLastName(lastName);
//		n.setPhone(phone);
//		userRepository.save(n);
		UserDTO addedUser = userService.addUser(userDTO);
		return addedUser;
	}
	
	@GetMapping(path = "/users")
	public @ResponseBody Iterable<UserDTO> getAllUsers(){
		return userService.findAll();
	}
	

	
	@GetMapping(path = "/user/search")
	public @ResponseBody List<UserDTO> search(@RequestParam (name = "firstName") String firstName){
		List<UserDTO> users = userService.search(firstName);  
		return users;
	}
	
	@DeleteMapping(path = "/user/delete/{id}")
	public @ResponseBody String delete (@PathVariable ("id") Integer id) {
		boolean isDeleted = userService.deleteUser(id);
		if(isDeleted) {
			return "User with id :" + id + " is deleted successfully";
		}
		return "User with id :" + id + "is not exist";
	}
	
	@PutMapping(path = "/user/update")
	public @ResponseBody UserDTO userDTO (@RequestBody UserDTO userDTO) {
		UserDTO updatedUser = userService.updateUser(userDTO);
		return updatedUser;
	}
	
	@PostMapping(path = "/user/findbyname")
	public @ResponseBody List<UserDTO> userDTOs (@RequestBody UserFilter userFilter) {
		List<UserDTO> foundUsers = userService.findByName(userFilter);
		return foundUsers;
	}
	
}
