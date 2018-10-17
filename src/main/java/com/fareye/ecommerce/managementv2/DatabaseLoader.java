package com.fareye.ecommerce.managementv2;

import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.InventoryRepository;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {

        User seller1 = new User(2L, "vibsharma", "password", "Vibhor", "Sharma", "Seller", true, "vibsharma@gmail.com");
        User seller2 = new User(3L, "vidsharma", "password", "Vidhi", "Sharma", "Seller", true, "vidsharma@gmail.com");

        userRepository.save(new User(1L, "vbvsharma", "password", "Vaibhav", "Sharma", "Admin", true, "vbvsharma@gmail.com"));
        userRepository.save(seller1);
        userRepository.save(seller2);

        Product product1 = new Product( "Laptops", "MacBook Pro", "Apple", "Best for devs.", "url1");
        Product product2 = new Product( "Laptops", "R17", "Alienware", "Best for gamers.", "url2");
        Product product3 = new Product( "Computers", "VivoBook R542UQ", "ASUS", "Best in range.", "url3");
        Product product4 = new Product( "Electronics", "Washing Machine", "LG", "Best for washing.", "url4");
        Product product5 = new Product( "Smartphones", "Redmi Note 6", "Xiaomi", "Budget Smartphone.", "url5");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);

        /*inventoryRepository.save(new Inventory(1L, 20, 179999.00, seller1, product1));
        inventoryRepository.save(new Inventory(2L, 10, 299999.00, seller1, product3));
        inventoryRepository.save(new Inventory(3L, 5, 299999.00, seller2, product3));
        inventoryRepository.save(new Inventory(4L, 10, 1002.00, seller1, product2));
        inventoryRepository.save(new Inventory(5L, 9, 20000.00, seller1, product4));
        inventoryRepository.save(new Inventory(6L, 13, 3000.00, seller1, product5));
        inventoryRepository.save(new Inventory(7L, 13, 3000.00, seller2, product4));*/
    }
}
