package by.alperin.quizer;

import by.alperin.quizer.task_generators.GroupTaskGenerator;
import by.alperin.quizer.task_generators.PoolTaskGenerator;
import by.alperin.quizer.tasks.TextTask;
import by.alperin.quizer.tasks.math_tasks.EquationTask;
import by.alperin.quizer.tasks.math_tasks.ExpressionTask;
import by.alperin.quizer.tasks.math_tasks.MathTask;

import java.util.*;

public class Main {
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<>();

        EnumSet<MathTask.Operation> allOperations = EnumSet.of(MathTask.Operation.SUM, MathTask.Operation.DIFFERENCE, MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION);
        ExpressionTask.Generator integerExGenerator = new ExpressionTask.Generator(-20, 20, allOperations);
        EquationTask.Generator integerEqGenerator = new EquationTask.Generator(-20, 20, allOperations);

        ExpressionTask.Generator realExGenerator = new ExpressionTask.Generator(-2, 0, allOperations,4);
        EquationTask.Generator realEqGenerator = new EquationTask.Generator(0, 2, allOperations,4);

        ExpressionTask.Generator extraExGenerator = new ExpressionTask.Generator(-5, 5, allOperations,10);
        EquationTask.Generator extraEqGenerator = new EquationTask.Generator(-5, 5, allOperations, 10);

        quizMap.put("IntegerEx", new Quiz(integerExGenerator, 10));
        quizMap.put("IntegerEq", new Quiz(integerEqGenerator, 10));
        quizMap.put("RealEx", new Quiz(realExGenerator, 10));
        quizMap.put("RealEq", new Quiz(realEqGenerator, 10));
        quizMap.put("ExtraEx", new Quiz(extraExGenerator, 10));
        quizMap.put("ExtraEq", new Quiz(extraEqGenerator, 10));

        ExpressionTask integerExTask1 = new ExpressionTask(3, 5, MathTask.Operation.SUM, 0);
        ExpressionTask integerExTask2 = new ExpressionTask(10, -2, MathTask.Operation.DIFFERENCE, 0);
        ExpressionTask integerExTask3 = new ExpressionTask(0, 1, MathTask.Operation.DIVISION, 0);
        ExpressionTask integerExTask4 = new ExpressionTask(-4, -5, MathTask.Operation.MULTIPLICATION, 0);
//        ExpressionTask exceptionExTask1 = new ExpressionTask(1, 0, MathTask.Operation.DIVISION, 0);
//        ExpressionTask exceptionExTask2 = new ExpressionTask(0.99999999, 0.11111111, MathTask.Operation.DIVISION, 0);
//        ExpressionTask exceptionExTask3 = new ExpressionTask(0, 0, MathTask.Operation.DIVISION, 0);

        ExpressionTask realExTask1 = new ExpressionTask(2.374252, 3.234972553, MathTask.Operation.SUM, 9);
        ExpressionTask realExTask2 = new ExpressionTask(-11.128365, -7.435623893, MathTask.Operation.DIFFERENCE, 9);
        ExpressionTask realExTask3 = new ExpressionTask(2.43431488, 1.121213123, MathTask.Operation.DIVISION, 3);
        ExpressionTask realExTask4 = new ExpressionTask(2.281337, 1.9723453, MathTask.Operation.MULTIPLICATION, 3);
//        ExpressionTask exceptionExTask4 = new ExpressionTask(0, 0, MathTask.Operation.MULTIPLICATION, 8);
//        ExpressionTask exceptionExTask5 = new ExpressionTask(-123, 123, MathTask.Operation.SUM, 228);
//        ExpressionTask exceptionExTask6 = new ExpressionTask(0, 0.01, MathTask.Operation.DIVISION, 1);

        EquationTask integerEqTask1 = new EquationTask(3, 5, MathTask.Operation.SUM, 0);
        EquationTask integerEqTask2 = new EquationTask(10, -2, MathTask.Operation.DIFFERENCE, 0);
        EquationTask integerEqTask3 = new EquationTask(3, 1, MathTask.Operation.DIVISION, 0);
        EquationTask integerEqTask4 = new EquationTask(-1, -5, MathTask.Operation.MULTIPLICATION, 0);
//        EquationTask exceptionEqTask1 = new EquationTask(0, 0, MathTask.Operation.MULTIPLICATION, 2);
//        EquationTask exceptionEqTask2 = new EquationTask(0, 0, MathTask.Operation.DIVISION, 0);
//        EquationTask exceptionEqTask3 = new EquationTask(1, 0, MathTask.Operation.DIVISION, 6);
//        EquationTask exceptionEqTask4 = new EquationTask(1, 1, MathTask.Operation.SUM, 13337);
//        EquationTask exceptionEqTask5 = new EquationTask(0.99999999, 0.0000001, MathTask.Operation.DIVISION, 2);

