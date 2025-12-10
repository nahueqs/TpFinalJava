package com.crudJava.demo.service;

import com.crudJava.demo.dto.response.CartResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    @Override
    public CartResponseDTO createCart() {
        return null;
    }

    @Override
    public void deleteCart(Long id) {

    }

    @Override
    public CartResponseDTO updateCart(Long id) {
        return null;
    }

    @Override
    public CartResponseDTO getCart(Long id) {
        return null;
    }

    @Override
    public CartResponseDTO addProductToCart(Long cartId, Long productId) {
        return null;
    }
}
