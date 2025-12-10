package com.crudJava.demo.repository;

import com.crudJava.demo.entity.Cart;
import com.crudJava.demo.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
}
