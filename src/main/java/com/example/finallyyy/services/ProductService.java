package com.example.finallyyy.services;

import com.example.finallyyy.models.Product;
import com.example.finallyyy.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);

    }

    public void updateProduct(Product product) {

        Product oldProduct = productRepository.getOne(product.getId());

        oldProduct.setName(product.getName());
        oldProduct.setCode(product.getCode());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());

        productRepository.save(oldProduct);

    }
}
