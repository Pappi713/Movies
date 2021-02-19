package com.greenfoxacademy.demo.service;

import com.greenfoxacademy.demo.model.RegisterDTO;
import com.greenfoxacademy.demo.model.User;
import com.greenfoxacademy.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   private UserRepository userRepository;

   @Autowired
    public UserService(UserRepository userRepository){
       this.userRepository=userRepository;
   }


   public void addUser(RegisterDTO registerDTO){
       User user = new User();
       user.setUserName(registerDTO.getUserName());
       user.setPassword(registerDTO.getPassword());
       user.setActive(false);
       user.setRoles("ROLE_USER");
       userRepository.save(user);
   }

}
