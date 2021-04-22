package com.spring.rest.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.app.entities.Role;

@Repository

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
