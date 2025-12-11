package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.CartCreateRequestDTO;
import com.crudJava.demo.dto.request.CartStatusChangeRequestDTO;
import com.crudJava.demo.dto.request.ProductCartRequestDTO;
import com.crudJava.demo.dto.response.CartResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface CartService {
     CartResponseDTO createCart(CartCreateRequestDTO request);
     void deleteCart(Long id);
     CartResponseDTO updateProductInCart(Long cartId, ProductCartRequestDTO request);
     CartResponseDTO getCart(Long id);
     CartResponseDTO addProductToCart(Long cartId, ProductCartRequestDTO request);
     CartResponseDTO changeStatusCart(Long cartId, CartStatusChangeRequestDTO cartStatusChangeRequestDTO);
    List<CartResponseDTO>  getAllCartsByUser(Long userId);

    List<CartResponseDTO> getAll();
}
