package com.spring.rest.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest.app.entities.User;
import com.spring.rest.app.entities.UserInRole;
import com.spring.rest.app.repositories.UserInRoleRepository;
import com.spring.rest.app.repositories.UserRepository;

@Service
public class SecurityUsersDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUserName(username);
		if (optional.isPresent()) {
			User user = optional.get();
			List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
			String[] roles = userInRoles.stream().map(r -> r.getRole().getName()).toArray(String[]::new);
			return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
					.password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}

	}

}
