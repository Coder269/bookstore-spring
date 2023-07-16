package com.abbas.bookstore.controller;

import com.abbas.bookstore.model.Purchase;
import com.abbas.bookstore.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.createPurchase(purchase), HttpStatus.CREATED);
    }

    @GetMapping("/user-purchases/{email}")
    public ResponseEntity<?> getUserPurchases(@PathVariable("email") String email){
        return new ResponseEntity<>(purchaseService.findPurchasesOfUser(email), HttpStatus.OK);
    }

    @GetMapping("/admin/all-purchases")
    public ResponseEntity<?> getAllPurchases(){
        return new ResponseEntity<>(purchaseService.findAllPurchases(), HttpStatus.OK);
    }
}

