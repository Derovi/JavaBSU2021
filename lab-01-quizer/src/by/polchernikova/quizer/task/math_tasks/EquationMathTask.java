package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;

import java.util.EnumSet;
import java.util.Objects;

public class EquationMathTask extends AbstractMathTask {
    public static class Generator extends AbstractMathTask.Generator {
        public Generator (
                double minNumber,
                double maxNumber,
                EnumSet<Operation> opers,
                int precision
        ) {
            super(minNumber, maxNumber, opers, precision);
        }
    }
    public EquationMathTask(double firstArgument, double secondArgument, String oper, int precision) {
        super(firstArgument, secondArgument, oper, precision);
    }

    public String getText() {
        return Double.toString(firstArg) + operation + Double.toString(secondArg) + "=?";
    }

    public String getAnswer() {
        return Double.toString(calculateAnswer());
    }

    public double calculateAnswer() {
        double ans;
        if (Objects.equals(operation, "+")) {
            ans = (firstArg + secondArg);
        } else if (Objects.equals(operation, "-")) {
            ans = (firstArg - secondArg);
        } else if (Objects.equals(operation, "*")) {
            ans = (firstArg * secondArg);
        } else if (Objects.equals(operation, "/")) {
            ans = (firstArg / secondArg);
        } else {
            throw new NumberFormatException();
        }
        return convertUsingPrecision(ans);
    }
}
