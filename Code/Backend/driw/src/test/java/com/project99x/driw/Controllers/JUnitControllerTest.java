package com.project99x.driw.Controllers;

import com.project99x.driw.Entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

//import org.junit;

public class JUnitControllerTest {

    @Test
    public void testProductController(){

        ProductController productController = new ProductController();
        String response =  productController.index();
        Assertions.assertEquals(response,"Hello All");
    }
}
