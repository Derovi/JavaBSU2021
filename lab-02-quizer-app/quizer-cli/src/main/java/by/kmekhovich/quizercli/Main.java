package by.kmekhovich.quizercli;

import by.kmekhovich.exceptions.QuizNotFinishedException;
import by.kmekhovich.quizer.Quiz;
import by.kmekhovich.quizer.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter quiz name. Available tests are : ");
        HashMap<String, Quiz> quizMap = Quiz.getQuizMap();
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
        try {
            System.out.println("Mark : " + quiz.getMark());
        } catch (QuizNotFinishedException ignored) {}
    }
}
