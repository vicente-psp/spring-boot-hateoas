package com.vicente.springboothateoas.services.impl;

import com.vicente.springboothateoas.entities.Product;
import com.vicente.springboothateoas.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product post(Product entity) {
        return null;
    }

    @Override
    public Product get(Long id) {
        return null;
    }

    @Override
    public void put(Product entity) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public void patch(Product entity) {

    }

    @Override
    public List<Product> post(List<Product> entities) {
        return null;
    }

    @Override
    public List<Product> get() {
        return null;
    }

    @Override
    public void put(List<Product> entities) {

    }

    @Override
    public void delete(List<Product> entities) {

    }

    @Override
    public void patch(List<Product> entities) {

    }

}
