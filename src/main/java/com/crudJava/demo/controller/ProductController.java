package com.crudJava.demo.controller;


import com.crudJava.demo.dto.request.ProductCreateRequestDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    ProductDetailResponseDTO getAllProducts(){
        return null;
    }

    @GetMapping("/{id}")
    ProductDetailResponseDTO getProductById(@PathVariable Long id){
        return null;
    }

    @PostMapping()
    ProductDetailResponseDTO createProduct(ProductCreateRequestDTO productCreateRequestDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id){


    }

    @PutMapping
    void updateProduct(){

    }









}
