package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.CartCreateRequestDTO;
import com.crudJava.demo.dto.request.CartStatusChangeRequestDTO;
import com.crudJava.demo.dto.request.ProductCartRequestDTO;
import com.crudJava.demo.dto.response.CartResponseDTO;
import com.crudJava.demo.dto.response.ProductCartResponseDTO;
import com.crudJava.demo.dto.response.ProductDetailResponseDTO;
import com.crudJava.demo.entity.Cart;
import com.crudJava.demo.entity.Product;
import com.crudJava.demo.entity.ProductCart;
import com.crudJava.demo.entity.User;
import com.crudJava.demo.enums.CartStatus;
import com.crudJava.demo.exceptions.CantidadPedidoInvalidaException;
import com.crudJava.demo.exceptions.ProductoNoEstaEnPedidoException;
import com.crudJava.demo.exceptions.RecursosInexistenteException;
import com.crudJava.demo.repository.CartRepository;
import com.crudJava.demo.repository.ProductCartRepository;
import com.crudJava.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ProductCartRepository productCartRepository;
    private final UserService userService;
    private final ProductRepository productRepository;


    @Override
    public CartResponseDTO createCart(CartCreateRequestDTO request) {
        User user = userService.findModelById(request.idUser());
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setStatus(CartStatus.PENDING);
        cart.setProductCarts(new ArrayList<>());

        Cart savedCart = cartRepository.save(cart);

        if (request.productCartRequestDTOList() != null && !request.productCartRequestDTOList().isEmpty()) {
            List<ProductCart> productCarts = new ArrayList<>();
            for (ProductCartRequestDTO productRequest : request.productCartRequestDTOList()) {
                if (productRequest.quantity() <= 0) {
                    throw new CantidadPedidoInvalidaException("La cantidad debe ser mayor a cero.");
                }
                Product product = productService.getProductModelById(productRequest.productID());
                ProductCart productCart = new ProductCart();
                productCart.setCart(savedCart);
                productCart.setProduct(product);
                productCart.setQuantity(productRequest.quantity());
                productCarts.add(productCart);
            }
            productCartRepository.saveAll(productCarts);
            savedCart.setProductCarts(productCarts);
        }

        return toCartResponseDTO(savedCart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartResponseDTO updateProductInCart(Long cartId, ProductCartRequestDTO request) {
        if (request.quantity() < 0) {
            throw new CantidadPedidoInvalidaException("La cantidad del producto no puede ser negativa.");
        }

        Cart existingCart = findModelbyId(cartId);

        if (!productAlreadyInCart(existingCart, request.productID())) {
            throw new ProductoNoEstaEnPedidoException("El producto no está en el carrito");
        }

        ProductCart productCart = existingCart.getProductCarts().stream()
                .filter(pc -> pc.getProduct().getId().equals(request.productID()))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEstaEnPedidoException("El producto no está en el carrito"));

        productCart.setQuantity(request.quantity());

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
                model.getId(),
                model.getUser().getId(),
                model.getStatus().toString(),
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
    public CartResponseDTO addProductToCart(Long cartId, ProductCartRequestDTO request) {
        if (request.quantity() <= 0) {
            throw new CantidadPedidoInvalidaException("La cantidad debe ser mayor a cero.");
        }

        Cart existingCart = findModelbyId(cartId);
        Product producto = productService.getProductModelById(request.productID());

        Optional<ProductCart> existingProductCart = findProductInCart(existingCart, request.productID());

        if (existingProductCart.isPresent()) {
            ProductCart productCart = existingProductCart.get();
            productCart.setQuantity(productCart.getQuantity() + request.quantity());
            productCartRepository.save(productCart);
        } else {
            ProductCart newProductCart = new ProductCart();
            newProductCart.setCart(existingCart);
            newProductCart.setProduct(producto);
            newProductCart.setQuantity(request.quantity());

            productCartRepository.save(newProductCart);

            existingCart.getProductCarts().add(newProductCart);
        }

        cartRepository.save(existingCart);
        return toCartResponseDTO(existingCart);
    }


    @Override
    public CartResponseDTO changeStatusCart(Long cartId, CartStatusChangeRequestDTO cartStatusChangeRequestDTO) {

        Cart cart = findModelbyId(cartId);
        CartStatus newStatus = CartStatus.fromString(cartStatusChangeRequestDTO.status());

        if (newStatus == CartStatus.COMPLETED) {
            for (ProductCart productCart : cart.getProductCarts()) {
                Product product = productCart.getProduct();
                int newStock = product.getStock() - productCart.getQuantity();
                if (newStock < 0) {
                    throw new CantidadPedidoInvalidaException("No hay stock suficiente para el producto: " + product.getName());
                }
                product.setStock(newStock);
                productRepository.save(product);
            }
        }

        cart.setStatus(newStatus);
        cartRepository.save(cart);
        return toCartResponseDTO(cart);

    }

    @Override
    public List<CartResponseDTO> getAllCartsByUser(Long userId) {
        User user = userService.findModelById(userId);
        return toCartResponseDTOList(user.getCarts());
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
