package by.quackable.quizer.main;

import by.quackable.quizer.quiz.Quiz;
import by.quackable.quizer.task.*;
import by.quackable.quizer.task.math_task.EquationMathTask;
import by.quackable.quizer.task.math_task.ExpressionMathTask;
import by.quackable.quizer.task.math_task.MathTask;
import by.quackable.quizer.task_generator.*;
import by.quackable.quizer.task_generator.math_task_generator.*;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static Map<String, Quiz> getQuizByNameMap() {
        Map<String, Quiz> map = new HashMap<>();
        map.put("IntegerTest", new Quiz(new GroupTaskGenerator(
                new ExpressionMathTask.Generator(10, 20, EnumSet.allOf(Operator.class)),
                new EquationMathTask.Generator(0, 10, EnumSet.of(Operator.MULTIPLY, Operator.SUM)),
                new AppleTaskGenerator("A", "B", 20)),
                10));
        map.put("RealTest", new Quiz(new GroupTaskGenerator(
                new ExpressionMathTask.Generator(2, 10, 20, EnumSet.allOf(Operator.class)),
                new EquationMathTask.Generator(2, 0, 10, EnumSet.of(Operator.SUBTRACT, Operator.SUM))),
                10));
        map.put("RealHighPrecisionTest", new Quiz(new GroupTaskGenerator(
                new ExpressionMathTask.Generator(6, 10, 20, EnumSet.allOf(Operator.class)),
                new EquationMathTask.Generator(6, 0, 10, EnumSet.of(Operator.SUBTRACT, Operator.SUM))),
                10));
        return map;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String quiz_name = sc.next();
        Quiz quiz = getQuizByNameMap().get(quiz_name);
        if (quiz == null) {
            System.out.println("Cant get quiz with name: " + quiz_name);
            return;
        }
        while (!quiz.isFinished()) {
            Task task = quiz.nextTask();
            System.out.println(task.getText());
            String input = sc.next();
            Result result = quiz.provideAnswer(input);
            System.out.println(result);
            if (result == Result.WRONG) {
                System.out.println("Right answer: " + ((MathTask) task).getAnswer());
            }
            System.out.println("==============");
        }
        System.out.println(quiz.getMark());
    }

}
