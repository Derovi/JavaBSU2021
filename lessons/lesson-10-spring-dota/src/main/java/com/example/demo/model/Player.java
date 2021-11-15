package com.example.demo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author derovi
 */

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
public class Player {
    Integer deaths;
    Integer denies;
    Integer kills;
    Double kda;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    Hero hero;
    @ManyToOne
    Match match;

    public double ruinerRating() {
        return kda + denies / 10.0;
    }
}
