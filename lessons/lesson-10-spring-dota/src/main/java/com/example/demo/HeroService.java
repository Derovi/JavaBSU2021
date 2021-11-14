package com.example.demo;

import com.example.demo.model.Hero;
import com.example.demo.repositories.HeroesRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author derovi
 */

@Service
public class HeroService {
//    @Getter
//    private Map<Integer, Hero> heroes = new HashMap<>();
    public Iterable<Hero> getHeroes() {
        return repository.findAll();
    }

    @Autowired
    HeroesRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void fetch() {
        System.out.println("fetching pudge");
//        heroes.clear();
        var newHeroes = restTemplate.getForObject(
                "https://api.opendota.com/api/heroStats",
                Hero[].class);
        repository.saveAll(Arrays.asList(newHeroes));
    }
}
