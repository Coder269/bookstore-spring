package com.abbas.bookstore.service;

import com.abbas.bookstore.model.Book;
import com.abbas.bookstore.model.Cart;

import javax.transaction.Transactional;

public interface CartService {
    Cart findCartByUserId(Long id);

    Cart updateCart(Cart cart);

    Cart createCart(Cart cart);
}
