package com.apnageneralstore.controller;

import com.apnageneralstore.common.ApiResponse;
import com.apnageneralstore.dto.cart.AddToCardDto;
import com.apnageneralstore.dto.cart.CartDto;
import com.apnageneralstore.exception.AuthenticationFailException;
import com.apnageneralstore.repository.entity.Product;
import com.apnageneralstore.repository.entity.User;
import com.apnageneralstore.service.AuthenticationService;
import com.apnageneralstore.service.CartService;
import com.apnageneralstore.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;


    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@PathParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCardDto addToCartDto,
                                                 @PathParam("token") String token) throws AuthenticationFailException{

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        log.info("User {} is valid",user.getFirstName()+" "+user.getLastName());

        Product product = productService.getProductById(addToCartDto.getProductId());
        log.info("Product to add {}",product.getName());

        cartService.addToCart(addToCartDto,product,user);
        return new ResponseEntity<>(new ApiResponse(true,"Added to cart"),HttpStatus.CREATED);
    }
}