package com.example.finallyyy.repositories;

import com.example.finallyyy.models.InvoiceProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceProductQuantityRepository extends JpaRepository<InvoiceProductQuantity, Integer> {

}
