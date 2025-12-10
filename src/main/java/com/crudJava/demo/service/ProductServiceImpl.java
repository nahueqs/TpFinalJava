package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.ProductCreateRequestDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService
{
    @Override
    public ProductDetailResponseDTO editProductById(Long id, Product dataToEdit) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductDetailResponseDTO createProduct(ProductCreateRequestDTO productRequest) {
        return null;
    }

    @Override
    public ProductDetailResponseDTO getProductByID(Long id) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
