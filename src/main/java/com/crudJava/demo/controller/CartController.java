package com.crudJava.demo.controller;


import com.crudJava.demo.dto.request.CartCreateRequestDTO;
import com.crudJava.demo.dto.request.CartStatusChangeRequestDTO;
import com.crudJava.demo.dto.request.ProductCartRequestDTO;
import com.crudJava.demo.dto.response.CartResponseDTO;
import com.crudJava.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {


    private final CartService cartService;

    @GetMapping
    public List<CartResponseDTO> getCarts() {
        return cartService.getAll();
    }

    @PostMapping()
    public CartResponseDTO createCart(@RequestBody CartCreateRequestDTO request){
        return cartService.createCart(request);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id){
         cartService.deleteCart(id);
    }

    @GetMapping("/{id}")
    public CartResponseDTO getCart(@PathVariable Long id){
        return cartService.getCart(id);
    }


    @PostMapping("/{cartId}/add")
    public CartResponseDTO addProductToCart(@PathVariable Long cartId, @RequestBody ProductCartRequestDTO request){
        return cartService.addProductToCart(cartId, request );
    }

    @PutMapping("/{cartId}/update")
    public CartResponseDTO updateProductOnCart(@PathVariable Long cartId, @RequestBody ProductCartRequestDTO request){
        return cartService.updateProductInCart(cartId, request );
    }

    @PutMapping("/{id}/status")
    public CartResponseDTO changeStatus(@PathVariable Long id, @RequestBody CartStatusChangeRequestDTO request){
        return cartService.changeStatusCart(id, request);
    }

    @GetMapping("/users/{id}")
    public List<CartResponseDTO> getCartsByUser(@PathVariable Long id) {
        return cartService.getAllCartsByUser(id);
    }
}