        EquationTask realEqTask1 = new EquationTask(2.374252, 3.234972553, MathTask.Operation.SUM, 9);
        EquationTask realEqTask2 = new EquationTask(-11.128365, -7.435623893, MathTask.Operation.DIFFERENCE, 9);
        EquationTask realEqTask3 = new EquationTask(2.43431488, 1.121213123, MathTask.Operation.DIVISION, 3);
        EquationTask realEqTask4 = new EquationTask(2.281337, 1.9723453, MathTask.Operation.MULTIPLICATION, 3);

        TextTask textTask1 = new TextTask("На берёзе росло 5 яблок. Ваня сорвал 2 яблока. Сколько яблок осталось на берёзе?", "0");
        TextTask textTask2 = new TextTask("На стене нарисованы футбольные ворота и рядом мяч. Тебе говорят: \"Забей гол\". Твои действия?", "А ты сначала пас дай!");

        List<Task> realTasks = new ArrayList<>();
        realTasks.add(realEqTask1);
        realTasks.add(realEqTask2);
        realTasks.add(realEqTask3);
        realTasks.add(realEqTask4);
        realTasks.add(realExTask1);
        realTasks.add(realExTask2);
        realTasks.add(realExTask3);
        realTasks.add(realExTask4);

        List<Task> integerTasks = new ArrayList<>();
        integerTasks.add(integerEqTask1);
        integerTasks.add(integerEqTask2);
        integerTasks.add(integerEqTask3);
        integerTasks.add(integerEqTask4);
        integerTasks.add(integerExTask1);
        integerTasks.add(integerExTask2);
        integerTasks.add(integerExTask3);
        integerTasks.add(integerExTask4);

        PoolTaskGenerator integerDuplicatesPoolGenerator = new PoolTaskGenerator(true, integerTasks);
        PoolTaskGenerator integerNoDuplicatesPoolGenerator = new PoolTaskGenerator(false, integerTasks);
        PoolTaskGenerator realDuplicatesPoolGenerator = new PoolTaskGenerator(true, realTasks);
        PoolTaskGenerator realNoDuplicatesPoolGenerator = new PoolTaskGenerator(false, realTasks);
        PoolTaskGenerator textTasks = new PoolTaskGenerator(false, textTask1, textTask2);

        GroupTaskGenerator groupIntegerGenerator = new GroupTaskGenerator(integerExGenerator, integerEqGenerator);
        GroupTaskGenerator groupRealGenerator = new GroupTaskGenerator(realExGenerator, realEqGenerator);
        GroupTaskGenerator groupMixedGenerator = new GroupTaskGenerator(integerNoDuplicatesPoolGenerator, realNoDuplicatesPoolGenerator);

        quizMap.put("IntegerDupPool" , new Quiz(integerDuplicatesPoolGenerator, 8));
        quizMap.put("IntegerNoDupPool" , new Quiz(integerNoDuplicatesPoolGenerator, 8));
        quizMap.put("RealDupPool" , new Quiz(realDuplicatesPoolGenerator, 8));
        quizMap.put("RealNoDupPool" , new Quiz(realNoDuplicatesPoolGenerator, 8));
        quizMap.put("GroupInteger", new Quiz(groupIntegerGenerator, 10));
        quizMap.put("GroupReal", new Quiz(groupRealGenerator, 10));
        quizMap.put("GroupMixed", new Quiz(groupMixedGenerator, 10));
        quizMap.put("Bonus", new Quiz(textTasks, 2));
        return quizMap;
    }


    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Map<String, Quiz> quizMap = getQuizMap();
        String testName;
        do {
            System.out.print("Enter the test name:");
            testName = reader.nextLine();
        } while (quizMap.get(testName) == null);

        Quiz quiz = quizMap.get(testName);
        while (!quiz.isFinished()) {
            Task task = quiz.nextTask();
            System.out.println(task.getText());
            String userAnswer = reader.nextLine();
            Result result = quiz.provideAnswer(userAnswer);

            if (result == Result.OK) {
                System.out.println("Correct!\n");
            } else if (result == Result.WRONG) {
                System.out.println("Wrong\n");
            } else if (result == Result.INCORRECT_INPUT) {
                System.out.println("Incorrect input\n");
            }
        }

        System.out.println("Your mark: " + quiz.getMark() * 10);
        System.out.println("--------------------------------");
        System.out.println("Correct answers: " + quiz.getCorrectAnswerNumber());
        System.out.println("Wrong answers: " + quiz.getWrongAnswerNumber());
        System.out.println("Incorrect answers: " + quiz.getIncorrectInputNumber());
    }
}