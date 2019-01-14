package com.zarzisdev.EShopApp.controller;

import com.zarzisdev.EShopApp.model.Product;
import com.zarzisdev.EShopApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web")
public class ProductController {
    @Autowired
    ProductRepository repository;

    @GetMapping("/products")
    public List<Product> getAllItems() {
        System.out.println("Get all products...");
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);

        return products;
    }

    @PostMapping("/products/create")
    public Product postItem(@RequestBody Product product) {
        product.setCategory(null);
        Product _product = repository.save(new Product(product.getName(), product.getDescription(), product.getCategory()));
        return _product;
    }

    @DeleteMapping("/products/delete")
    public ResponseEntity<String> deleteAllProducts(){
        System.out.println("Delete all products...");
        repository.deleteAll();
        return new ResponseEntity<>("Product has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateCustomer(@PathVariable("id") long id, @RequestBody Product product) {
        System.out.println("Update Product with ID = " + id + "...");

        Optional<Product> productData = repository.findById(id);

        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setName(product.getName());
            _product.setDescription(product.getDescription());
            return new ResponseEntity<>(repository.save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
