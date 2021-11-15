package by.zhabdex.quizzer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends CrudRepository<QuizResult, Long> {
    Iterable<QuizResult> getAllByOrderByMarkDesc();
}
