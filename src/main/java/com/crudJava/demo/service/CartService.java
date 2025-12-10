package com.crudJava.demo.service;

import com.crudJava.demo.dto.response.CartResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    public CartResponseDTO createCart();
    public void deleteCart(Long id);
    public CartResponseDTO updateCart(Long id);
    public CartResponseDTO getCart(Long id);
    public CartResponseDTO addProductToCart(Long cartId, Long productId);
}
