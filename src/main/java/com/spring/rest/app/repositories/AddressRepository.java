package com.spring.rest.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.rest.app.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	
	@Query("SELECT a FROM Address a where a.profile.user.id=?1 and a.profile.id=?2")
	List<Address> findByProfileId(Integer userId,Integer profileId);
}
