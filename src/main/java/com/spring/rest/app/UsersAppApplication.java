package com.spring.rest.app;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.spring.rest.app.entities.Role;
import com.spring.rest.app.entities.User;
import com.spring.rest.app.entities.UserInRole;
import com.spring.rest.app.repositories.RoleRepository;
import com.spring.rest.app.repositories.UserInRoleRepository;
import com.spring.rest.app.repositories.UserRepository;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner {

	@Autowired
	private Faker faker;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;

	
	private static final Logger log = LoggerFactory.getLogger(UsersAppApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(UsersAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Role[] roles = { new Role("ADMIN"), new Role("SUPPORT"), new Role("USER") };
		for (Role role : roles) {
			roleRepository.save(role);
		}

		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setUserName(faker.name().username());
//			user.setPassword(faker.dragonBall().character());
			user.setPassword("123456789");

//			user.setProfile(null);
		User created=	userRepository.save(user);
			
			UserInRole userInRole = new UserInRole(created,roles[new Random().nextInt(3)]);
          UserInRole createUserRole=  userInRoleRepository.save(userInRole);
          log.info("Usuario creado : {} con rol {}",createUserRole.getUser().getUserName(),createUserRole.getRole().getName() );
		}

	}

}
