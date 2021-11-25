package com.Khody31.selfdrivingdatabase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Maneuver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer duration;
    String name;

    Double averageScore;
    Integer scoreCnt;

    public void addScore(double score) {
        averageScore = (averageScore * scoreCnt + score) / (scoreCnt + 1);
        scoreCnt += 1;
    }
}
