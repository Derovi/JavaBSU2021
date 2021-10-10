package by.kmekhovich.quizer;

import by.kmekhovich.exceptions.QuizNotFinishedException;
import by.kmekhovich.operator.Operator;
import by.kmekhovich.task_generators.GroupTaskGenerator;
import by.kmekhovich.task_generators.PoolTaskGenerator;
import by.kmekhovich.tasks.math_tasks.*;
import by.kmekhovich.tasks.TextTask;

import java.util.EnumSet;
import java.util.TreeMap;
import java.util.HashMap;

public class Quiz {
    Task.Generator generator;
    int taskCount;
    int solvedTasks = 0;
    Task currentTask = null;
    int score = 0;
    int incorrectInputCount = 0;
    boolean lastIncorrectInput = false;

    public Quiz(Task.Generator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    public Task nextTask() {
        if (!lastIncorrectInput) {
            currentTask = generator.generate();
        }
        return currentTask;
    }

    public Result provideAnswer(String answer) {
        if (currentTask == null) {
            throw new RuntimeException("Generate task firstly");
        }
        Result result = currentTask.validate(answer);
        if (result == Result.OK) {
            score++;
        }
        if (result == Result.INCORRECT_INPUT) {
            lastIncorrectInput = true;
            incorrectInputCount++;
        } else {
            lastIncorrectInput = false;
            solvedTasks++;
        }
        return result;
    }

    public boolean isFinished() {
        return solvedTasks == taskCount;
    }

    public int getCorrectAnswerNumber() {
        return score;
    }

    public int getWrongAnswerNumber() {
        return solvedTasks - score;
    }

    public int getIncorrectInputNumber() {
        return incorrectInputCount;
    }

    public double getMark() throws QuizNotFinishedException {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Finish test firstly");
        }
        return (double) score / taskCount;
    }

    public static HashMap<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> quizMap = new HashMap<>();
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
}
