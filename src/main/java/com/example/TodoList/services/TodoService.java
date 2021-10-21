package com.example.TodoList.services;

import com.example.TodoList.models.Todo;
import com.example.TodoList.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    public Page<Todo> getTodos() {
        Pageable page = PageRequest.of(0, 10);
        return todoRepo.findAll(page);
    }

    public void addTodo(Todo todo) {
        todoRepo.save(todo);
    }
}
