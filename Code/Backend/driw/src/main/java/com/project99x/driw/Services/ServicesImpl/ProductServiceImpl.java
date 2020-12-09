package com.project99x.driw.Services.ServicesImpl;

import com.project99x.driw.DTO.ProductDTO;
import com.project99x.driw.Entities.Product;
import com.project99x.driw.Repositories.ProductRepository;
import com.project99x.driw.Services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        logger.debug("Request all Product from DB");
        List<Product> products = productRepository.findAll();
        if (products == null || products.isEmpty()){
            System.out.println("List is Nul");
        }else{
            for (int i=0; i<products.size();i++ ){
                System.out.println(products.get(i).getName());
            }
        }
        productRepository.flush();
        return products;
    }

    @Override
    public Product getProductById(int id) {
        logger.debug("Request a Product using its ID");
        Product product = productRepository.getOne(id);

        return product;
    }

    @Override
    public Product getProductByUuid(String uuid) {
        logger.debug("Request a Product using its UUID");
        System.out.println("Greetings from Spring Boot! inside the getProductByUuid");
        Product product = productRepository.findByUuid(uuid);
        return product;
    }

    @Override
    public ProductDTO purchase(ProductDTO purchasedProduct) {

        Product product = this.getProductByUuid(purchasedProduct.getUuid());
        return null;
    }

    public Float calculateTotal(){


        return null;
    }
}
