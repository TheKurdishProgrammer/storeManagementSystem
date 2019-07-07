package com.example.finallyyy.repositories;

import com.example.finallyyy.models.Invoice;
import com.example.finallyyy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
