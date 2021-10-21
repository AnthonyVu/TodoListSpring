package com.example.TodoList.controllers;
import com.example.TodoList.models.CustomUserDetails;
import com.example.TodoList.models.Todo;
import com.example.TodoList.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<Page<Todo>> getAll(Authentication authentication) {
        return ResponseEntity.ok(todoService.getTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<Void> updateTodo(@RequestBody Todo todo) {
        todoService.editTodo(todo);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addTodo(@RequestBody Todo todo, Authentication authentication) {
        CustomUserDetails auth = (CustomUserDetails) authentication.getPrincipal();
        todo.setUser(auth.getUser());
        todoService.addTodo(todo);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
