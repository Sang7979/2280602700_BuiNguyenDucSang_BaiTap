package com.example.bai5.dao;

import com.example.bai5.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // GET ALL
    public List<Product> getAll() {

        String sql =
                "SELECT p.id, p.name, p.image, p.price, " +
                        "c.id AS category_id, " +
                        "c.name AS category_name " +
                        "FROM product p " +
                        "INNER JOIN category c " +
                        "ON p.category_id = c.id";

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    // FIND BY ID (EDIT)
    public Product findById(int id) {

        String sql =
                "SELECT p.id, p.name, p.image, p.price, " +
                        "c.id AS category_id, " +
                        "c.name AS category_name " +
                        "FROM product p " +
                        "INNER JOIN category c ON p.category_id = c.id " +
                        "WHERE p.id=?";

        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
    }

    // SAVE
    public void save(Product p) {

        String sql = "INSERT INTO product(name, price, image, category_id) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                p.getName(),
                p.getPrice(),
                p.getImage(),
                p.getCategory().getId());
    }

    // UPDATE
    public void update(Product p) {

        String sql = "UPDATE product SET name=?, price=?, image=?, category_id=? WHERE id=?";

        jdbcTemplate.update(sql,
                p.getName(),
                p.getPrice(),
                p.getImage(),
                p.getCategory().getId(),
                p.getId());
    }

    // DELETE
    public void delete(int id) {

        String sql = "DELETE FROM product WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }
}