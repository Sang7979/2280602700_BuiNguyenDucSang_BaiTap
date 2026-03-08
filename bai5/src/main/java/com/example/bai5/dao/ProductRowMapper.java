package com.example.bai5.dao;

import com.example.bai5.model.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setImage(rs.getString("image"));
        p.setPrice(rs.getLong("price"));

        Category c = new Category();
        c.setId(rs.getInt("category_id"));
        c.setName(rs.getString("category_name"));

        p.setCategory(c);

        return p;
    }
}