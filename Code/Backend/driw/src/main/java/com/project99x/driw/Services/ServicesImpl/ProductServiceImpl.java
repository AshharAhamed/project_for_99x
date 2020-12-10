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

/**
 * service implementation file to do the business logic of the application
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    private static final int minNoOfCartonForDiscount = 3;

    /**
     * JPA repository declaration to interact with the DB
     */
    @Autowired
    ProductRepository productRepository;

    /**
     * method for get all product as List
     *
     * @return
     */
    @Override
    public List<Product> getAllProducts() {
        logger.debug("Request all Product from DB");
        List<Product> products = productRepository.findAll();
        if (products == null || products.isEmpty()) {
            System.out.println("List is Nul");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println(products.get(i).getName());
            }
        }
        productRepository.flush();
        return products;
    }

    /**
     * this method is for get product by it's ID
     * developed initially to get data from db
     * then access the data by its UUID inorder to the best practice
     *
     * @param id
     * @return
     */
    @Override
    public Product getProductById(int id) {
        logger.debug("Request a Product using its ID");
        Product product = productRepository.getOne(id);

        return product;
    }

    /**
     * method for the get product by it's UUID
     * Best practice
     *
     * @param uuid
     * @return
     */
    @Override
    public Product getProductByUuid(String uuid) {
        logger.debug("Request a Product using its UUID");
        System.out.println("Greetings from Spring Boot! inside the getProductByUuid");
        Product product = productRepository.findByUuid(uuid);
        return product;
    }

    /**
     * this is the method which is invocation by the controller when price calculation request came from the client
     *
     * @param purchasedProduct
     * @return
     */
    @Override
    public ProductDTO purchase(ProductDTO purchasedProduct) {

        Product product = this.getProductByUuid(purchasedProduct.getUuid());
        if (product == null) {
            purchasedProduct = null;
        } else {
            purchasedProduct.setTotal(this.calculateTotal(purchasedProduct.getUuid(), purchasedProduct.getUnitPurchased(), product));
        }

        return purchasedProduct;
    }

    /**
     * this is the calculation method to get final price
     * return type is float
     *
     * @param uuid
     * @param purchaseQty
     * @param product
     * @return
     */
    public float calculateTotal(String uuid, int purchaseQty, Product product) {
        logger.debug("Calculation method is start to process");
        /*Product product = this.getProductByUuid(uuid);*/
        float totalPrice = (float) 0.0;
        float unitPrice = (float) 0.0;
        float cartonPrice = product.getCartonPrice();
        int unitPerCarton = product.getUnitPerCarton();
        int numberOfPurchasedCarton = 0;

        if (purchaseQty < product.getUnitPerCarton()) {
            unitPrice = (cartonPrice + (cartonPrice * 30 / 100)) / unitPerCarton;
            totalPrice = unitPrice * purchaseQty;
        } else {
            logger.debug("calculateTotal function inside 1st else the getProductByUuid");

            int nbrBalancedUnit = purchaseQty % unitPerCarton;
            System.out.println("Number of balanced unit : " + nbrBalancedUnit);

            numberOfPurchasedCarton = (purchaseQty - (purchaseQty % unitPerCarton)) / unitPerCarton;

            logger.debug("numberOfPurchasedCarton" + numberOfPurchasedCarton);
            if (numberOfPurchasedCarton >= minNoOfCartonForDiscount) {
                logger.debug("calculateTotal function inside 2nd if in the calculateTotal");
                float finalPriceOfCarton = cartonPrice - (cartonPrice * 10 / 100);
                logger.debug("cartonPrice: " + cartonPrice);
                logger.debug("finalPriceOfCarton" + finalPriceOfCarton);

                unitPrice = finalPriceOfCarton / unitPerCarton;
                logger.debug("unitPrice : " + unitPrice);
                totalPrice = (finalPriceOfCarton * numberOfPurchasedCarton) + (unitPrice * nbrBalancedUnit);
            } else {
                unitPrice = (cartonPrice + (cartonPrice * 30 / 100)) / unitPerCarton;
                totalPrice = unitPrice * purchaseQty;
            }
        }

        return totalPrice;
    }
}
