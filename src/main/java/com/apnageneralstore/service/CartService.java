package com.apnageneralstore.service;

import com.apnageneralstore.dto.cart.CartDto;
import com.apnageneralstore.dto.cart.CartItemDto;
import com.apnageneralstore.repository.ICartRepository;
import com.apnageneralstore.repository.entity.Cart;
import com.apnageneralstore.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    ICartRepository cartRepository;
    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }

    private CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }
}
