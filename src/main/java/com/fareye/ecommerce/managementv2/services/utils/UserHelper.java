package com.fareye.ecommerce.managementv2.services.utils;

import com.fareye.ecommerce.managementv2.exceptions.UserNotFoundException;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserHelper {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("Seller not found: username - " + username);
        }

        return optionalUser.get();
    }
}
