package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.CartStatusChangeRequestDTO;
import com.crudJava.demo.dto.response.CartResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
     CartResponseDTO createCart();
     void deleteCart(Long id);
     CartResponseDTO updateProductInCart(Long cartId, Long productId, Integer quantity);
     CartResponseDTO getCart(Long id);
     CartResponseDTO addProductToCart(Long cartId, Long productId, Integer quantity);
     CartResponseDTO changeStatusCart(Long cartId, CartStatusChangeRequestDTO cartStatusChangeRequestDTO);
    List<CartResponseDTO>  getAllCartsByUser(Long userId);

    List<CartResponseDTO> getAll();
}
