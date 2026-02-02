package com.example.BTtuan4.Service;

import com.example.BTtuan4.Model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.*;

@Service
public class ProductService {

    private List<Product> list = new ArrayList<>();

    public List<Product> getAll() {
        return list;
    }

    public Product get(int id) {
        return list.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Product p) {
        int maxId = list.stream().mapToInt(Product::getId).max().orElse(0);
        p.setId(maxId + 1);
        list.add(p);
    }

    public void update(Product product) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == product.getId()) {
                list.set(i, product);
                return;
            }
        }
    }

    public void delete(int id) {
        list.removeIf(p -> p.getId() == id);
    }

    // ✅ LƯU ẢNH
    public void updateImage(Product p, MultipartFile image) {
        try {
            Path uploadDir = Paths.get("target/classes/static/images");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Files.copy(image.getInputStream(),
                    uploadDir.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            p.setImage(fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

