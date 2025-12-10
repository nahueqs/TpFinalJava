package com.crudJava.demo.service;

import com.crudJava.demo.entity.Product;

import java.util.List;

public interface ProductService {

    public  Product editProductById(Long id, Product dataToEdit);
    public List<Product> getAllProducts();
}
