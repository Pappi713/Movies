package com.greenfoxacademy.demo.controller;

import com.greenfoxacademy.demo.model.RegisterDTO;
import com.greenfoxacademy.demo.model.User;
import com.greenfoxacademy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }


    @GetMapping("/register")
    private String register() {
        return "register";
    }

    @PostMapping("register")
    private String register(@ModelAttribute RegisterDTO registerDTO){
        userService.addUser(registerDTO);
        return "redirect:/login";
    }

}
