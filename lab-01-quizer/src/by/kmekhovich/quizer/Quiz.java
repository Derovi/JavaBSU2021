package by.kmekhovich.quizer;

import by.kmekhovich.exceptions.QuizNotFinishedException;
import by.kmekhovich.operator.Operator;
import by.kmekhovich.task_generators.GroupTaskGenerator;
import by.kmekhovich.task_generators.PoolTaskGenerator;
import by.kmekhovich.tasks.TextTask;

import java.util.EnumSet;
import java.util.TreeMap;

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
}
