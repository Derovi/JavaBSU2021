package com.Khody31.selfdrivingdatabase;

import com.Khody31.selfdrivingdatabase.model.Comment;
import com.Khody31.selfdrivingdatabase.model.Maneuver;
import com.Khody31.selfdrivingdatabase.model.Ride;
import com.Khody31.selfdrivingdatabase.repositories.CommentsRepository;
import com.Khody31.selfdrivingdatabase.repositories.ManeuverRepository;
import com.Khody31.selfdrivingdatabase.repositories.RidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    RidesRepository ridesRepository;

    @Autowired
    ManeuverRepository maneuverRepository;

    @Autowired
    CommentsRepository commentsRepository;

    List<Maneuver> maneuvers;

    @GetMapping("/")
    String mainPage(Model model) {
        model.addAttribute("rides", ridesRepository.findAll());
        return "main_page";
    }

    @PostMapping("/clear")
    String clear() {
        ridesRepository.deleteAll();
        maneuverRepository.deleteAll();
        maneuvers = null;
        return "redirect:/";
    }

    @GetMapping("/admin")
    String adminPage(Model model) {
        model.addAttribute("maneuvers", maneuvers);
        return "admin_page";
    }

    @PostMapping(value = "/add_ride")
    String addRide(
            @RequestParam String carName,
            @RequestParam String location,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Ride ride = new Ride();
        ride.setCarName(carName);
        ride.setLocation(location);
        ride.setDate(date);

        ride.setManeuverList(maneuvers);
        maneuvers = null;

        ridesRepository.save(ride);
        System.out.println(ride.getId());
        return "redirect:/admin";
    }

    @PostMapping(value = "/add_maneuver")
    String addManeuver(
            @RequestParam String name,
            @RequestParam Integer duration) {
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

    @GetMapping("/ride")
    String ride(Model model, @RequestParam Integer id) {
        model.addAttribute("rides", ridesRepository.findAllById(List.of(id)));
        return "ride_page";
    }

    @PostMapping("/rate")
    String rate(
            Model model,
            @RequestParam Double score,
            @RequestParam Integer maneuverId,
            @RequestParam Integer rideId) {
        Optional<Maneuver> maneuver = maneuverRepository.findById(maneuverId);
        maneuver.ifPresent(value -> value.addScore(score));
        maneuver.ifPresent(value -> maneuverRepository.save(value));
        return ride(model, rideId);
    }

    @PostMapping("/add_comment")
    String addComment(
            Model model,
            @RequestParam String commentBody,
            @RequestParam Integer rideId) {
        Comment comment = new Comment();
        comment.setComment(commentBody);
        commentsRepository.save(comment);

        System.out.println(comment.getId());

        Ride cur_ride = ridesRepository.findById(rideId).get();
        cur_ride.addComment(comment);

        return ride(model, rideId);
    }
}
