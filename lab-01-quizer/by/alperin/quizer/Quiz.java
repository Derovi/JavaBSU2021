package by.alperin.quizer;

import by.alperin.quizer.exceptions.QuizFinishedException;
import by.alperin.quizer.exceptions.QuizNotFinishedException;

public class Quiz {
    private int correctAnswerNumber;
    private int wrongAnswerNumber;
    private int incorrectAnswerNumber;
    private int taskCount;
    private Result lastResult;
    private final Task.Generator generator;
    private Task lastTask;

    Quiz(Task.Generator generator, int taskCount) {
        this.correctAnswerNumber = 0;
        this.wrongAnswerNumber = 0;
        this.incorrectAnswerNumber = 0;
        this.taskCount = taskCount;
        this.generator = generator;
    }

    Task nextTask() {
        if (taskCount == 0) {
            throw new QuizFinishedException("Couldn't found next task!");
        }

        if (lastResult == Result.INCORRECT_INPUT) {
            return lastTask;
        }

        --taskCount;
        lastTask = generator.generate();
        return lastTask;
    }

    Result provideAnswer(String answer) {
        lastResult = lastTask.validate(answer);

        if (lastResult == Result.OK) {
            ++correctAnswerNumber;
        } else if (lastResult == Result.WRONG) {
            ++wrongAnswerNumber;
        } else if (lastResult == Result.INCORRECT_INPUT) {
            ++incorrectAnswerNumber;
        } else {
            throw new RuntimeException("Validation exception");
        }

        return lastResult;
    }

    boolean isFinished() {
        return taskCount == 0;
    }

    int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    int getWrongAnswerNumber() {
        return wrongAnswerNumber;
    }

    int getIncorrectInputNumber() {
        return incorrectAnswerNumber;
    }

    double getMark() {
        if (isFinished()) {
            return (double) correctAnswerNumber / (correctAnswerNumber + wrongAnswerNumber);
        } else {
            throw new QuizNotFinishedException("Quiz isn't finished!");
        }
    }
}
