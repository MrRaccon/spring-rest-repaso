package com.spring.rest.app.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.rest.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUserName(String userName);
	
	public Optional<User> findByUserNameAndPassword(String userName,String password);
	/*
	 * Esto no es SQL*
	 */
	
	@Query("Select u.userName from User u")
	public Page<String> getNames(Pageable pageable);

}
