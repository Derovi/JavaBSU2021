package by.kmekhovich.quizer;

import by.kmekhovich.exceptions.QuizNotFinishedException;
import by.kmekhovich.operator.Operator;
import by.kmekhovich.task_generators.GroupTaskGenerator;
import by.kmekhovich.task_generators.PoolTaskGenerator;
import by.kmekhovich.task_generators.math_tasks_generators.IntegerEquationMathTaskGenerator;
import by.kmekhovich.task_generators.math_tasks_generators.IntegerExpressionMathTaskGenerator;
import by.kmekhovich.task_generators.math_tasks_generators.RealEquationMathTaskGenerator;
import by.kmekhovich.task_generators.math_tasks_generators.RealExpressionMathTaskGenerator;
import by.kmekhovich.tasks.TextTask;
import by.kmekhovich.tasks.math_tasks.RealEquationMathTask;

import java.util.EnumSet;
import java.util.TreeMap;

public class Quiz {
    TaskGenerator generator;
    int taskCount;
    int solvedTasks = 0;
    Task currentTask = null;
    int score = 0;
    int incorrectInputCount = 0;
    boolean lastIncorrectInput = false;

    public Quiz(TaskGenerator generator, int taskCount) {
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

    boolean isFinished() {
        return solvedTasks == taskCount;
    }

    int getCorrectAnswerNumber() {
        return score;
    }

    int getWrongAnswerNumber() {
        return solvedTasks - score;
    }

    int getIncorrectInputNumber() {
        return incorrectInputCount;
    }

    double getMark() throws QuizNotFinishedException {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Finish test firstly");
        }
        return (double) score / taskCount;
    }

    static TreeMap<String, Quiz> getQuizMap() {
        TreeMap<String, Quiz> quizMap = new TreeMap<>();
        quizMap.put("pool", new Quiz(new PoolTaskGenerator(true,
                new TextTask("no", "yes"),
                new TextTask("gg", "wp"),
                new TextTask("omg", "wtf"),
                new TextTask("yandex", "google")),
                3));
        quizMap.put("math", new Quiz(new GroupTaskGenerator(
                new IntegerExpressionMathTaskGenerator(-5, 5, EnumSet.allOf(Operator.class)),
                new IntegerEquationMathTaskGenerator(-5, 5, EnumSet.allOf(Operator.class)),
                new RealExpressionMathTaskGenerator(3, -5, 5, EnumSet.allOf(Operator.class)),
                new RealEquationMathTaskGenerator(3,-5, 5, EnumSet.allOf(Operator.class))),
                5));
        quizMap.put("int", new Quiz(new GroupTaskGenerator(
                new IntegerExpressionMathTaskGenerator(1, 9, EnumSet.allOf(Operator.class)),
                new IntegerEquationMathTaskGenerator(1, 9, EnumSet.allOf(Operator.class))),
                10));
        quizMap.put("real", new Quiz(new GroupTaskGenerator(
                new RealExpressionMathTaskGenerator(3, 1, 9, EnumSet.allOf(Operator.class)),
                new RealEquationMathTaskGenerator(3, 1, 9, EnumSet.allOf(Operator.class))),
                3));
        quizMap.put("questions", new Quiz(new PoolTaskGenerator(false,
                new TextTask("sqrt(9)", "+-3"),
                new TextTask("gfdlgdfg", "dasdas"),
                new TextTask("gsdgs", "gdsg)"),
                new TextTask("fsa", "gdsgsd")),
                1));
        return quizMap;
    }
}
