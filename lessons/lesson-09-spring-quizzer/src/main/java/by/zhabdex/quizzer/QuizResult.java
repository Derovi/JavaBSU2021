package by.zhabdex.quizzer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author derovi
 */

@Entity
@Data
public class QuizResult {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String login;
    Double mark;
}
