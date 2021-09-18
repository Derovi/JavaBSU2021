package by.Khody31.quizer;

import by.Khody31.quizer.task_generators.GroupTaskGenerator;
import by.Khody31.quizer.task_generators.PoolTaskGenerator;
import by.Khody31.quizer.tasks.TextTask;
import by.Khody31.quizer.tasks.math_tasks.EquationMathTask;
import by.Khody31.quizer.tasks.math_tasks.ExpressionMathTask;
import by.Khody31.quizer.tasks.math_tasks.MathTask;

import java.util.*;

public class Main {
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<>();

        EquationMathTask.Generator equationGenerator =
                new EquationMathTask.Generator(1, 5, EnumSet.of(
                        MathTask.Operation.SUM,
                        MathTask.Operation.DIFFERENCE), 2);
        quizMap.put("Equation test", new Quiz(equationGenerator, 5));

        ExpressionMathTask.Generator expressionGenerator =
                new ExpressionMathTask.Generator(1, 5, EnumSet.of(
                        MathTask.Operation.MULTIPLICATION,
                        MathTask.Operation.DIVISION), 2);
        quizMap.put("Expression test", new Quiz(expressionGenerator, 5));

        List<Task> textTasks = new ArrayList<>(5);
        textTasks.add(new TextTask("question", "answer"));
        textTasks.add(new TextTask("question1", "answer1"));
        textTasks.add(new TextTask("question2", "answer2"));
        textTasks.add(new TextTask("question3", "answer3"));
        textTasks.add(new TextTask("question4", "answer4"));
        PoolTaskGenerator poolGenerator = new PoolTaskGenerator(true, textTasks);
        quizMap.put("Text test", new Quiz(poolGenerator, 5));

        quizMap.put("Group test", new Quiz(new GroupTaskGenerator(
                poolGenerator, equationGenerator, expressionGenerator), 15));
        return quizMap;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Enter test name...");
        Scanner reader = new Scanner(System.in);
        String testName = reader.nextLine();
        Map<String, Quiz> quizMap = getQuizMap();
        Quiz test = quizMap.get(testName);
        while (!test.isFinished()) {
            System.out.println(test.nextTask().getText());
            String answer = reader.nextLine();
            test.provideAnswer(answer);
        }
        System.out.println(test.getMark());
    }
}