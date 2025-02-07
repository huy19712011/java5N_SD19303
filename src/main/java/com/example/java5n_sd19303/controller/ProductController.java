package com.example.java5n_sd19303.controller;

import com.example.java5n_sd19303.entity.Product;
import com.example.java5n_sd19303.service.CategoryService;
import com.example.java5n_sd19303.service.ProductService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {

        // get data from DB
        List<Product> products = productService.getAllProducts();

        // add to model
        model.addAttribute("products", products);

        // return view
        return "views/products";
    }

    @GetMapping("/products/showNewProductForm")
    public String showProductForm(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "views/new_product";
    }

}
