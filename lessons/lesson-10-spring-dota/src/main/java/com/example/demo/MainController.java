package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author derovi
 */

@Controller
public class MainController {

    @GetMapping("/")
    String startPage() {
        return "index";
    }



}
