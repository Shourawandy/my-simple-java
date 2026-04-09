package com.example.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")  // allow frontend requests
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // GET /api/todos — list all todos
    @GetMapping
    public List<Todo> getAll() {
        return service.getAll();
    }

    // POST /api/todos — create a new todo
    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        if (title == null || title.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.create(title.trim()));
    }

    // PATCH /api/todos/{id}/toggle — mark done/undone
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggle(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.toggleComplete(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /api/todos/{id} — update title
    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String title = body.get("title");
        if (title == null || title.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(service.updateTitle(id, title.trim()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/todos/{id} — delete a todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/todos/stats — summary stats
    @GetMapping("/stats")
    public Map<String, Long> stats() {
        List<Todo> all = service.getAll();
        long total = all.size();
        long done = all.stream().filter(Todo::isCompleted).count();
        return Map.of("total", total, "done", done, "pending", total - done);
    }
}
