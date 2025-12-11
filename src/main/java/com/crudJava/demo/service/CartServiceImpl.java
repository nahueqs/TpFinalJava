package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.CartStatusChangeRequestDTO;
import com.crudJava.demo.dto.response.CartResponseDTO;
import com.crudJava.demo.dto.response.ProductCartResponseDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.entity.Cart;
import com.crudJava.demo.entity.Product;
import com.crudJava.demo.entity.ProductCart;
import com.crudJava.demo.enums.CartStatus;
import com.crudJava.demo.exceptions.CantidadPedidoInvalidaException;
import com.crudJava.demo.exceptions.ProductoNoEstaEnPedidoException;
import com.crudJava.demo.exceptions.ProductoYaEnCarrito;
import com.crudJava.demo.exceptions.RecursosInexistenteException;
import com.crudJava.demo.repository.CartRepository;
import com.crudJava.demo.repository.ProductCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.spi.ToolProvider.findFirst;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ProductCartRepository productCartRepository;


    @Override
    public CartResponseDTO createCart() {
        return null;
    }

    @Override
    public void deleteCart(Long id) {

    }

    @Override
    public CartResponseDTO updateProductInCart(Long cartId, Long productId, Integer quantity) {
        if (quantity < 0) {
            throw new CantidadPedidoInvalidaException("La cantidad del producto no puede ser negativa.");
        }

        Cart existingCart = findModelbyId(cartId);

        // Check if product is already in cart using the corrected method
        if (!productAlreadyInCart(existingCart, productId)) {
            throw new ProductoNoEstaEnPedidoException("El producto no está en el carrito");
        }

        // Find the existing ProductCart entry
        ProductCart productCart = existingCart.getProductCarts().stream()
                .filter(pc -> pc.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEstaEnPedidoException("El producto no está en el carrito"));

        // Update quantity
        productCart.setQuantity(quantity);

        // Save the ProductCart entity
        productCartRepository.save(productCart);

        return toCartResponseDTO(existingCart);
    }



    private List<ProductDetailResponseDTO> productListToResponseDTOList(List<Product> products){
        return products.stream().map(
                product -> new ProductDetailResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDesc(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getStock()
                )).toList();
    }



    private ProductCartResponseDTO productCartToResponseDTO(ProductCart model){
        return new ProductCartResponseDTO(
                model.getProduct().getId(),
                model.getQuantity()
        );
    }

    private List<ProductCartResponseDTO> productCartListToResponseDTOList(List<ProductCart> models){
        return models.stream().map(this::productCartToResponseDTO).toList();
    }

    private CartResponseDTO toCartResponseDTO(Cart model){
        return new CartResponseDTO(
                model.getUser().getId(),
                productCartListToResponseDTOList(model.getProductCarts())
        );
    }

    private Cart findModelbyId(Long id){
        return cartRepository.findById(id).orElseThrow(
                () -> new RecursosInexistenteException("El carrito no existe"));
    }


    @Override
    public CartResponseDTO getCart(Long id) {
        return toCartResponseDTO(findModelbyId(id));
    }


    private boolean productAlreadyInCart(Cart cart, Long productId){
        return cart.getProductCarts().stream()
                .anyMatch(productCart -> productCart.getProduct().getId().equals(productId));
    }


    private Optional<ProductCart> findProductInCart(Cart cart, Long productId) {
        return cart.getProductCarts().stream()
                .filter(productCart -> productCart.getProduct().getId().equals(productId))
                .findFirst();
    }

    @Override
    public CartResponseDTO addProductToCart(Long cartId, Long productId, Integer quantity) {
        if (quantity <= 0) {
            throw new CantidadPedidoInvalidaException("La cantidad debe ser mayor a cero.");
        }

        Cart existingCart = findModelbyId(cartId);
        Product producto = productService.getProductModelById(productId);

        Optional<ProductCart> existingProductCart = findProductInCart(existingCart, productId);

        if (existingProductCart.isPresent()) {
            // Update existing product quantity
            ProductCart productCart = existingProductCart.get();
            productCart.setQuantity(productCart.getQuantity() + quantity);
            productCartRepository.save(productCart);
        } else {
            // Create new ProductCart entry
            ProductCart newProductCart = new ProductCart();
            newProductCart.setCart(existingCart);
            newProductCart.setProduct(producto);
            newProductCart.setQuantity(quantity);

            // Save the ProductCart first
            productCartRepository.save(newProductCart);

            // Add to cart's list
            existingCart.getProductCarts().add(newProductCart);
        }

        cartRepository.save(existingCart);
        return toCartResponseDTO(existingCart);
    }


    @Override
    public CartResponseDTO changeStatusCart(Long cartId, CartStatusChangeRequestDTO cartStatusChangeRequestDTO) {

        Cart cart = findModelbyId(cartId);


        cart.setStatus(CartStatus.fromString(cartStatusChangeRequestDTO.status()));

        cartRepository.save(cart);
        return toCartResponseDTO(cart);

    }

    @Override
    public List<CartResponseDTO> getAllCartsByUser(Long userId) {
        return List.of();
    }

    @Override
    public List<CartResponseDTO> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return toCartResponseDTOList(carts);
    }


    private List<CartResponseDTO> toCartResponseDTOList(List<Cart> all) {
        return all.stream().map(this::toCartResponseDTO).toList();
    }

    public CartResponseDTO removeProductFromCart(Long cartId, Long productId) {
        Cart cart = findModelbyId(cartId);

        ProductCart productCartToRemove = cart.getProductCarts().stream()
                .filter(pc -> pc.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEstaEnPedidoException("El producto no está en el carrito"));

        cart.getProductCarts().remove(productCartToRemove);

        productCartRepository.delete(productCartToRemove);

        cartRepository.save(cart);
        return toCartResponseDTO(cart);
    }
}
