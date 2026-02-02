package com.example.BTtuan4.Controller;

import com.example.BTtuan4.Model.Product;
import com.example.BTtuan4.Service.CategoryService;
import com.example.BTtuan4.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listproduct", productService.getAll());
        return "product/products";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid Product product,
            BindingResult result,
            @RequestParam("category_id") int categoryId,
            @RequestParam("imageProduct") MultipartFile image,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/create";
        }

        product.setCategory(categoryService.get(categoryId));
        productService.updateImage(product, image);
        productService.add(product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }

    // HIỂN THỊ FORM EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Product product = productService.get(id);
        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());

        return "product/edit";
    }

    // XỬ LÝ SUBMIT EDIT
    @PostMapping("/edit")
    public String edit(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            @RequestParam("category_id") int categoryId,
            @RequestParam("imageProduct") MultipartFile image,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/edit";
        }

        product.setCategory(categoryService.get(categoryId));

        // chỉ update ảnh nếu có chọn ảnh mới
        if (!image.isEmpty()) {
            productService.updateImage(product, image);
        }

        productService.update(product);

        return "redirect:/products";
    }

}
