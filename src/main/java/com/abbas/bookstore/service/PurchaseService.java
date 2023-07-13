package com.abbas.bookstore.service;

import com.abbas.bookstore.model.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase createPurchase(Purchase purchase);

    List<Purchase> findPurchasesOfUser(String email);

    List<Purchase> findAllPurchases();
}
