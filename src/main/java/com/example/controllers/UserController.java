package com.example.controllers;

import com.example.models.User;
import com.example.repository.UserRepository;
import com.example.security.services.UserDetailsImpl;
import com.example.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        Optional<User> user = userRepository.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



}
