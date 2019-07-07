package com.example.finallyyy.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    @ManyToOne
    @JoinColumn
    private Supplier supplier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<InvoiceProductQuantity> invoices;

    public List<InvoiceProductQuantity> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceProductQuantity> invoices) {
        this.invoices = invoices;
    }

    public Integer getTotalQuantity() {
        return getInvoices().stream().mapToInt(entry -> entry.getQuantity()).sum();
    }

    public double getTotalPrice() {
        return getInvoices().stream().mapToDouble(entry -> entry.getProduct().getPrice()).sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


}