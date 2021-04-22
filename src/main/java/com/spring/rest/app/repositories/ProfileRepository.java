package com.spring.rest.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.rest.app.entities.Profile;

@Repository

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	
	@Query("Select p from Profile p where p.user.id=?1 and p.id=?2")
	Optional<Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);

}
