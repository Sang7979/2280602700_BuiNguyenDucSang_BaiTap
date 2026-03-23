package com.example.bai7.service;

import com.example.bai7.model.Product;
import com.example.bai7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(String keyword, Integer categoryId, String sort, int page, int size) {
        Sort sortObj = Sort.unsorted();

        if ("priceAsc".equals(sort)) {
            sortObj = Sort.by("price").ascending();
        } else if ("priceDesc".equals(sort)) {
            sortObj = Sort.by("price").descending();
        }

        Pageable pageable = PageRequest.of(page, size, sortObj);

        if (keyword == null) {
            keyword = "";
        }

        return productRepository.searchProducts(keyword.trim(), categoryId, pageable);
    }

    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}