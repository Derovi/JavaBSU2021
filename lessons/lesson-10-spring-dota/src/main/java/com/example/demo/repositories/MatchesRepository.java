package com.example.demo.repositories;

import com.example.demo.model.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchesRepository extends CrudRepository<Match, Long> {
}
