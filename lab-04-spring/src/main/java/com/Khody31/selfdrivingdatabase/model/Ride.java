package com.Khody31.selfdrivingdatabase.model;

import lombok.*;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
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

    @OneToMany
    List<Maneuver> maneuverList;
}
