package com.spring.rest.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.rest.app.entities.Address;
import com.spring.rest.app.entities.Profile;
import com.spring.rest.app.repositories.AddressRepository;
import com.spring.rest.app.repositories.ProfileRepository;

@Service
public class AddressService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private AddressRepository addressRepository;

	public List<Address> findAddressByUserIdAndProfileId(Integer userId, Integer profileId) {
		return addressRepository.findByProfileId(userId, profileId);
	}

	public Address createAddress(Address address, Integer userId, Integer profileId) {
		Optional<Profile> opProf = profileRepository.findByUserIdAndProfileId(userId, profileId);

		if (opProf.isPresent()) {
			address.setProfile(opProf.get());
			return addressRepository.save(address);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro el perfil ni usuario , mi perro");
		}
	}

}
