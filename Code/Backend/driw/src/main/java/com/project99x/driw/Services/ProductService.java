package com.project99x.driw.Services;

import com.project99x.driw.DTO.ProductDTO;
import com.project99x.driw.Entities.Product;

import java.util.List;

/**
 * interface to hide the product service implementation
 */
public interface ProductService {

    public List<Product> getAllProducts();

    Product getProductById(int id);

    Product getProductByUuid(String uuid);

    ProductDTO purchase(ProductDTO purchasedProduct);

    float calculateTotal(String s, int i, Product product);
}
