package by.Khody31.quizer.tasks.math_tasks;

import by.Khody31.quizer.Task;

public interface MathTask extends Task {
    interface Generator extends Task.Generator {
        public double getMinNumber();
        public double getMaxNumber();
        default double getDiffNumber() {
            return getMaxNumber() - getMinNumber();
        }
    }

    public enum Operation {
        SUM("+"),
        DIFFERENCE("-"),
        MULTIPLICATION("*"),
        DIVISION("/");

        private Operation(String symbol) {
            this.symbol = symbol;
        }

        String symbol;
    }

    public String getAnswer();
}
