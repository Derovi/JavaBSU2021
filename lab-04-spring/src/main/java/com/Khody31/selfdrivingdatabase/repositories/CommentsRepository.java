package com.Khody31.selfdrivingdatabase.repositories;

import com.Khody31.selfdrivingdatabase.model.Comment;
import com.Khody31.selfdrivingdatabase.model.Maneuver;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comment, Integer> {
}
