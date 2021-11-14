package by.bsu.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class StudentRestController {

    @Autowired
    StudentRepository students;

    @GetMapping("/students")
    public Iterable<Student> getAll() {
        return students.findAll();
    }
}
