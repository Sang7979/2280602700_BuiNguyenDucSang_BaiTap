package com.example.bai7.controller;

import com.example.bai7.model.Product;
import com.example.bai7.repository.CategoryRepository;
import com.example.bai7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listProducts(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "") String sort,
                               @RequestParam(required = false) Integer categoryId) {

        Page<Product> productPage = productService.getProducts(keyword, categoryId, sort, page, 5);

        model.addAttribute("products", productPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort", sort);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryRepository.findAll());

        return "product/list";
    }
}