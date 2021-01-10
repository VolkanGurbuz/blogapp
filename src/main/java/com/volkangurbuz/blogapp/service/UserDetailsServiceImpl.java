package com.volkangurbuz.blogapp.service;

import com.volkangurbuz.blogapp.model.User;
import com.volkangurbuz.blogapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("No User found" + username));

    // what authority the user is having
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        true,
        true,
        true,
        true,
        getAuthorities("ROLE_USER"));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
    return Collections.singletonList(new SimpleGrantedAuthority(role_user));
  }
}
