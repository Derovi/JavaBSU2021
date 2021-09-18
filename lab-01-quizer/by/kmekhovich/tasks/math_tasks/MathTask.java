package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.quizer.Task;

public interface MathTask extends Task {
    boolean isCorrectInput(String input);

    default boolean hasDivisionByNull() {
        return false;
    }
}
