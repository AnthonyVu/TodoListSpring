package com.example.TodoList.controllers;

import com.example.TodoList.models.AppUser;
import com.example.TodoList.models.CustomUserDetails;
import com.example.TodoList.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public ResponseEntity<Page<AppUser>> getAll() {
        return ResponseEntity.ok(appUserService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody AppUser user) {
        appUserService.addUser(user);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}