package com.tai.org.usermanager.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tai.org.usermanager.dto.HashTagDTO;
import com.tai.org.usermanager.dto.ProjectDTO;
import com.tai.org.usermanager.dto.UserDTO;
import com.tai.org.usermanager.dto.UserFilter;
import com.tai.org.usermanager.enties.HashTagEntity;
import com.tai.org.usermanager.enties.ProjectEntity;
import com.tai.org.usermanager.enties.UserEntity;

import com.tai.org.usermanager.repositories.UserRepository;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IHashTagService hashTagService;

	@Autowired
	private IProjectService projectService;

	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		List<UserEntity> users = userRepository.findAll();
		List<UserDTO> userResponses = new ArrayList<>();
		for (UserEntity user : users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDTO.setName(user.getFirstName() + " " + user.getLastName());
			userResponses.add(userDTO);
		}
		return userResponses;
	}

	// UserEntity lam viec voi bang USER va USER_HASHTAG
	@Override
	public UserDTO addUser(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);
		Set<Integer> hashTagIds = userDTO.getHashTagIds();
		if (CollectionUtils.isNotEmpty(hashTagIds)) {
			Set<HashTagEntity> hashTags = hashTagService.findByHashTagIdIn(hashTagIds);
			userEntity.setHashTags(hashTags);
		}
		UserEntity savedUser = userRepository.save(userEntity); // Luu userEntity xuong du lieu
		BeanUtils.copyProperties(savedUser, userDTO);
		return userDTO;
	}

	@Override
	public List<UserDTO> search(String firstName) {

		List<UserEntity> users = userRepository.findByFirstNameContaining(firstName);
		List<UserDTO> userDTOs = new ArrayList<>();
		for (UserEntity user : users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	public boolean deleteUser(int id) {
		Optional<UserEntity> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			userRepository.delete(userOptional.get());
			return true;
		}
		return false;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {

		Optional<UserEntity> userOptional = userRepository.findById(userDTO.getId());

		if (userOptional.isPresent()) {
			UserEntity userEntity = userOptional.get();
			BeanUtils.copyProperties(userDTO, userEntity);

			Set<HashTagDTO> hashTagDTOs = new HashSet<>();
			Set<Integer> hashTagIds = userDTO.getHashTagIds();

			Set<ProjectDTO> projectDTOs = new HashSet<>();
			Set<Integer> projectIds = userDTO.getProjectIds();

			if (CollectionUtils.isNotEmpty(hashTagIds)) {
				Set<HashTagEntity> hashTags = hashTagService.findByHashTagIdIn(hashTagIds);
				userEntity.setHashTags(hashTags);

				for (HashTagEntity hashTag : hashTags) {
					HashTagDTO hashTagDTO = new HashTagDTO();
					BeanUtils.copyProperties(hashTag, hashTagDTO);
					hashTagDTOs.add(hashTagDTO);
				}

			}

			if (CollectionUtils.isNotEmpty(projectIds)) {
				Set<ProjectEntity> projects = projectService.findByProjectIdIn(projectIds);
				userEntity.setProjects(projects);

				for (ProjectEntity project : projects) {
					ProjectDTO projectDTO = new ProjectDTO();
					BeanUtils.copyProperties(project, projectDTO);
					projectDTOs.add(projectDTO);
				}
			}

			UserDTO result = new UserDTO();
			UserEntity savedUser = userRepository.save(userEntity);
			BeanUtils.copyProperties(savedUser, result);
			result.setHashTags(hashTagDTOs);
			result.setProjects(projectDTOs);

			return result;
		}
		return null;
	}

	// Chuyen doi jsonproperty sang bien
	public String findNameByJson(String jsonProperty) {
		Field[] fields = UserDTO.class.getDeclaredFields(); // Tra ve mang field UserDTO
		for (Field field : fields) {
			if (field.isAnnotationPresent(JsonProperty.class)) {
				String annotationValue = field.getAnnotation(JsonProperty.class).value();
				if (StringUtils.equals(annotationValue, jsonProperty)) {
					return field.getName();
				}
			}
		}
		return StringUtils.EMPTY;
	}

	@Override
	public List<UserDTO> findByName(UserFilter userFilter) {
		List<UserDTO> userDTOs = new ArrayList<>();
		List<UserEntity> users;

		String order = userFilter.getOrder();
		String orderBy = userFilter.getOrderBy();
		String name = userFilter.getName();
		Sort sort = getSort(order, orderBy);

		users = userRepository.findByFirstNameContainingOrLastNameContaining(name, name, sort);

		for (UserEntity user : users) {
			/*
			 * String name = user.getFirstName() + user.getLastName();
			 * if(name.contains(userFilter.getName())) {
			 */
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDTO.setName(user.getFirstName() + " " + user.getLastName());
			userDTOs.add(userDTO);
		}
		// }
		return userDTOs;
	}

	private Sort getSort(String order, String orderBy) {
		Sort sort;
		if (order.equals("DESC")) {
			sort = Sort.by(Sort.Direction.DESC, findNameByJson(orderBy));
		} else {
			sort = Sort.by(Sort.Direction.ASC, findNameByJson(orderBy));

		}

		return sort;
	}

}
