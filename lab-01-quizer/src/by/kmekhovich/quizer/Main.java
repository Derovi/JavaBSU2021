package by.kmekhovich.quizer;

import by.kmekhovich.exceptions.QuizNotFinishedException;
import by.kmekhovich.operator.Operator;
import by.kmekhovich.task_generators.GroupTaskGenerator;
import by.kmekhovich.task_generators.PoolTaskGenerator;
import by.kmekhovich.tasks.TextTask;
import by.kmekhovich.tasks.math_tasks.EquationTask;
import by.kmekhovich.tasks.math_tasks.ExpressionTask;

import java.util.EnumSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static TreeMap<String, Quiz> getQuizMap() {
        TreeMap<String, Quiz> quizMap = new TreeMap<>();
        quizMap.put("pool", new Quiz(new PoolTaskGenerator(true,
                new TextTask("no", "yes"),
                new TextTask("gg", "wp"),
                new TextTask("omg", "wtf"),
                new TextTask("yandex", "google")),
                3));
        quizMap.put("math", new Quiz(new GroupTaskGenerator(
                new ExpressionTask.Generator(-5, 5, EnumSet.allOf(Operator.class), 1),
                new EquationTask.Generator(-5, 5, EnumSet.allOf(Operator.class), 1),
                new ExpressionTask.Generator(-5, 5, EnumSet.allOf(Operator.class), 0),
                new EquationTask.Generator(-5, 5, EnumSet.allOf(Operator.class), 0)),
                5));
        quizMap.put("int", new Quiz(new GroupTaskGenerator(
                new ExpressionTask.Generator(1, 9, EnumSet.allOf(Operator.class), 0),
                new EquationTask.Generator(1, 9, EnumSet.allOf(Operator.class), 0)),
                10));
        quizMap.put("real", new Quiz(new GroupTaskGenerator(
                new ExpressionTask.Generator(1, 9, EnumSet.allOf(Operator.class), 2),
                new EquationTask.Generator(1, 9, EnumSet.allOf(Operator.class), 2)),
                3));
        quizMap.put("question", new Quiz(new PoolTaskGenerator(false,
                new TextTask("sqrt(9)", "+-3"),
                new TextTask("gfdlgdfg", "dasdas"),
                new TextTask("gsdgs", "gdsg)"),
                new TextTask("fsa", "gdsgsd")),
                1));
        return quizMap;
    }

    public static void main(String[] args) throws QuizNotFinishedException {
        System.out.println("Enter quiz name. Available tests are : ");
        TreeMap<String, Quiz> quizMap = getQuizMap();
        for(Map.Entry<String, Quiz> entry : quizMap.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
        }

        Scanner scanner = new Scanner(System.in);
        Quiz quiz;
        while (true) {
            String input = scanner.nextLine();
            if (!quizMap.containsKey(input)) {
                System.out.println("No such test");
                continue;
            }
            quiz = quizMap.get(input);
            break;
        }

        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String input = scanner.nextLine();
            Result result = quiz.provideAnswer(input);
            System.out.println(result.toString());
        }
        System.out.println("Mark : " + quiz.getMark());
    }
}
