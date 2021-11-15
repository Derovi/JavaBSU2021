package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author derovi
 */

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
