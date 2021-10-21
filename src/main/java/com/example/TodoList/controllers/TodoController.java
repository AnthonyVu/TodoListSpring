package com.example.TodoList.controllers;
import com.example.TodoList.models.AppUser;
import com.example.TodoList.models.CustomUserDetails;
import com.example.TodoList.models.Todo;
import com.example.TodoList.repositories.AppUserRepo;
import com.example.TodoList.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    Logger logger = Logger.getLogger(TodoController.class.getName());

    @GetMapping
    public ResponseEntity<Page<Todo>> getAll(Authentication authentication) {
        logger.info("here");
        return ResponseEntity.ok(todoService.getTodos());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTodo() {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Void> addTodo(@RequestBody Todo todo, Authentication authentication) {
        CustomUserDetails auth = (CustomUserDetails) authentication.getPrincipal();
        todo.setUser(auth.getUser());
        todoService.addTodo(todo);
        logger.info("added todo");
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
