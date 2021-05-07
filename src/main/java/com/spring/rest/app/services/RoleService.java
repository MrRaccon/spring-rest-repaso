package com.spring.rest.app.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.app.entities.Role;
import com.spring.rest.app.entities.User;
import com.spring.rest.app.models.AuditDetails;
import com.spring.rest.app.models.BimbsSecurityRule;
import com.spring.rest.app.repositories.RoleRepository;
import com.spring.rest.app.repositories.UserInRoleRepository;

@Service
public class RoleService {
	
	

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	private ObjectMapper mapper= new ObjectMapper();
	private static final Logger log = LoggerFactory.getLogger(RoleService.class);


	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	public Role createRole(Role role) {
		Role roleCreated=roleRepository.save(role);
		String createdBy=SecurityContextHolder.getContext().getAuthentication().getName();
		String jsonString;
		try {
			jsonString = mapper.writeValueAsString(new AuditDetails(createdBy, role.getName()));
			kafkaTemplate.send("devs4j-topic",jsonString);

		} catch (JsonProcessingException e) {
			log.error("Ocurrio un error al convertir el objeto", e.getMessage());
		    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,String.format("Ocurrio un error : %s", e.getMessage()));
		}
		
		return roleCreated;
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
/*
 * sE DEBE DE USAR 'ROLE_' ANTES DE CUALQUER ROL, ESTO LO HACE AUTOMATICAMENTE SPRING, POR ESO 
 * PARA VALIDAR LO USAMOS ASI*
 */
//	@Secured({"ROLE_ADMIN"})
//	@RolesAllowed({"ROLE_ADMIN"})//NOTACION PROPIA DE JAVA JSR250
//	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")//autoriza entrada
//	@PostAuthorize("hasRole('ROLE_ADMIN') and 7 <8")//autoriza salida
	@BimbsSecurityRule
	public List<User> getRolesByName(String roleName) {
		log.info("Getting Roles by name :{} ",roleName);
		return userInRoleRepository.findUserByRoleName(roleName);
	}
}
