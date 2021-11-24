package com.Khody31.selfdrivingdatabase;

import com.Khody31.selfdrivingdatabase.model.Ride;
import com.Khody31.selfdrivingdatabase.repositories.RidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    RidesRepository ridesRepository;

    @GetMapping("/")
    String mainPage(Model model) {
        model.addAttribute("rides", ridesRepository.findAll());
        return "main_page";
    }

    @GetMapping("/test")
    String testPage() {
        return "test_page";
    }

    @GetMapping("/admin")
    String adminPage() {
        return "admin_page";
    }

    @PostMapping(value="/add_ride")
    String addRide(@RequestParam String carName, @RequestParam String location) {
        Ride ride = new Ride(0, carName, location);

        ridesRepository.save(ride);
        return "redirect:/admin";
    }
}
