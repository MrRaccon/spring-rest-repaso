package com.spring.rest.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.rest.app.entities.Role;
import com.spring.rest.app.entities.User;
import com.spring.rest.app.repositories.RoleRepository;
import com.spring.rest.app.repositories.UserInRoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;

	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	public Role updateRole(Role role, Integer roleId) {
		Optional<Role> roleExist = roleRepository.findById(roleId);
		if (roleExist.isPresent()) {
			return roleRepository.save(role);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encuentra el role indicado");
		}

	}

	public void delete(Integer roleId) {
			Optional<Role> role=roleRepository.findById(roleId);
			if (role.isPresent()) {
				 roleRepository.delete(role.get());

			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encuentra el role indicado");
			}
	}

	public List<User> getRolesByName(String roleName) {
		return userInRoleRepository.findUserByRoleName(roleName);
	}
}
