package com.example.finallyyy.models;

import javax.persistence.*;
import java.util.List;


@Entity
public class Supplier {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String address;
    private String phNumber;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Invoice> invoices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
