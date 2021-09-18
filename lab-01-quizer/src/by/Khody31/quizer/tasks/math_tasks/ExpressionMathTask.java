package by.Khody31.quizer.tasks.math_tasks;

import by.Khody31.quizer.Task;

import java.util.EnumSet;
import java.util.Objects;

public class ExpressionMathTask extends AbstractMathTask {
    public static class Generator extends AbstractMathTask.Generator {
        public Generator (
                double minNumber, double maxNumber,
                EnumSet<Operation> ops, int precision) {
            super(minNumber, maxNumber, ops, precision);
        }

        @Override
        public Task generate() {
            double num1 = (Math.random() * (maxNum - minNum) + minNum);
            double num2 = (Math.random() * (maxNum - minNum) + minNum);
            String op = operations.get((int)(Math.random() * operations.size()));
            return new ExpressionMathTask(num1, num2, op, precision);
        }
    }

    public ExpressionMathTask(double num1, double num2,
                            String op, int precision) {
        super(num1, num2, op, precision);
    }

    @Override
    public String getText() {
        return num1 + op + num2 + "=?";
    }

    @Override
    public String getAnswer() {
        double ans = 0;
        if (Objects.equals(op, "+")) {
            ans = num1 + num2;
        } else if (Objects.equals(op, "-")) {
            ans = num1 - num2;
        } else if (Objects.equals(op, "*")) {
            ans = num1 * num2;
        } else if (Objects.equals(op, "/")) {
            ans = num1 / num2;
        } else {
            throw new NumberFormatException();
        }
        return Double.toString(ans);
    }
}
