package com.example.finallyyy.models;

import com.example.finallyyy.models.Invoice;
import com.example.finallyyy.models.Product;

import javax.persistence.*;

@Entity
public class InvoiceProductQuantity {


    @Id
    @GeneratedValue
    private int Id;


    @ManyToOne
    @JoinColumn
    private Invoice invoice;
    @ManyToOne
    @JoinColumn
    private Product product;
    private int quantity;

    public int getId() {
        return Id;

    }

    public void setId(int id) {
        Id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
