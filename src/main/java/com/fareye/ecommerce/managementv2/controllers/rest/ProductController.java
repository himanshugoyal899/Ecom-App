package com.fareye.ecommerce.managementv2.controllers.rest;

import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import com.fareye.ecommerce.managementv2.services.ProductService;
import com.fareye.ecommerce.managementv2.services.utils.SellerForProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/seller/{username}/products")
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping(path = "/api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping(path = "/admin/{adminName}/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    /*@GetMapping(path = "/api/products/brands")
    public List<String> getAllBrandNames() {
        return productService.getAllBrandNames();
    }*/



    @GetMapping(path = "/api/products/brand={brandName}")
    public List<Product> getProductsByBrand(@PathVariable String brandName) {
        return productService.getProductsByBrand(brandName);
    }

    @GetMapping(path = "/api/products/category={categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping(path = "/api/products/{productId}")
    public Product getProductDetails(@PathVariable Long productId) {
        return productService.getProductDetails(productId);
    }

    @GetMapping(path = "api/products/{productId}/sellers")
    public List<SellerForProduct> getSellersDetailsForProduct(@PathVariable Long productId) {
        return productService.getSellersDetailsForProduct(productId);
    }
}

