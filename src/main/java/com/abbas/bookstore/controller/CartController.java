package com.abbas.bookstore.controller;


import com.abbas.bookstore.model.Book;
import com.abbas.bookstore.model.Cart;
import com.abbas.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {


    @Autowired
    private CartService cartService;

    @GetMapping("/user-cart/{id}")
    public ResponseEntity<?> getUserCart(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cartService.findCartByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/create-cart")
    public ResponseEntity<?> createCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.createCart(cart), HttpStatus.CREATED);
    }

    @PutMapping("/update-cart")
    public ResponseEntity<?> updateCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.updateCart(cart), HttpStatus.OK);
    }

}

