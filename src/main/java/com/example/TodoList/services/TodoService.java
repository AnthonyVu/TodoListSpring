package com.example.TodoList.services;

import com.example.TodoList.models.Todo;
import com.example.TodoList.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
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

    public void deleteTodo(long id) {
        Optional<Todo> todoToDelete = todoRepo.findById(id);
        if(todoToDelete.isPresent()) {
            todoRepo.delete(todoToDelete.get());
        }
    }

    public void editTodo(Todo newTodo) {
        Optional<Todo> todo = todoRepo.findById(newTodo.getId());
        if(newTodo.getDescription() != null) {
            todo.get().setDescription(newTodo.getDescription());
        }
        if(newTodo.getDue_date() != null) {
            todo.get().setDue_date(newTodo.getDue_date());
        }
        if(newTodo.getPriority() != null) {
            todo.get().setPriority(newTodo.getPriority());
        }
        if(newTodo.getTitle() != null) {
            todo.get().setTitle(newTodo.getTitle());
        }
    }
}
