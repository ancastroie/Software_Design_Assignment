package com.project.stackoverflow.controllers;

import com.project.stackoverflow.dtos.SignupDTO;
import com.project.stackoverflow.dtos.UserDTO;
import com.project.stackoverflow.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody(required = true) SignupDTO signupDTO){

        if(userService.hasUserWithEmail(signupDTO.getEmail()))
           return new ResponseEntity<>("user already exists with email",HttpStatus.NOT_ACCEPTABLE);

        UserDTO createdUser = userService.createUser(signupDTO);
        if(createdUser==null)
            return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
}