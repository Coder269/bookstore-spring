package com.abbas.bookstore.repository;

import com.abbas.bookstore.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByUserEmail (String email);
}
