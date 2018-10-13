package com.fareye.ecommerce.managementv2.services.utils;

import com.fareye.ecommerce.managementv2.exceptions.SellerNotFoundException;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class UserHelper {

    private UserRepository userRepository;

    public UserHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new SellerNotFoundException("Seller not found: username - " + username);
        }

        return optionalUser.get();
    }
}
