package com.abbas.bookstore.service;


import com.abbas.bookstore.model.Purchase;
import com.abbas.bookstore.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase createPurchase(Purchase purchase){
        purchase.setPurchaseDate(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> findPurchasesOfUser(String email){
     return purchaseRepository.findAllByUserEmail(email);
    }

    @Override
    public List<Purchase> findAllPurchases(){
        return purchaseRepository.findAll();
    }
}
