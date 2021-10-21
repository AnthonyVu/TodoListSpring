package com.example.TodoList.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime due_date;
    @Column(nullable = false)
    private String priority;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Todo(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @PrePersist
    void setDate() {
        due_date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return getId() == todo.getId() && getTitle().equals(todo.getTitle()) && getDescription().equals(todo.getDescription()) && getDue_date().equals(todo.getDue_date()) && getPriority().equals(todo.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getDue_date(), getPriority());
    }
}
