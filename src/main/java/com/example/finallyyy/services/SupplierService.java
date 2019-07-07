package com.example.finallyyy.services;

import com.example.finallyyy.models.Supplier;
import com.example.finallyyy.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier saveSupploer(Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    public void updateSupplier(Supplier supplier) {
        Supplier oldSupplier = supplierRepository.getOne(supplier.getId());

        oldSupplier.setAddress(supplier.getAddress());
        oldSupplier.setName(supplier.getName());
        oldSupplier.setPhNumber(supplier.getPhNumber());
        supplierRepository.save(supplier);

    }

    public Optional<Supplier> getSupplier(Integer id) {
        return supplierRepository.findById(id);
    }

    public void deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
    }
}
