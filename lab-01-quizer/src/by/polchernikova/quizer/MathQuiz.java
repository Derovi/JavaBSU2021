package by.polchernikova.quizer;
import by.polchernikova.quizer.task.math_tasks.IntegerExpressionMathTask;
import by.polchernikova.quizer.task_generators.math_task_generators.*;
import by.polchernikova.quizer.task_generators.*;
import by.polchernikova.quizer.task.math_tasks.*;
import by.polchernikova.quizer.exceptions.*;
import java.util.*;

public class MathQuiz {
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<String, Quiz>();
        IntegerEquationMathTaskGenerator easy_equation_gen = new IntegerEquationMathTaskGenerator(1, 5, true, true, false, false);
        IntegerExpressionMathTaskGenerator easy_expression_gen = new IntegerExpressionMathTaskGenerator(1, 5, true, true, false, false);
        quizMap.put("EASY TEST", new Quiz(new GroupTaskGenerator(easy_equation_gen, easy_expression_gen), 5));

        RealEquationMathTaskGenerator hard_equation_gen = new RealEquationMathTaskGenerator(-4.0, 9.0, true, true, true, true, 1);
        RealExpressionMathTaskGenerator hard_expression_gen = new RealExpressionMathTaskGenerator(-9.0, 4.0, true, true, true, true, 1);
        quizMap.put("HARD TEST", new Quiz(new GroupTaskGenerator(hard_equation_gen, hard_expression_gen), 10));

        quizMap.put("SENSELESS PRECISION TEST", new Quiz(new RealEquationMathTaskGenerator(-5, 5, true, true, true, true, 10), 5));

        IntegerExpressionMathTask task1 = new IntegerExpressionMathTask(0, 2, "+");
        IntegerExpressionMathTask task2 = new IntegerExpressionMathTask(1, 1, "+");
        IntegerExpressionMathTask task3 = new IntegerExpressionMathTask(2, 0, "+");
        IntegerExpressionMathTask task4 = new IntegerExpressionMathTask(3, -1, "+");
        IntegerExpressionMathTask task5 = new IntegerExpressionMathTask(4, -2, "+");
        RealExpressionMathTask rtask1 = new RealExpressionMathTask(0.9, 2.9, "+", 1);
        RealExpressionMathTask rtask2 = new RealExpressionMathTask(1.9, 1.9, "+", 1);
        RealExpressionMathTask rtask3 = new RealExpressionMathTask(2.9, 0.9, "+", 1);
        RealExpressionMathTask rtask4 = new RealExpressionMathTask(3.9, -1.9, "+", 1);
        RealExpressionMathTask rtask5 = new RealExpressionMathTask(4.9, -2.9, "+", 1);
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
