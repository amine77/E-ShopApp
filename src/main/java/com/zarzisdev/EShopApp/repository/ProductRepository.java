package com.zarzisdev.EShopApp.repository;

import com.zarzisdev.EShopApp.model.Item;
import com.zarzisdev.EShopApp.model.Product;
import com.zarzisdev.EShopApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCategory(String category);
}