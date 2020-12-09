package com.project99x.driw.Repositories;

import com.project99x.driw.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByUuid(String uuid);
}
