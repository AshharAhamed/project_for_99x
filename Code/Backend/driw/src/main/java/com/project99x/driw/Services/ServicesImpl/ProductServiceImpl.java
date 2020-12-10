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
    private static final int minNoOfCartonForDiscount = 3;
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
        if (product==null){
            purchasedProduct=null;
        }else{
            purchasedProduct.setTotal(this.calculateTotal(purchasedProduct.getUuid(), purchasedProduct.getUnitPurchased(), product));
        }

        return purchasedProduct;
    }

    public float calculateTotal(String uuid, int purchaseQty, Product product){
        /*Product product = this.getProductByUuid(uuid);*/
        float totalPrice = (float) 0.0;
        float unitPrice = (float) 0.0;
        float cartonPrice = product.getCartonPrice();
        int unitPerCarton = product.getUnitPerCarton();
        int numberOfPurchasedCarton = 0;

        if (purchaseQty<product.getUnitPerCarton()){
            unitPrice = (cartonPrice + (cartonPrice * 30/100))/unitPerCarton;
            totalPrice = unitPrice * purchaseQty;
        }else{
            System.out.println("calculateTotal function inside 1st else the getProductByUuid ");
            System.out.println("Name "+product.getName());
            int nbrBalancedUnit =  purchaseQty%unitPerCarton;
            System.out.println("Number of balanced unit : "+nbrBalancedUnit);
            numberOfPurchasedCarton = (purchaseQty - (purchaseQty%unitPerCarton))/unitPerCarton;
            System.out.println("numberOfPurchasedCarton : "+numberOfPurchasedCarton);
            if (numberOfPurchasedCarton >= minNoOfCartonForDiscount){
                System.out.println("calculateTotal function inside 2nd if in the calculateTotal");
                System.out.println("numberOfPurchasedCarton : "+numberOfPurchasedCarton);
                float finalPriceOfCarton = cartonPrice - (cartonPrice*10/100);
                System.out.println("cartonPrice : "+cartonPrice);
                System.out.println("finalPriceOfCarton : "+finalPriceOfCarton);
                unitPrice = finalPriceOfCarton/unitPerCarton;
                System.out.println("unitPrice : "+unitPrice);
                totalPrice = (finalPriceOfCarton * numberOfPurchasedCarton) + (unitPrice * nbrBalancedUnit);
            }else{
                unitPrice = (cartonPrice + (cartonPrice * 30/100))/unitPerCarton;
                totalPrice = unitPrice * purchaseQty;
            }
        }

        return totalPrice;
    }
}
