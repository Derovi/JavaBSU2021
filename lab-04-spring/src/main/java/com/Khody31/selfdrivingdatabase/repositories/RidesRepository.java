package com.Khody31.selfdrivingdatabase.repositories;

import com.Khody31.selfdrivingdatabase.model.Ride;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RidesRepository extends CrudRepository<Ride, Integer> {}
