package by.bsu.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    StudentRepository students;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value="query", required = false) String query) {
        Iterable<Student> s = query != null
                        ? students.findAllByNameContains(query)
                        : students.findAll();
        model.addAttribute("students", s);
        return "index";
    }

    @GetMapping("/top")
    public String top(Model model) {
        model.addAttribute("students",
                students.getAllByOrderByMissedLessonsDesc());
        return "index";
    }

    @GetMapping("/add")
    public String addStudentPage() {
        return "addstudent";
    }

    @PostMapping(value = "/add",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addStudent(Student student) {
        students.save(student);
        return "redirect:/";
    }
}
