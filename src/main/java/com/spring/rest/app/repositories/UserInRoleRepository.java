package com.spring.rest.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.rest.app.entities.User;
import com.spring.rest.app.entities.UserInRole;

@Repository
public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer>{

	@Query("SELECT u.user FROM UserInRole u where u.role.name=?1")
	List<User> findUserByRoleName(String roleName);

	List<UserInRole> findByUser(User user);

}
