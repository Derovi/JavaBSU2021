package com.Khody31.selfdrivingdatabase;

import com.Khody31.selfdrivingdatabase.model.Maneuver;
import com.Khody31.selfdrivingdatabase.model.Ride;
import com.Khody31.selfdrivingdatabase.repositories.ManeuverRepository;
import com.Khody31.selfdrivingdatabase.repositories.RidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    RidesRepository ridesRepository;

    @Autowired
    ManeuverRepository maneuverRepository;

    List<Maneuver> maneuvers;

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
    String adminPage(Model model) {
        model.addAttribute("maneuvers", maneuvers);
        return "admin_page";
    }

    @PostMapping(value = "/add_ride")
    String addRide(@RequestParam String carName, @RequestParam String location) {
        Ride ride = new Ride();
        ride.setCarName(carName);
        ride.setLocation(location);

        ride.setManeuverList(maneuvers);
        maneuvers = null;

        ridesRepository.save(ride);
        return "redirect:/admin";
    }

    @PostMapping(value = "/add_maneuver")
    String addManeuver(@RequestParam String name, @RequestParam Integer duration) {
        if (maneuvers == null) {
            maneuvers = new ArrayList<>();
        }

        Maneuver maneuver = new Maneuver();
        maneuver.setName(name);
        maneuver.setDuration(duration);

        maneuvers.add(maneuver);
        maneuverRepository.save(maneuver);

        return "redirect:/admin";
    }
}
