package com.apnageneralstore.controller;

import com.apnageneralstore.dto.cart.CartDto;
import com.apnageneralstore.repository.entity.User;
import com.apnageneralstore.service.AuthenticationService;
import com.apnageneralstore.service.CartService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    CartService cartService;


    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@PathParam("token") String token){
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}