package com.fareye.ecommerce.managementv2.controllers.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = "/admin")
    public String adminHome() {
        return "admin";
    }

    @GetMapping(path = "/seller")
    public String sellerHome() {
        return "seller";
    }
}
