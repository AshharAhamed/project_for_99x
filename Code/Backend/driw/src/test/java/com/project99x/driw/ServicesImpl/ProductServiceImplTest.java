package com.project99x.driw.ServicesImpl;

import com.project99x.driw.DTO.ProductDTO;
import com.project99x.driw.Entities.Product;
import com.project99x.driw.Services.ProductService;
import com.project99x.driw.Services.ServicesImpl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }

    @Autowired
    private ProductService productService;

    /*@Mock
    ProductServiceImpl productService;

    @BeforeAll
    public  void init() {
        MockitoAnnotations.initMocks(this);
    }*/

    @Test
    public void testCalculateTotal(){
        Product product = new Product(1,"f261b978-dfb2-40e7-9c65-e83edda7ce00", "Penguin-ear",20, 100, 175);

        float price = productService.calculateTotal("f261b978-dfb2-40e7-9c65-e83edda7ce00",60, product);

        Assertions.assertEquals(price,472.5);

    }
}
