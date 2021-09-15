package by.polchernikova.quizer;
import by.polchernikova.quizer.task_generators.math_task_generators.*;
import by.polchernikova.quizer.task_generators.*;
import by.polchernikova.quizer.task.math_tasks.*;
import by.polchernikova.quizer.exceptions.*;
import java.util.*;

public class MathQuiz {
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<String, Quiz>();
        EquationMathTaskGenerator easy_equation_gen = new EquationMathTaskGenerator(1, 5, true, true, false, false, 0);
        ExpressionMathTaskGenerator easy_expression_gen = new ExpressionMathTaskGenerator(1, 5, true, true, false, false, 0);
        quizMap.put("EASY TEST", new Quiz(new GroupTaskGenerator(easy_equation_gen, easy_expression_gen), 5));

        EquationMathTaskGenerator hard_equation_gen = new EquationMathTaskGenerator(-4.0, 9.0, true, true, true, true, 1);
        ExpressionMathTaskGenerator hard_expression_gen = new ExpressionMathTaskGenerator(-9.0, 4.0, true, true, true, true, 1);
        quizMap.put("HARD TEST", new Quiz(new GroupTaskGenerator(hard_equation_gen, hard_expression_gen), 10));

        quizMap.put("SENSELESS PRECISION TEST", new Quiz(new EquationMathTaskGenerator(-5, 5, true, true, true, true, 10), 5));

        ExpressionMathTask task1 = new ExpressionMathTask(0, 2, "+", 0);
        ExpressionMathTask task2 = new ExpressionMathTask(1, 1, "+", 0);
        ExpressionMathTask task3 = new ExpressionMathTask(2, 0, "+", 0);
        ExpressionMathTask task4 = new ExpressionMathTask(3, -1, "+", 0);
        ExpressionMathTask task5 = new ExpressionMathTask(4, -2, "+", 0);
        ExpressionMathTask rtask1 = new ExpressionMathTask(0.9, 2.9, "+", 1);
        ExpressionMathTask rtask2 = new ExpressionMathTask(1.9, 1.9, "+", 1);
        ExpressionMathTask rtask3 = new ExpressionMathTask(2.9, 0.9, "+", 1);
        ExpressionMathTask rtask4 = new ExpressionMathTask(3.9, -1.9, "+", 1);
        ExpressionMathTask rtask5 = new ExpressionMathTask(4.9, -2.9, "+", 1);
        quizMap.put("POOL TEST WITH DUPLICATES", new Quiz(new PoolTaskGenerator(true, task1, task2, task3, task4, task5, rtask1, rtask2, rtask3, rtask4, rtask5), 10));
        quizMap.put("POOL TEST WITHOUT DUPLICATES", new Quiz(new PoolTaskGenerator(false, task1, task2, task3, task4, task5, rtask1, rtask2, rtask3, rtask4, rtask5), 10));

        return quizMap;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Enter test name...");
        Scanner reader = new Scanner(System.in);
        String test_name = reader.nextLine();
        Map<String, Quiz> quiz_map= getQuizMap();
        Quiz test = quiz_map.get(test_name);
        if(test == null) {
            throw new InvalidTestNameException("No such test");
        }
        while(!test.isFinished()) {
            System.out.println(test.nextTask().getText());
            String answer = reader.nextLine();
            test.provideAnswer(answer);
        }
        System.out.println(test.getMark());
    }
}