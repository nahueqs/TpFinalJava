package com.crudJava.demo;

import com.crudJava.demo.entity.Cart;
import com.crudJava.demo.entity.Product;
import com.crudJava.demo.entity.ProductCart;
import com.crudJava.demo.entity.User;
import com.crudJava.demo.enums.CartStatus;
import com.crudJava.demo.repository.CartRepository;
import com.crudJava.demo.repository.ProductCartRepository;
import com.crudJava.demo.repository.ProductRepository;
import com.crudJava.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demoData(UserRepository userRepository, ProductRepository productRepository,
//									  CartRepository cartRepository, ProductCartRepository productCartRepository) {
//		return args -> {
//			// Create Users
//			User user1 = new User(null, "John Doe", "john.doe@example.com", null);
//			User user2 = new User(null, "Jane Smith", "jane.smith@example.com", null);
//			userRepository.save(user1);
//			userRepository.save(user2);
//
//			// Create Products
//			Product product1 = new Product(null, "Laptop", "Desc", 1200.00);
//			Product product2 = new Product(null, "Mouse", "Desc", 25.00);
//			Product product3 = new Product(null, "Keyboard", "Desc", 75.00);
//			productRepository.save(product1);
//			productRepository.save(product2);
//			productRepository.save(product3);
//
//			// Create Carts
//			Cart cart1 = new Cart(null, user1, CartStatus.PENDING, null);
//			Cart cart2 = new Cart(null, user2, CartStatus.COMPLETED, null);
//			cartRepository.save(cart1);
//			cartRepository.save(cart2);
//
//			// Create ProductCarts
//			ProductCart pc1 = new ProductCart(null, cart1, product1, 1);
//			ProductCart pc2 = new ProductCart(null, cart1, product2, 2);
//			ProductCart pc3 = new ProductCart(null, cart2, product3, 1);
//			productCartRepository.save(pc1);
//			productCartRepository.save(pc2);
//			productCartRepository.save(pc3);
//
//			// Update Cart with ProductCarts (optional, as ProductCart already links to Cart)
//			cart1.setProductCarts(Arrays.asList(pc1, pc2));
//			cart2.setProductCarts(Collections.singletonList(pc3));
//			cartRepository.save(cart1);
//			cartRepository.save(cart2);
//
//			// Update User with Carts (optional, as Cart already links to User)
//			user1.setCarts(Collections.singletonList(cart1));
//			user2.setCarts(Collections.singletonList(cart2));
//			userRepository.save(user1);
//			userRepository.save(user2);
//
//			System.out.println("Sample data inserted!");
//		};
//	}
}
