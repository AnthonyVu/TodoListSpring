package com.example.TodoList.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String authority;
    @Column(nullable = false)
    private boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Todo> todoList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser) o;
        return getId() == appUser.getId() && isEnabled() == appUser.isEnabled() && getUsername().equals(appUser.getUsername()) && getPassword().equals(appUser.getPassword()) && getAuthority().equals(appUser.getAuthority()) && getTodoList().equals(appUser.getTodoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getAuthority(), isEnabled(), getTodoList());
    }
}
