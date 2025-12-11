package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.ProductCreateRequestDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.entity.Product;
import com.crudJava.demo.exceptions.RecursosInexistenteException;
import com.crudJava.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDetailResponseDTO editProductById(Long id, ProductCreateRequestDTO dataToEdit) {
        Product product = getProductModelById(id);
        product.setName(dataToEdit.name());
        product.setDesc(dataToEdit.desc());
        product.setPrice(Double.valueOf(dataToEdit.price()));
        product.setCategory(dataToEdit.categoryId().intValue());
        productRepository.save(product);
        return toProductDetailResponseDTO(product);
    }

    @Override
    public List<ProductDetailResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toProductDetailResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailResponseDTO createProduct(ProductCreateRequestDTO productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDesc(productRequest.desc());
        product.setPrice(Double.valueOf(productRequest.price()));
        product.setCategory(productRequest.categoryId().intValue());
        productRepository.save(product);
        return toProductDetailResponseDTO(product);
    }

    @Override
    public ProductDetailResponseDTO getProductByID(Long id) {
        return toProductDetailResponseDTO(getProductModelById(id));
    }

    @Override
    public Product getProductModelById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RecursosInexistenteException("No existe el producto")
        );
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDetailResponseDTO toProductDetailResponseDTO(Product product) {
        return new ProductDetailResponseDTO(
                product.getId(),
                product.getName(),
                product.getDesc(),
                product.getPrice(),
                product.getCategory(),
                product.getStock()
        );
    }
}
