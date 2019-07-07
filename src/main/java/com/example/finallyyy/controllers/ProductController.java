package com.example.finallyyy.controllers;

import com.example.finallyyy.models.Product;
import com.example.finallyyy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"", "/"})
    public String getProducts(Model model) {

        List<Product> products = productService.getProducts();

        model.addAttribute("products", products);

        return "products";
    }

    @PostMapping(value = {"/addProduct", "addProduct"})
    public String addProduct(Product product, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors()) {
                System.out.println(allError.getDefaultMessage());
            }
        } else {

            Product savedProduct = productService.saveProduct(product);
        }
        /*
            handling not saved situation
            if (savedProduct.getId() <= 0)
          */


        return "redirect:/products/";
    }

    @RequestMapping(value = {"/editProduct", "/editProduct/"})
    public String editProduct(Product product) {

        productService.updateProduct(product);

        return "redirect:/products";
    }

    @RequestMapping(value = {"/getProduct", "/getProduct/"})
    @ResponseBody
    public Optional<Product> getProduct(Integer id) {


        Optional<Product> product = productService.getProduct(id);
        /*
             handle nullability also
         */

        return product;
    }

    @RequestMapping(value = {"/deleteProduct", "/deleteProduct/"})
    public String deleteProduct(Integer id) {


        productService.deleteProduct(id);


        return "redirect:/products";
    }

}
