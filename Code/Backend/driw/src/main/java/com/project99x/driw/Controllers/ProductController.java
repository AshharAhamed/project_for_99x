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

/*@CrossOrigin(origins = {"http://localhost:3000", "https://neo-bus-frontend.herokuapp.com"})*/

/**
 * REST controller
 * enable crossorgin for http://localhost:3000 react app
 */
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/product")
public class ProductController {

    /**
     * declaration of product service implementation using its interface
     */
    @Autowired
    private ProductService productService;

    /**
     * REST end-point for check the controller
     * @return
     */
    @GetMapping("/")
    public String index() {
        System.out.println("Greetings from Spring Boot!");

        return "Hello All";
    }

    /**
     * REST end-point for get all product
     * @return
     */
    @GetMapping("/all")
    public List<Product> getAllProduct() {
        System.out.println("Greetings from Spring Boot!");
        List<Product> products = productService.getAllProducts();
        return products;
    }

    /**
     *
     * get Product by ID end-point
     *
     * @param id
     * @return
     */
    @GetMapping("/id")
    public Product getProductById(@RequestParam int id) {
        System.out.println("Greetings from Spring Boot!");
        Product products = productService.getProductById(id);
        return products;
    }

    /**
     * get pri]oduct by it's UUID end-point
     * @param uuid
     * @return
     */
    @GetMapping("/{uuid}")
    public HttpEntity<? extends Object> getProductByUuid(@PathVariable String uuid) {
        System.out.println("Greetings from Spring Boot!");
        Product products = productService.getProductByUuid(uuid);
        System.out.println(products);

        if (products == null){
            return new ResponseEntity<>("Product not found", HttpStatus.NO_CONTENT);
//            return products;
        }else{
            return new ResponseEntity<Product>(products, HttpStatus.OK);
//            return products;
        }

    }

    @GetMapping("/test/{uuidt}")
    public Product getProductByUuidforTest(@PathVariable String uuidt) {
        System.out.println("Greetings from Spring Boot!");
        Product products = productService.getProductByUuid(uuidt);
        System.out.println(products);

        if (products == null){
//            return new ResponseEntity<>("Product not found", HttpStatus.NO_CONTENT);
            return products;
        }else{
//            return new ResponseEntity<Product>(products, HttpStatus.OK);
            return products;
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
