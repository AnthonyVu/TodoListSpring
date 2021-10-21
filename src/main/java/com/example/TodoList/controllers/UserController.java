package com.example.TodoList.controllers;

import com.example.TodoList.models.AppUser;
import com.example.TodoList.models.CustomUserDetails;
import com.example.TodoList.models.Todo;
import com.example.TodoList.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public ResponseEntity<Page<AppUser>> getAll() {
        return ResponseEntity.ok(appUserService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") long id) {
        appUserService.deleteUser(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<Void> updateTodo(@RequestBody AppUser user) {
        appUserService.editUser(user);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody AppUser user) {
        appUserService.addUser(user);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
