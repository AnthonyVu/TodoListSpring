package com.example.TodoList.controllers;
import com.example.TodoList.models.AppUser;
import com.example.TodoList.models.CustomUserDetails;
import com.example.TodoList.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/enable")
public class OtpController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> enableUser(@RequestBody AppUser appUser) {
        CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(appUser.getUsername());
        user.getUser().setEnabled(true);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
