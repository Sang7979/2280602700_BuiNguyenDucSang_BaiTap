package com.example.bai7.repository;

import com.example.bai7.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("""
        select p
        from Product p
        left join p.category c
        where (:keyword = '' or lower(p.name) like lower(concat('%', :keyword, '%')))
          and (:categoryId is null or c.id = :categoryId)
    """)
    Page<Product> searchProducts(@Param("keyword") String keyword,
                                 @Param("categoryId") Integer categoryId,
                                 Pageable pageable);
}