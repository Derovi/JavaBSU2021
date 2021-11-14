package by.bsu.lesson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Iterable<Student> getAllByOrderByMissedLessonsDesc();
    Iterable<Student> findAllByNameContains(String name);
}
