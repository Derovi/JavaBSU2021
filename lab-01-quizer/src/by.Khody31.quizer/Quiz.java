package by.Khody31.quizer;

import by.Khody31.quizer.exceptions.QuizNotFinishedException;
import by.Khody31.quizer.exceptions.UndefinedResultException;

class Quiz {
    Quiz(Task.Generator generator, int taskCount) {
        this.generator = generator;
        this.tasksRemain = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        currentTask = generator.generate();
        --tasksRemain;
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) throws Exception {
        Result result = currentTask.validate(answer);
        if (result == Result.OK) {
            ++correctAnswerNumber;
        } else if (result == Result.INCORRECT_INPUT) {
            ++incorrectInputNumber;
        } else if (result == Result.WRONG) {
            ++wrongAnswerNumber;
        } else {
            throw new UndefinedResultException("Answer result is undefined");
        }

        return result;
    }
    boolean isFinished() {
        return tasksRemain == 0;
    }

    int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }
    int getWrongAnswerNumber() {
        return wrongAnswerNumber;
    }
    int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    double getMark() throws QuizNotFinishedException {
        if(!isFinished()) {
            throw new QuizNotFinishedException("Mark is required before the quiz is finished");
        }
        int answerCount = correctAnswerNumber + wrongAnswerNumber + incorrectInputNumber;
        return (double) correctAnswerNumber / (double) answerCount;
    }

    private final Task.Generator generator;
    private Task currentTask = null;
    private int tasksRemain = 0;
    private int correctAnswerNumber = 0;
    private int wrongAnswerNumber = 0;
    private int incorrectInputNumber = 0;
}