package com.example.demo.repositories;

import com.example.demo.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author derovi
 */

@Repository
public interface HeroesRepository extends CrudRepository<Hero, Integer> {}
