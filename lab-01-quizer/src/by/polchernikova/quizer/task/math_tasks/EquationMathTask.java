package by.polchernikova.quizer.task.math_tasks;

import by.polchernikova.quizer.Result;

import java.util.Objects;

public class EquationMathTask extends AbstractMathTask {
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
