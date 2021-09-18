package by.Khody31.quizer;

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
    Result provideAnswer(String answer) {
        if (currentTask == null) {
            // TODO : throw exception
        }

        Result result = currentTask.validate(answer);
        if (result == Result.OK) {
            ++correctAnswerNumber;
        } else if (result == Result.INCORRECT_INPUT) {
            ++incorrectInputNumber;
        } else if (result == Result.WRONG) {
            ++wrongAnswerNumber;
        } else {
            // TODO : throw exception
        }

        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return tasksRemain == 0;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return wrongAnswerNumber;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     * Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) {
            // TODO : throw exception
            return 0.0;
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