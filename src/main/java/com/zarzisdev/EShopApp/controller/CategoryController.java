package com.zarzisdev.EShopApp.controller;

import com.zarzisdev.EShopApp.model.Category;
import com.zarzisdev.EShopApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryRepository repository;

    @GetMapping("/public/categories")
    public List<Category> getAllItems() {
        System.out.println("Get all categories...");
        List<Category> categories = new ArrayList<>();
        repository.findAll().forEach(categories::add);

        return categories;
    }

    @PostMapping("/categories/create")
    public Category postItem(@RequestBody Category category) {
        Category _category = repository.save(new Category(category.getName(), category.getDescription()));
        return _category;
    }

    @DeleteMapping("/categories/delete")
    public ResponseEntity<String> deleteAllProducts(){
        System.out.println("Delete all categories...");
        repository.deleteAll();
        return new ResponseEntity<>("Category has been deleted!", HttpStatus.OK);
    }
    

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCustomer(@PathVariable("id") long id, @RequestBody Category category) {
        System.out.println("Update Category with ID = " + id + "...");

        Optional<Category> productData = repository.findById(id);

        if (productData.isPresent()) {
            Category _category = productData.get();
            _category.setName(category.getName());
            _category.setDescription(category.getDescription());
            return new ResponseEntity<>(repository.save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
