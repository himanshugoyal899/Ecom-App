package com.fareye.ecommerce.managementv2.controllers.rest;

import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/api/admin/profile")
    protected User getAdminDetails(HttpServletRequest request) {
            Principal principal = request.getUserPrincipal();
            String username = principal.getName();
            return userService.getAdmin(username);
    }

    /**
     *
     * @return
     */
    @GetMapping(path = "api/admin/sellers")
    public List<User> getAllSellers() {
        return userService.getAllSellers();
    }

    /**
     * Delete seller using seller id
     * @param sellerId of seller to be deleted
     */

    @DeleteMapping(path = "api/admin/sellers")
    public void deleteSeller(@RequestParam Long sellerId) {
        userService.deleteSeller(sellerId);
    }

    @PutMapping(path = "api/admin/sellers/{sellerId}")
    public void enableOrDisableSeller(@PathVariable Long sellerId,
                                      @RequestBody User user) {
        userService.enableOrDisableSeller(sellerId, user.getEnabled());
    }

    /**
     * Returns logged in seller profile.
     */
    @GetMapping(path = "/api/seller/profile")
    protected User getSellerDetails(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.getSeller(username);
    }

    /**
     * updates seller information.
     */
    @PutMapping(path = "api/seller/{sellerUsername}")
    public User updateSeller(@PathVariable String sellerUsername,
                            @RequestBody User user) {
        return userService.updateSeller(sellerUsername, user);
    }

}
