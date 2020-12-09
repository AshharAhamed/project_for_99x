package com.project99x.driw.Controllers;

import com.project99x.driw.DTO.ProductDTO;
import com.project99x.driw.Entities.Product;
import com.project99x.driw.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"http://localhost:3000", "https://neo-bus-frontend.herokuapp.com"})
@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/hello")
    public List<Product> getAllProduct() {
        System.out.println("Greetings from Spring Boot!");
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/id")
    public Product getProductById(@RequestParam int id) {
        System.out.println("Greetings from Spring Boot!");
        Product products = productService.getProductById(id);
        return products;
    }

    @GetMapping("/{uuid}")
    public Product getProductByUuid(@PathVariable String uuid) {
        System.out.println("Greetings from Spring Boot!");
        Product products = productService.getProductByUuid(uuid);
        System.out.println(products);
        return products;
    }

    @PostMapping(path = "/add")
    public ProductDTO purchaseProducct(@RequestBody ProductDTO purchasedProduct){

        ProductDTO productDTO = productService.purchase(purchasedProduct);
        return null;
    }
}
