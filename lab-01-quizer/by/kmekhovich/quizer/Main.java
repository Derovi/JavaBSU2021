package by.kmekhovich.quizer;

import by.kmekhovich.exceptions.QuizNotFinishedException;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws QuizNotFinishedException {
        System.out.println("Enter quiz name. Available tests are : ");
        TreeMap<String, Quiz> quizMap = Quiz.getQuizMap();
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
