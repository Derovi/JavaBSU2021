package by.zhabdex.quizzer;

import by.gosha_krovsh.quizer.Quiz;
import by.gosha_krovsh.quizer.Result;
import by.gosha_krovsh.quizer.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author derovi architect, dranik june
 */

@Controller
// TODO FIX REQUESTMAPPIN
public class QuizController {
    @Autowired
    QuizResultRepository quizResults;

    @GetMapping("/quiz")
    public String quiz(HttpSession session, Model model) {
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        if (quiz == null) {
            return "forward:/quiz/start";
        }
        model.addAttribute("login", session.getAttribute("login"));
        model.addAttribute("task", session.getAttribute("task"));
        model.addAttribute("quizName", session.getAttribute("quizName"));

        String message = (String) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        return "quiz";
    }
    @PostMapping("/quiz/answer")
    public String answerQuiz(@RequestParam String answer, HttpSession session, Model model) {
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        if (quiz.provideAnswer(answer) == Result.OK) {
            if (quiz.isFinished()) {
                return "redirect:/quiz/results";
            }
            Task task = quiz.nextTask();
            session.setAttribute("task", task);
            session.setAttribute("message", "Molodetz! :)))");
        } else {
            session.setAttribute("message", "Wrong answer!!! :((");
        }
        return "redirect:/quiz";
    }
    @GetMapping("/quiz/history")
    public String quizHistory(Model model) {
        model.addAttribute("results", quizResults.findAll());
        return "history";
    }

    @GetMapping("/quiz/top")
    public String quizTop(Model model) {
        model.addAttribute("results", quizResults.getAllByOrderByMarkDesc());
        return "history";
    }

    @GetMapping("/quiz/results")
    public String quizResults(HttpSession session, Model model) {
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        if (quiz == null) {
            return "redirect:/quiz";
        }
        model.addAttribute("quiz", quiz);
        session.removeAttribute("quiz");

        // save to database
        QuizResult result = new QuizResult();
        result.setLogin((String) session.getAttribute("login"));
        result.setMark(quiz.getMark());
        quizResults.save(result);

        return "result";
    }
    @PostMapping("/quiz/start")
    public String startQuiz(@RequestParam("select") String name, HttpSession session) {
        Quiz quiz = Quiz.getQuizMap().get(name);
        session.setAttribute("quizName", name);
        session.setAttribute("quiz", quiz);
        session.setAttribute("task", quiz.nextTask());
        return "redirect:/quiz";
    }

    @GetMapping("/quiz/start")
    public String start(Model model) {
        Iterable<String> names = Quiz.getQuizMap().keySet();
        model.addAttribute("names", names);
        return "start_quiz";
    }

}
