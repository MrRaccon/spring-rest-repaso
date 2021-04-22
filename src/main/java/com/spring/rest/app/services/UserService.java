package com.spring.rest.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.rest.app.entities.User;
import com.spring.rest.app.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Page<User> getUsers(int page,int size){
		return userRepository.findAll(PageRequest.of(page, size));
		
	}


	public User findById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "que paso mi perro, ese usuario no existe"));
	}
	
	@Cacheable("users")
	public User findUserByUserName(String userName) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRepository.findByUserName(userName).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "que paso mi perro, ese usuario no existe"));
	}


	public User authenticate(User user) {
		return userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "que paso mi perro, ese usuario no existe"));
	}


	public Page<String> getUsernNames(int page, int size) {
		return userRepository.getNames(PageRequest.of(page, size));
	}


	public void deleteUserByUserName(String userName) {
		User user =findUserByUserName(userName);
		userRepository.delete(user);
		
	}
	
	

}
