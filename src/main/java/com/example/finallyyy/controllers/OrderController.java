package com.example.finallyyy.controllers;

import com.example.finallyyy.models.*;
import com.example.finallyyy.models.invoiceVm.InVoiceViewModel;
import com.example.finallyyy.models.invoiceVm.InvoiceEntryModel;
import com.example.finallyyy.models.InvoiceProductQuantity;
import com.example.finallyyy.models.invoiceVm.SupplierViewModel;
import com.example.finallyyy.services.InvoiceService;
import com.example.finallyyy.services.ProductService;
import com.example.finallyyy.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductService productService;
    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping("/")
    public String getOrders(Model model) {

        List<Invoice> orders = invoiceService.getInvoices();

        model.addAttribute("suppliers", supplierService.getSuppliers());
        model.addAttribute("orders", orders);


        return "orders";
    }


    @RequestMapping(value = {"/printOrder", "/printOrder/"})
    @ResponseBody
    public String printOrder(Integer id) {


        Optional<Invoice> invoice = invoiceService.getInvoice(id);
        /*
             handle nullability also
         */

        String pdfLink = invoiceService.printInvoice(invoice.get());

        return pdfLink;
//        return "redirect:/";

    }


    @PostMapping(value = {"/addOrder", "/addOrder/"})
    public String addOrder(HttpServletRequest request) {


        Invoice invoice = new Invoice();

        Supplier supplier = supplierService.getSupplier(Integer.valueOf(request.getParameter("answer"))).get();

        invoice.setSupplier(supplier);

//        invoiceService.save(invoice);


        String[] productIds = request.getParameterValues("ids[]");
        String[] producttQuantities = request.getParameterValues("quantities[]");

        List<InvoiceProductQuantity> invoiceProductQuantities = new ArrayList<>();

        for (int i = 0; i < productIds.length; i++) {
            int pid = Integer.parseInt(productIds[i]);
            int quantity = Integer.parseInt(producttQuantities[i]);

            InvoiceProductQuantity iq = new InvoiceProductQuantity();
            iq.setQuantity(quantity);
            iq.setProduct(productService.getProduct(pid).get());

            iq.setInvoice(invoice);
            invoiceProductQuantities.add(iq);
        }

        invoice.setInvoices(invoiceProductQuantities);


        invoiceService.save(invoice);

        return "redirect:/orders/";
    }


}
