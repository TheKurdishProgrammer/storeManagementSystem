package com.example.finallyyy.controllers;

import com.example.finallyyy.models.Supplier;
import com.example.finallyyy.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(value = {"", "/"})
    public String getSupplier(Model model) {

        List<Supplier> suppliers = supplierService.getSuppliers();

        model.addAttribute("suppliers", suppliers);

        return "suppliers";
    }

    @PostMapping(value = {"/addSupplier", "/addSupplier/"})
    public String addSupplier(Supplier supplier, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors()) {
                System.out.println(allError.getDefaultMessage());
            }

        } else {
            Supplier savedSupplier = supplierService.saveSupploer(supplier);
        }
        /*
            handling not saved situation
            if (savedSupplier.getId() <= 0)
          */


        return "redirect:/suppliers/";
    }

    @RequestMapping(value = {"/editSupplier", "/editSupplier/"})
    public String editSupplier(Supplier supplier) {

        supplierService.updateSupplier(supplier);

        return "redirect:/suppliers";
    }

    @RequestMapping(value = {"/getSupplier", "/getSupplier/"})
    @ResponseBody
    public Optional<Supplier> getSupplier(Integer id) {


        Optional<Supplier> supplier = supplierService.getSupplier(id);
        /*
             handle nullability also
         */

        return supplier;
    }

    @RequestMapping(value = {"/deleteSupplier", "/deleteSupplier/"})
    public String deleteSupplier(Integer id) {


        supplierService.deleteSupplier(id);


        return "redirect:/suppliers";
    }

}

