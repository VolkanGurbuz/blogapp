package com.volkangurbuz.blogapp.service;

import com.volkangurbuz.blogapp.model.User;
import com.volkangurbuz.blogapp.repositories.UserRepository;
import dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired private UserRepository userRepository;

  public void signUp(RegisterRequest registerRequest) {
    User user = new User();
    user.setUserName(registerRequest.getUserName());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(registerRequest.getPassword());
    userRepository.save(user);
  }
}
