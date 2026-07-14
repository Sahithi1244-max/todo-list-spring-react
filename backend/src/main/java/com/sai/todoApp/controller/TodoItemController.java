package com.sai.todoApp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.sai.todoApp.model.TodoItem;
import com.sai.todoApp.service.TodoItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174",
    "http://localhost:5175",
    "http://localhost:5176",
    "http://localhost:5177"
})
@RestController
@RequestMapping("/items")
public class TodoItemController {

    private final TodoItemService service;

    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoItem> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getItemById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TodoItem> createItem(@RequestBody TodoItem item) {
        TodoItem created = service.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateItem(@PathVariable Long id, @RequestBody TodoItem item) {
        return service.updateItem(id, item)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        return service.deleteItem(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
