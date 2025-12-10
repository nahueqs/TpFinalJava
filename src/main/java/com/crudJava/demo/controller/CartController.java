package com.crudJava.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/api/carts")
public class CartController {


    @PostMapping("/{id}")
    public void addProductToCart(){

    }


}
