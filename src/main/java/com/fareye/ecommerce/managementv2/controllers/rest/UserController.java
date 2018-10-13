package com.fareye.ecommerce.managementv2.controllers.rest;

import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/admin")
    public User createAdmin(@Valid @RequestBody User admin) {
        return userService.createAdmin(admin);
    }

    @GetMapping(path = "/users/admin/profile")
    protected User adminDetails(HttpServletRequest request) {
            Principal principal = request.getUserPrincipal();
            String username = principal.getName();
            return userService.getAdmin(username);
    }

    @GetMapping(path = "/users/admin/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/admin/{adminName}")
    public User getAdminDetails(@PathVariable String adminName) {
        return userService.getAdmin(adminName);
    }

    @GetMapping(path = "/admin/{adminName}/sellers")
    public List<User> getAllSellers() {
        return userService.getAllSellers();
    }

    @GetMapping(path = "/admin/{adminName}/admins")
    public List<User> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @PutMapping(path = "/admin/{adminName}/sellers/{sellerUsername}")
    public User enableOrDisableSeller(@PathVariable String sellerUsername) {
        return userService.enableOrDisableSeller(sellerUsername);
    }

    @PostMapping(path = "/sellers")
    public User createSeller(@RequestBody User user) {
        return userService.createSeller(user);
    }

    @PutMapping(path = "/sellers/{username}")
    public User updateSeller(@PathVariable String username,
                             @RequestBody User seller) {
        return userService.updateSeller(username, seller);
    }
}
