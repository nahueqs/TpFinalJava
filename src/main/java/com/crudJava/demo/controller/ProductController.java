package com.crudJava.demo.controller;


import com.crudJava.demo.dto.request.ProductCreateRequestDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/api/products")
public class ProductController {

    @GetMapping("/{id}")
    ProductDetailResponseDTO getProductById(@PathVariable Long id){
        return null;
    }

    @PostMapping()
    ProductCreateResponseDTO createProduct(ProductCreateRequestDTO productCreateRequestDTO){
        return null;
    }

    @PostMapping("/{id}")
    ProductDeleteResponseDTO deleteProduct(Long id){
        return null;
    }







}
