package com.example.demo;

import com.example.demo.model.Match;
import com.example.demo.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author derovi
 */

// matches?ids=5765432,5765433

@Controller
@RequestMapping("/matches")
public class MatchesController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HeroService heroService;

    @GetMapping
    String getMatches(Model model, @RequestParam List<Long> ids) {
        List<Match> matches = ids.stream()
                                 .map(id -> restTemplate.getForObject("https://api.opendota.com/api/matches/" + id,
                                         Match.class))
                                 .collect(Collectors.toList());
        model.addAttribute("matches", matches);
        return "matches";
    }

    @GetMapping("{id}")
    String getMatch(Model model, @PathVariable Long id) {
        // var match = restTemplate.getForObject("https://api.opendota.com/api/matches/" + id, Match.class);
        // model.addAttribute("match", match);
        // Player ruiner = match.getPlayers().stream().min(Comparator.comparing(Player::ruinerRating)).get();
        // String heroIcon = ruiner.getHero().getIcon();
        // model.addAttribute("heroIcon", heroIcon);
        return "match";
    }

}
