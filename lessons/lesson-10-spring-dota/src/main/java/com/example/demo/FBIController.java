package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author derovi
 */

@RestController
public class FBIController {
    @GetMapping("/secret")
    public String secret() {
        return "Gde bil Roma v sredu na pare po MCHA? Uspeshno prohodil sobes!";
    }
}
