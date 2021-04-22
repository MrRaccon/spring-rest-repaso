package com.spring.rest.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.rest.app.entities.Profile;
import com.spring.rest.app.entities.User;
import com.spring.rest.app.repositories.ProfileRepository;
import com.spring.rest.app.repositories.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	public Profile createProfile(Integer userId, Profile profile) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			profile.setUser(user.get());
			return profileRepository.save(profile);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro el usuario , mi perro");
		}

	}

	public Profile getUserIdAndProfileId(Integer userId, Integer profileId) {
	return	 profileRepository.findByUserIdAndProfileId(userId, profileId).
			orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe ningun registro con los datos enviados"));
		


	}

}
