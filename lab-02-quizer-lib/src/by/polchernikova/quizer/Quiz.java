package by.polchernikova.quizer;

import by.polchernikova.quizer.exceptions.QuizNotFinishedException;

public class Quiz {
    public Quiz(Task.Generator generator, int taskCount) {
        gen = generator;
        tasksRemain = taskCount;
    }

    public Task nextTask() {
        tasksRemain--;
        currentTask = gen.generate();
        return currentTask;
    }

    public Result provideAnswer(String answer) {
        Result res = currentTask.validate(answer);
        if(res == Result.OK) {
            correctAnswers++;
        } else if(res == Result.WRONG) {
            wrongAnswers++;
        } else {
            incorrectInputAnswers++;
        }
        return res;
    }

    public boolean isFinished() {
        return tasksRemain == 0;
    }

    int getCorrectAnswerNumber() {
        return correctAnswers;
    }
    int getWrongAnswerNumber() {
        return wrongAnswers;
    }
    int getIncorrectInputNumber() {
        return incorrectInputAnswers;
    }

    public double getMark() throws Exception {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Mark is required before the quiz is finished");
        }
        return (double) (correctAnswers) /  (double) (correctAnswers + incorrectInputAnswers + wrongAnswers);
    }

    private Task.Generator gen;
    private Task currentTask = null;
    private int tasksRemain;
    private int wrongAnswers = 0;
    private int correctAnswers = 0;
    private int incorrectInputAnswers = 0;
}

