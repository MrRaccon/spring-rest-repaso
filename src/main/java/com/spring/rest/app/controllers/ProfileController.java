package com.spring.rest.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.app.entities.Profile;
import com.spring.rest.app.services.ProfileService;

@RestController
@RequestMapping("/users/{userId}/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;

	@PostMapping
	public ResponseEntity<Profile> createProfile(@PathVariable("userId")Integer userId, @RequestBody Profile profile){
		
		return new ResponseEntity<Profile>(profileService.createProfile(userId, profile),HttpStatus.OK);
	}
	
	
	@GetMapping("/{profileId}")
	public ResponseEntity<Profile> getById(@PathVariable("userId") Integer userId,@PathVariable("profileId") Integer profileId){
		return new ResponseEntity<Profile>(profileService.getUserIdAndProfileId(userId,profileId),HttpStatus.ACCEPTED);
	}
	
}
