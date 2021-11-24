package com.Khody31.selfdrivingdatabase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    String mainPage() {
        return "main_page";
    }
}
