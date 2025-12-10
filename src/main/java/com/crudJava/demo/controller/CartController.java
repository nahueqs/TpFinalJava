package com.crudJava.demo.controller;


import com.crudJava.demo.dto.request.CartStatusChangeRequestDTO;
import com.crudJava.demo.dto.response.CartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    @PostMapping()
    public CartResponseDTO createCart(){
        return null;
    }

    @DeleteMapping("/{id}")
    public CartResponseDTO deleteCart(@PathVariable String id){
        return null;
    }

    @PutMapping("/{id}")
    public CartResponseDTO updateCart(@PathVariable String id){
        return null;
    }

    @GetMapping("/{id}")
    public CartResponseDTO getCart(@PathVariable String id){
        return null;
    }


    @PostMapping("/{cartId}/{productId}")
    public CartResponseDTO addProductToCart(){
        return null;
    }

    @PutMapping("/{cartId}/{productId}")
    public CartResponseDTO updateProductOnCart(){
        return null;
    }

    @PutMapping("/{id}/status")
    public CartResponseDTO changeStatus(@PathVariable Long id, CartStatusChangeRequestDTO request){
        return null;
    }

}
