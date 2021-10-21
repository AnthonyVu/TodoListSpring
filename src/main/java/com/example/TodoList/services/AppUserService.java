package com.example.TodoList.services;

import com.example.TodoList.models.AppUser;
import com.example.TodoList.models.Todo;
import com.example.TodoList.repositories.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<AppUser> getAll() {
        Pageable page = PageRequest.of(0, 10);
        return appUserRepo.findAll(page);
    }

    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepo.save(appUser);
    }

    public void deleteUser(long id) {
        Optional<AppUser> userToDelete = appUserRepo.findById(id);
        if(userToDelete.isPresent()) {
            appUserRepo.delete(userToDelete.get());
        }
    }

    public void editUser(AppUser appUser) {
        Optional<AppUser> todo = appUserRepo.findById(appUser.getId());
        if(appUser.getUsername() != null) {
            todo.get().setUsername(appUser.getUsername());
        }
        if(appUser.getPassword() != null) {
            todo.get().setPassword(appUser.getPassword());
        }
    }
}
