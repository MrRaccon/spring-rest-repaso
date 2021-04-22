package com.spring.rest.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.app.entities.User;
import com.spring.rest.app.services.UserService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	@Timed("get.users")
	public ResponseEntity<Page<User>> getUsers(@RequestParam(required=false,value="page",defaultValue = "0")int page,@RequestParam(required=false,value="size",defaultValue = "1000")int size){
		return new ResponseEntity<>(userService.getUsers(page, size),HttpStatus.OK);
	}
	
	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUserNames(@RequestParam(required=false,value="page",defaultValue = "0")int page,@RequestParam(required=false,value="size",defaultValue = "1000")int size){
		return new ResponseEntity<>(userService.getUsernNames(page, size),HttpStatus.OK);
	}
	
	@ApiOperation(value="Regresar el usuario buscado por el id",response=User.class)
	@ApiResponses(value= {@ApiResponse(code =200,message="Regresa al usuario exitosamente")})
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId")Integer userId){
		return new ResponseEntity<User>(userService.findById(userId),HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByName(@PathVariable("username")String userName){
		return new ResponseEntity<User>(userService.findUserByUserName(userName),HttpStatus.OK);
	}
	
	@CacheEvict("users")
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUserByUserName(@PathVariable("username") String userName) {
		userService.deleteUserByUserName(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticateUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.authenticate(user),HttpStatus.OK);
	}
	
	

}
