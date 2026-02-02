package com.example.BTtuan4.Service;

import com.example.BTtuan4.Model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    public List<Category> getAll() {
        return List.of(
                new Category(1, "Điện thoại"),
                new Category(2, "Laptop")
        );
    }

    public Category get(int id) {
        return getAll().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
