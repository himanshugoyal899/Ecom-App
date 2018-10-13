package com.fareye.ecommerce.managementv2.services;

import com.fareye.ecommerce.managementv2.exceptions.SellerNotFoundException;
import com.fareye.ecommerce.managementv2.exceptions.UserAlreadyExists;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import com.fareye.ecommerce.managementv2.services.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private UserHelper userHelper;

    public UserService() {
        this.userHelper = new UserHelper(userRepository);
    }

    public User createAdmin(User admin) {
        admin.setRole("Admin");
        return userRepository.save(admin);
    }

    public User createSeller(User admin) {
        admin.setRole("Seller");
        return userRepository.save(admin);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllSellers() {
        return userRepository.findByRole("Seller");
    }

    public List<User> getAllAdmins() {
        return userRepository.findByRole("Admin");
    }

    public User enableOrDisableSeller(String sellerUsername) {
        User seller = userHelper.getUserByUsername(sellerUsername);

        if (seller.getRole().equals("Seller")) {
            seller.setEnabled(!seller.getEnabled());
        }
        return userRepository.save(seller);
    }

    public User getSeller(String sellerUsername) {
        User seller = userHelper.getUserByUsername(sellerUsername);

        if (!seller.getRole().equals("Seller")) {
            throw new SellerNotFoundException("Seller not found: username - " + sellerUsername);
        }

        return seller;
    }

    public User updateSeller(String username, User givenSeller) {
        User seller = userHelper.getUserByUsername(username);

        if (!seller.getUsername().equals(givenSeller.getUsername())) {
            throw new UserAlreadyExists("User already exists by this name: username - " + username);
        }

        seller.setEmailId(givenSeller.getEmailId());
        seller.setFirstName(givenSeller.getFirstName());
        seller.setLastName(givenSeller.getLastName());
        seller.setUsername(givenSeller.getLastName());
        seller.setPassword(givenSeller.getPassword());

        return userRepository.save(seller);
    }

    public User getAdmin(String username) {
        User admin = userHelper.getUserByUsername(username);

        if (!admin.getRole().equals("Admin")) {
            throw new SellerNotFoundException("Admin not found: username - " + username);
        }

        return admin;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(s);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("Username not found: username - " + s);
        }

        User user = optionalUser.get();

        return new UserDetailsImpl(user);
    }
}