package com.project99x.driw.Controllers;

import com.project99x.driw.DTO.ProductDTO;
import com.project99x.driw.Entities.Product;
import com.project99x.driw.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity<? extends Object> getProductByUuid(@PathVariable String uuid) {
        System.out.println("Greetings from Spring Boot!");
        Product products = productService.getProductByUuid(uuid);
        System.out.println(products);

        if (products == null){
            return new ResponseEntity<>("Product not found", HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<Product>(products, HttpStatus.OK);
        }

    }

    @PostMapping(path = "/purchase")
    public ResponseEntity<ProductDTO> purchaseProducct(@RequestBody ProductDTO purchasedProduct){

        ProductDTO productDTO = productService.purchase(purchasedProduct);
        if (productDTO==null){
            return new ResponseEntity<ProductDTO>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
        }
//        return productDTO;
    }
}
