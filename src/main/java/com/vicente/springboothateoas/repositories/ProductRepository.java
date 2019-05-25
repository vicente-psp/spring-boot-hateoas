package com.vicente.springboothateoas.repositories;

import com.vicente.springboothateoas.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
