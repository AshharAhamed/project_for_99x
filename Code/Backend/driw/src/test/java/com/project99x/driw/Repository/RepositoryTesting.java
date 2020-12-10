package com.project99x.driw.Repository;

import com.project99x.driw.Entities.Product;
import com.project99x.driw.Repositories.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTesting {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByUuid(){
        Product product = productRepository.findByUuid("f261b978-dfb2-40e7-9c65-e83edda7ce00");

        Assertions.assertEquals(product.getName(),"Penguin-ears");
    }
}
