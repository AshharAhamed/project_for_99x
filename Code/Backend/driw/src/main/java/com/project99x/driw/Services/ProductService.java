package com.project99x.driw.Services;

import com.project99x.driw.DTO.ProductDTO;
import com.project99x.driw.Entities.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    Product getProductById(int id);

    Product getProductByUuid(String uuid);

    ProductDTO purchase(ProductDTO purchasedProduct);
}