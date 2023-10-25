package com.codegym.aurora.service.impl;

import com.codegym.aurora.cache.CartCache;
import com.codegym.aurora.converter.CartConverter;
import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.CartLine;
import com.codegym.aurora.entity.Product;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.response.CartDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.repository.CartLineRepository;
import com.codegym.aurora.repository.CartRepository;
import com.codegym.aurora.repository.ProductRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final CartCache cartCache;
    private final ProductRepository productRepository;
    private final CartConverter cartConverter;
    private final CartRepository cartRepository;
    private final CartLineRepository cartLineRepository;

    @Override
    public ResponseDTO addToCart(Long productId, int quantity) {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            return ResponseDTO.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Product does not exist")
                    .build();
        }
        String userName = userService.getCurrentUsername();
        User user = userRepository.findByUsername(userName);
        Cart cart = cartRepository.findCartByUser(user);
        if (cart.getCartLineList().isEmpty()) {
            CartLine cartLine = CartLine.builder()
                    .product(product)
                    .quantity(quantity)
                    .totalPrice(product.getPrice() * quantity)
                    .cart(cart)
                    .build();
            cart.getCartLineList().add(cartLine);
            cart.setTotalAmount(cartLine.getTotalPrice());
            cartRepository.save(cart);
            cartCache.addToCart(user.getId(), cart);
            return ResponseDTO.builder()
                    .status(HttpStatus.OK)
                    .message("Product added to cart successfully")
                    .build();
        } else {
            List<CartLine> cartLineList = cart.getCartLineList();
            for (CartLine cartLine : cartLineList) {
                long newQuantity = cartLine.getQuantity() + quantity;
                if (product.getId() == cartLine.getProduct().getId())
                    if (product.getQuantity() >= newQuantity) {
                        cartLine.setQuantity(cartLine.getQuantity() + quantity);
                        cartLine.setTotalPrice(product.getPrice() * cartLine.getQuantity());
                        cartRepository.save(cart);
                        cartCache.addToCart(user.getId(), cart);
                        return ResponseDTO.builder()
                                .status(HttpStatus.OK)
                                .message("Product added to cart successfully")
                                .build();
                    } else {
                        responseDTO.setMessage("Out of stock");
                        responseDTO.setStatus(HttpStatus.OK);
                        return responseDTO;
                    }
            }
            CartLine newCartLine = CartLine.builder()
                    .totalPrice(product.getPrice() * quantity)
                    .quantity(quantity)
                    .product(product)
                    .cart(cart)
                    .build();
            cartLineList.add(newCartLine);
            cart.setCartLineList(cartLineList);
            cartRepository.save(cart);
            cartCache.addToCart(user.getId(), cart);
            return ResponseDTO.builder()
                    .status(HttpStatus.OK)
                    .message("Product added to cart successfully")
                    .build();
        }
    }

    @Override
    public ResponseDTO showCart() {
        String userName = userService.getCurrentUsername();
        User user = userRepository.findByUsername(userName);
        Cart cart = cartCache.getCart(user.getId());
        List<CartLine> cartLineList = cart.getCartLineList();
        long totalAmount = 0;
        for (CartLine cartLine : cartLineList) {
            totalAmount += cartLine.getTotalPrice();
        }
        cart.setTotalAmount(totalAmount);
        CartDTO cartDTO = cartConverter.convertCartEntityToDTO(cart);
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message("Show cart successfully")
                .data(cartDTO)
                .build();
    }

    @Override
    public ResponseDTO removeCartLine(long productId) {
        String userName = userService.getCurrentUsername();
        User user = userRepository.findByUsername(userName);
        Cart cart = cartCache.getCart(user.getId());
        List<CartLine> cartLineList = cart.getCartLineList();
        List<CartLine> newCartLineList = new ArrayList<>();
        long newToTalAmount = 0;
        for (CartLine cartLine : cartLineList) {
            if (cartLine.getProduct().getId() == productId) {
                cartLineRepository.delete(cartLine);
            } else {
                newCartLineList.add(cartLine);
                newToTalAmount += cartLine.getTotalPrice();
            }
        }
        cart.setTotalAmount(newToTalAmount);
        cart.setCartLineList(newCartLineList);
        cartCache.updateCart(user.getId(), cart);
        CartDTO cartDTO = cartConverter.convertCartEntityToDTO(cart);
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .data(cartDTO)
                .message("delete cart line successfully")
                .build();
    }

    @Override
    public ResponseDTO addNewQuantityToCart(Long productId, int quantity) {
        String userName = userService.getCurrentUsername();
        User user = userRepository.findByUsername(userName);
        Cart cart = cartCache.getCart(user.getId());
        List<CartLine> cartLineList = cart.getCartLineList();
        long totalAmount = 0;
        for (CartLine cartLine : cartLineList) {
            if (cartLine.getProduct().getId() == productId) {
                cartLine.setQuantity(quantity);
                cartLine.setTotalPrice(cartLine.getProduct().getPrice() * quantity);
                cartLineRepository.save(cartLine);
            }
            totalAmount += cartLine.getTotalPrice();
            cart.setTotalAmount(totalAmount);
//            cartRepository.save(cart);
        }
        cartCache.addToCart(user.getId(), cart);
        CartDTO cartDTO = cartConverter.convertCartEntityToDTO(cart);
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message("add new quantity to cart successfully")
                .data(cartDTO)
                .build();
    }

    @Override
    public ResponseDTO saveCart() {
        String username = userService.getCurrentUsername();
        User user = userRepository.findByUsername(username);
        Cart cart = cartRepository.findCartByUser(user);
        CartDTO cartDTO = cartConverter.convertCartEntityToDTO(cart);
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message("save cart successfully")
                .data(cartDTO)
                .build();
    }
}
