package com.example.todo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getAll() {
        return repo.findAllByOrderByCreatedAtDesc();
    }

    public Todo create(String title) {
        return repo.save(new Todo(title));
    }

    public Todo toggleComplete(Long id) {
        Todo todo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found: " + id));
        todo.setCompleted(!todo.isCompleted());
        return repo.save(todo);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Todo updateTitle(Long id, String newTitle) {
        Todo todo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found: " + id));
        todo.setTitle(newTitle);
        return repo.save(todo);
    }

    public long countPending() {
        return repo.findByCompletedFalse().size();
    }
}
