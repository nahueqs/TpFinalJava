package com.crudJava.demo.controller;


import com.crudJava.demo.dto.request.ProductCreateRequestDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    List<ProductDetailResponseDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    ProductDetailResponseDTO getProductById(@PathVariable Long id){
        return productService.getProductByID(id);
    }

    @PostMapping()
    ProductDetailResponseDTO createProduct(@RequestBody ProductCreateRequestDTO productCreateRequestDTO){
        return productService.createProduct(productCreateRequestDTO);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);

    }

    @PutMapping("/{id}")
    ProductDetailResponseDTO updateProduct(@RequestBody ProductCreateRequestDTO request, @PathVariable Long id){
       return productService.editProductById(id, request);
    }









}
