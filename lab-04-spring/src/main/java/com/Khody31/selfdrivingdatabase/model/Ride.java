package com.Khody31.selfdrivingdatabase.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    String location;
    String carName;
    Date date;

    @OneToMany
    List<Maneuver> maneuverList;

    @OneToMany
    List<Comment> commentsList;

    public void addComment(Comment comment) {
        commentsList.add(comment);
    }
}
