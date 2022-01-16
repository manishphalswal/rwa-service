package com.rwa.user.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return Optional.of(userService.getUserById(username))
//				.map(userDto -> User.builder().username(userDto.getUsername()).password(userDto.getPassword()).build())
//				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return null;

	}
}