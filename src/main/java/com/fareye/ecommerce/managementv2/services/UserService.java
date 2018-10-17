package com.fareye.ecommerce.managementv2.services;

import com.fareye.ecommerce.managementv2.exceptions.UserNotFoundException;
import com.fareye.ecommerce.managementv2.exceptions.UserAlreadyExists;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import com.fareye.ecommerce.managementv2.services.utils.UserHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Create admin.
     * @param admin holds admin details.
     * @return admin details saved.
     */
    public User createAdmin(@Valid User admin) {

        if (!admin.getRole().equals("Admin")) {
            log.debug("Not an admin : " + admin);
            return null;
        }


        User savedUser = null;

        try {
            savedUser = userRepository.save(admin);
            log.info("Admin added to database : " + admin);
        } catch(Exception ex) {
            log.debug("Failed to save user : " + admin);
            throw ex;
        }

        return savedUser;
    }

    /**
     * Create a seller
     * @param seller to be added to database.
     * @return added seller
     */
    public User createSeller(User seller) {
        if (!seller.getRole().equals("Seller")) {
            log.debug("Not a seller : " + seller);
            return null;
        }


        User savedUser = null;

        try {
            savedUser = userRepository.save(seller);
            log.info("Seller added to database : " + seller);
        } catch(Exception ex) {
            log.debug("Failed to save user : " + seller);
            throw ex;
        }

        return savedUser;
    }


    /**
     * Get all users (including sellers and admin).
     * @return list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get all sellers.
     * @return list of all sellers.
     */
    public List<User> getAllSellers() {
        return userRepository.findByRole("Seller");
    }

    /**
     * Get all admins.
     * @return list of all admins.
     */
    public List<User> getAllAdmins() {
        return userRepository.findByRole("Admin");
    }

    /**
     * Enable or disable a seller
     * @param sellerId of seller
     * @param enabled valed value to be put
     * @return changed seller settings.
     */
    public User enableOrDisableSeller(Long sellerId, Boolean enabled) {
        User seller = userRepository.findById(sellerId).get();

        if (seller.getRole().equals("Seller")) {
            seller.setEnabled(enabled);
        }

        return userRepository.save(seller);
    }

    /**
     * Get seller with username
     * @param sellerUsername of seller
     * @return details of seller
     */
    public User getSeller(String sellerUsername) {
        User seller = getUserByUsername(sellerUsername);

        if (!seller.getRole().equals("Seller")) {
            throw new UserNotFoundException("Seller not found: username - " + sellerUsername);
        }

        return seller;
    }

    /**
     * Update seller details.
     * @param username of seller to be updated
     * @param givenSeller details to be updated
     * @return updated details
     */
    public User updateSeller(String username, User givenSeller) {
        User seller = getUserByUsername(username);

        if (!seller.getUsername().equals(givenSeller.getUsername())) {
            throw new UserAlreadyExists("User already exists by this name: username - " + username);
        }

        seller.setEmailId(givenSeller.getEmailId());
        seller.setFirstName(givenSeller.getFirstName());
        seller.setLastName(givenSeller.getLastName());
        return userRepository.save(seller);
    }

    /**
     * Get admin details by username
     * @param username of admin.
     * @return admin details
     */
    public User getAdmin(String username) {
        User admin = getUserByUsername(username);

        if (!admin.getRole().equals("Admin")) {
            throw new UserNotFoundException("Admin not found: username - " + username);
        }

        User adminToBeSent = null;
        try {
            adminToBeSent = (User)admin.clone();
            adminToBeSent.setPassword("");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return adminToBeSent;
    }

    /**
     * Load a user during login process
     * @param username of user
     * @return all user details
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);

        return new UserDetailsImpl(user);
    }

    /**
     * Delete a seller with seller id sellerId
     * @param sellerId of seller
     */
    public void deleteSeller(Long sellerId) {
        userRepository.deleteById(sellerId);
    }


    /**
     * Get user by username.
     * @param username of the user.
     * @return user details.
     */
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found: username - " + username);
        }

        User user = optionalUser.get();

        return user;
    }
}