package com.spring.rest.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.app.entities.Role;
import com.spring.rest.app.entities.User;
import com.spring.rest.app.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	
	
	@GetMapping
	public ResponseEntity<List<Role>> getRoles(){
		return new ResponseEntity<List<Role>>(roleService.getRoles(),HttpStatus.OK);
	}
	
	@GetMapping("/rolename/{rolename}")
	public ResponseEntity<List<User>> getRoles(@PathVariable("rolename") String roleName){
		return new ResponseEntity<List<User>>(roleService.getRolesByName(roleName),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		return new ResponseEntity<Role>(roleService.createRole(role),HttpStatus.OK);

	}
	
	@PutMapping("/{roleId}")
	public ResponseEntity<Role> updateRole(@RequestBody Role role,@PathVariable("roleId") Integer roleId){
		return new ResponseEntity<Role>(roleService.updateRole(role,roleId),HttpStatus.OK);

	}
	
	
	@DeleteMapping("/{roleId}")
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer roleId){
		roleService.delete(roleId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
}
