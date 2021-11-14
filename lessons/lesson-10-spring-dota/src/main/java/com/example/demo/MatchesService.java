package com.example.demo;

import com.example.demo.model.Hero;
import com.example.demo.model.Match;
import com.example.demo.model.Player;
import com.example.demo.repositories.MatchesRepository;
import com.example.demo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author derovi
 */


@Service
public class MatchesService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MatchesRepository matchesRepository;

    @Autowired
    PlayerRepository playerRepository;

    public void fetch(int matchId) {
        var match= restTemplate.getForObject("https://api.opendota.com/api/matches/" + matchId, Match.class);
        List<Player> players = match.getPlayers();
        match.setPlayers(Collections.emptyList());
        matchesRepository.save(match);
        for (Player p: players) {
            p.setMatch(match);
            playerRepository.save(p);
        }
    }
}
