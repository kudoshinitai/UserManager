package com.tai.org.usermanager.services;

import java.util.List;

import com.tai.org.usermanager.dto.UserDTO;
import com.tai.org.usermanager.dto.UserFilter;

public interface IUserService {

	public List<UserDTO> findAll();

	List<UserDTO> search(String firstName);

	boolean deleteUser(int id);

	UserDTO addUser(UserDTO userDTO);

	UserDTO updateUser(UserDTO userDTO);

	List<UserDTO> findByName(UserFilter userFilter);

}
