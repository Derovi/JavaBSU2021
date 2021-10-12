package by.quackable.quizer.quiz;

import by.quackable.quizer.task.Result;
import by.quackable.quizer.task.Task;
import by.quackable.quizer.task_generator.TaskGenerator;

public class Quiz {
    public Quiz(TaskGenerator task_generator, int task_count) {
        this.task_generator = task_generator;
        if (task_count <= 0) {
            throw new RuntimeException("Task count cant be <= 0");
        }
        this.task_count = task_count;
    }

    public Task nextTask() {
        if (this.isFinished()) {
            throw new RuntimeException("Quiz is finished");
        }
        if (last_task == null) {
            last_task = task_generator.generate();
            task_count--;
        }
        return last_task;
    }

    public Result provideAnswer(String answer) {
        if (last_task == null) {
            throw new RuntimeException("generate task first");
        }
        Result result = last_task.validate(answer);
        switch (result) {
            case INCORRECT_INPUT -> incorrect_input_number++;
            case OK -> {
                correct_answer_number++;
                last_task = null;
            }
            case WRONG -> {
                wrong_answer_number++;
                last_task = null;
            }
        }
        return result;
    }

    public boolean isFinished() {
        return task_count == 0;
    }

    public int getCorrectAnswerNumber() {
        return correct_answer_number;
    }

    public int getWrongAnswerNumber() {
        return wrong_answer_number;
    }

    public int getIncorrectInputNumber() {
        return incorrect_input_number;
    }

    public double getMark() {
        if (!this.isFinished()) {
            throw new RuntimeException("Quiz not finished");
        }
        return 1. * correct_answer_number / (wrong_answer_number + correct_answer_number);
    }

    private Task last_task;
    private final TaskGenerator task_generator;
    private int task_count;
    private int correct_answer_number;
    private int wrong_answer_number;
    private int incorrect_input_number;
}
