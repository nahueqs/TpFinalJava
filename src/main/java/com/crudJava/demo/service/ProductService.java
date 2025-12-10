package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.ProductCreateRequestDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.entity.Product;
import lombok.Setter;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import java.util.List;

public interface ProductService {

    public  ProductDetailResponseDTO editProductById(Long id, Product dataToEdit);
    public List<Product> getAllProducts();
    public ProductDetailResponseDTO createProduct(ProductCreateRequestDTO productRequest);
    public ProductDetailResponseDTO getProductByID(Long id);
    public void deleteProductById(Long id);


}
