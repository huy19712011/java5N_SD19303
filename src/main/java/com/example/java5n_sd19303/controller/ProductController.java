package com.example.java5n_sd19303.controller;

import com.example.java5n_sd19303.entity.Product;
import com.example.java5n_sd19303.service.CategoryService;
import com.example.java5n_sd19303.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/products/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "views/new_product";
        }

        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/products/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id) {

        productService.deleteProductById(id);

        return "redirect:/products";
    }

    @GetMapping("/products/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {

        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());

        return "views/update_product";
    }

    @PostMapping("/products/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoryService.getAllCategories());
            return "views/update_product";
        }

        productService.updateProduct(product);

        return "redirect:/products";
    }


}
