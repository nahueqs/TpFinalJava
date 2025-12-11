package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.ProductCreateRequestDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.entity.Product;

import java.util.List;

public interface ProductService {

      ProductDetailResponseDTO editProductById(Long id, ProductCreateRequestDTO dataToEdit);
     List<ProductDetailResponseDTO> getAllProducts();
     ProductDetailResponseDTO createProduct(ProductCreateRequestDTO productRequest);
     ProductDetailResponseDTO getProductByID(Long id);
    Product getProductModelById(Long id);
     void deleteProductById(Long id);



}
