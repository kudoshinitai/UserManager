package com.tai.org.usermanager.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tai.org.usermanager.enties.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	List<UserEntity> findAll();

	List<UserEntity> findByFirstNameContaining(String firstName);

	void deleteById(String id);

	List<UserEntity> findAll(Sort sort);

	List<UserEntity> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Sort sort);
	
}
