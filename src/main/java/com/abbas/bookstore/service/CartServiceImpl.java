package com.abbas.bookstore.service;


import com.abbas.bookstore.model.Cart;
import com.abbas.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findCartByUserId(Long id) {
        return cartRepository.findByUserId(id).orElse(null);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }
    @Override
    public Cart createCart(Cart cart){
        return cartRepository.save(cart);
    }
}
