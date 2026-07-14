package com.sai.todoApp.service;

import com.sai.todoApp.model.TodoItem;
import com.sai.todoApp.repository.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> getAllItems() {
        return repository.findAll();
    }

    public Optional<TodoItem> getItemById(Long id) {
        return repository.findById(id);
    }

    public TodoItem createItem(TodoItem item) {
        return repository.save(item);
    }

    public Optional<TodoItem> updateItem(Long id, TodoItem updatedItem) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(updatedItem.getTitle());
            existing.setCompleted(updatedItem.isCompleted());
            return repository.save(existing);
        });
    }

    public boolean deleteItem(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
