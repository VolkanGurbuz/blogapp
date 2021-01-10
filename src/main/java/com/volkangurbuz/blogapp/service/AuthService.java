package com.volkangurbuz.blogapp.service;

import com.volkangurbuz.blogapp.model.User;
import com.volkangurbuz.blogapp.repositories.UserRepository;
import dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  public void signUp(RegisterRequest registerRequest) {
    User user = new User();
    user.setUserName(registerRequest.getUserName());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(encodePassword(registerRequest.getPassword()));
    userRepository.save(user);
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
