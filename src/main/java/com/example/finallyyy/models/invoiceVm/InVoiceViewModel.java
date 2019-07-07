package com.example.finallyyy.models.invoiceVm;

import java.util.List;

public class InVoiceViewModel {


    private int code;
    private SupplierViewModel supplier;

    private List<InvoiceEntryModel> entries;


    public Double getTotalPrice() {
        return getEntries().stream().mapToDouble(entry -> entry.getPrice()).sum();
    }

    public Integer getTotalQuantity() {
        return getEntries().stream().mapToInt(entry -> entry.getQuantity()).sum();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SupplierViewModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierViewModel supplier) {
        this.supplier = supplier;
    }

    public List<InvoiceEntryModel> getEntries() {
        return entries;
    }

    public void setEntries(List<InvoiceEntryModel> entries) {
        this.entries = entries;
    }
}
