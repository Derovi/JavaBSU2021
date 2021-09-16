package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Task;

public interface MathTask extends Task {
    interface Generator extends Task.Generator {
        public double getMinNumber();
        public double getMaxNumber();
        default double getDiffNUmber() {
            return (getMaxNumber() - getMinNumber());
        }
    }
    public enum Operation {
        SUM,
        DIFFERENCE,
        MULTIPLICATION,
        DIVISION
    }
    public String getAnswer();
}
