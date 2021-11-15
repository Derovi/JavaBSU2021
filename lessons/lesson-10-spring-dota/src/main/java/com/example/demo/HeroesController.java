package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author derovi
 */

@Controller
@RequestMapping("/heroes")
public class HeroesController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HeroService heroService;

    @GetMapping
    String getHeroes(Model model) {
        model.addAttribute("heroes", heroService.getHeroes());
        return "heroes";
    }


}
