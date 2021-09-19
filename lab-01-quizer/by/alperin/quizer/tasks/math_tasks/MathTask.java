package by.alperin.quizer.tasks.math_tasks;

import by.alperin.quizer.Task;

public interface MathTask extends Task {
    interface Generator extends Task.Generator {
        double getMinNumber();
        double getMaxNumber();

        default double getDiffNumber() {
            return getMaxNumber() - getMinNumber();
        }
    }

    enum Operation {
        SUM('+'),
        DIFFERENCE('-'),
        MULTIPLICATION('*'),
        DIVISION('/');

        Operation(char symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return String.valueOf(symbol);
        }

        private final char symbol;
    }
}
