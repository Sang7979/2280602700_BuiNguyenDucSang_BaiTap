package com.example.bai7.controller;

import com.example.bai7.model.Product;
import com.example.bai7.repository.CategoryRepository;
import com.example.bai7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {

        model.addAttribute("products", productService.getProducts(keyword, null, "", page, 5));
        model.addAttribute("keyword", keyword);
        return "admin/product/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("currentCategoryId", null);
        return "admin/product/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product,
                       @RequestParam(required = false) Integer categoryId) {

        if (categoryId != null) {
            categoryRepository.findById(categoryId).ifPresent(product::setCategory);
        } else {
            product.setCategory(null);
        }

        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("currentCategoryId",
                product != null && product.getCategory() != null ? product.getCategory().getId() : null);
        return "admin/product/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}