package com.example.bai5.controller;

import com.example.bai5.dao.ProductDAO;
import com.example.bai5.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // LIST
    @GetMapping("/products")
    public String listProducts(Model model) {

        model.addAttribute("products", productDAO.getAll());

        return "products";
    }

    // SHOW CREATE FORM
    @GetMapping("/products/create")
    public String showCreateForm(Model model) {

        model.addAttribute("product", new Product());

        return "create";
    }

    // SAVE
    @PostMapping("/products/save")
    public String save(Product product) {

        productDAO.save(product);

        return "redirect:/products";
    }

    // DELETE
    @GetMapping("/products/delete/{id}")
    public String delete(@PathVariable int id) {

        productDAO.delete(id);

        return "redirect:/products";
    }

    // SHOW EDIT FORM
    @GetMapping("/products/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Product product = productDAO.findById(id);

        model.addAttribute("product", product);

        return "edit";
    }

    // UPDATE
    @PostMapping("/products/update")
    public String update(Product product) {

        productDAO.update(product);

        return "redirect:/products";
    }
}